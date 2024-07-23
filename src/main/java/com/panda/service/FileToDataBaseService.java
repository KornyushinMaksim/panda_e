package com.panda.service;

import com.panda.dto.EmployeeDto;
import com.panda.dto.FileToDataBaseDto;
import com.panda.mapper.EmployeeMapper;
import com.panda.mapper.FileToDataBaseMapper;
import com.panda.model.Employee;
import com.panda.model.FileToDataBase;
import com.panda.repository.FileToDataBaseRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileToDataBaseService {

    private final FileToDataBaseRepository fileToDataBaseRepository;
    private final EmployeeService employeeService;
    private final FileToDataBaseMapper fileToDataBaseMapper;
    private final EmployeeMapper employeeMapper;
    @Value("${upload.path}")
    private String pathToStorage;

    /**
     * создание файла
     * создает файл и добавляет информацию в БД
     *
     * @param nameFile
     */
    @Transactional
    public void addNewFile(String nameFile, HttpServletResponse response) {

        try {
            File file = new File(pathToStorage + File.separator + nameFile);
            if (file.createNewFile()) {

                FileToDataBase fileToDataBase = FileToDataBase.builder()
                        .nameFile(file.getName())
                        .typeFile(formatFile(file.getName(), "."))
                        .dateOfChange(LocalDate.now())
                        .size(file.length())
                        .pathToStorage(file.getPath())
                        .isActive(false)
                        .build();

                fileToDataBaseRepository.save(fileToDataBase);
                System.out.println("Файл создан"); //заменить на логи

                response.sendRedirect("/user");

            } else {
                System.out.println("Файл уже существует"); //заменить на логи
                response.sendRedirect("/user");
            }

        } catch (IOException e) {
            System.out.println("Ошибка при создании файла"); //заменить на логи
            e.printStackTrace();
        }
    }

    /**
     * скачивание файлов из хранилища на комп
     * принимает id, по которому получает путь хранения нужного файла
     * и в виде массива байт передает на фронт
     * @param id
     * @param employeeId
     * @param response
     */
    @Transactional
    public void downloadFile(UUID id, UUID employeeId, HttpServletResponse response) {

        FileToDataBase fileToDataBase = fileToDataBaseRepository.findById(id).orElseThrow();
        EmployeeDto dto = employeeService.getEmployeeById(employeeId);
        Employee entity = employeeMapper.toEntity(dto);
        String filePath = fileToDataBase.getPathToStorage();
        String nameFileTypeFile = fileToDataBase.getNameFile();
        byte[] encodedFile = null;

        try (OutputStream out = response.getOutputStream()) {
            encodedFile = Files.readAllBytes(Paths.get(filePath));
            response.setHeader("Content-Disposition", "Attachment; fileName=" + nameFileTypeFile);
            out.write(encodedFile);

            fileToDataBase.setIsActive(true);
            fileToDataBase.setEmployee(entity);

            fileToDataBaseRepository.save(fileToDataBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * возвращает данные об объекте из БД по id
     *
     * @param id
     * @return
     */
    public FileToDataBaseDto getFileById(UUID id) {

        FileToDataBase fileToDataBase = fileToDataBaseRepository.findById(id).orElseThrow();

        return fileToDataBaseMapper.toDto(fileToDataBase);
    }

    /**
     * получение данных из таблицы по имени файла
     *
     * @param nameFile
     * @return
     */
    public FileToDataBase getFileToDataBaseByName(String nameFile) {

        return fileToDataBaseRepository.findByNameFile(nameFile);
    }

    /**
     * загрузка файла в хранилище
     * принимает файл в формате multipartFile, создает файл, если такого нет
     * записывает в него содержимое и записывает информацию о файле в БД
     * ставит метку isActive=false, колонка id_employee принимает значение null
     * @param multipartFile
     * @param employeeId
     */
    @Transactional
    public void uploadFile(MultipartFile multipartFile, UUID employeeId) {

        String path = pathToStorage + File.separator + multipartFile.getOriginalFilename();
        String nameMultiPartFile = multipartFile.getOriginalFilename();
        FileToDataBase fileToDB = getFileToDataBaseByName(nameMultiPartFile);

        if (nameMultiPartFile != null) {
            try {
                File file = new File(path);

                if (file.createNewFile()) {
                    FileToDataBase fileToDataBase = FileToDataBase.builder()
                            .pathToStorage(path)
                            .nameFile(nameMultiPartFile)
                            .typeFile(formatFile(nameMultiPartFile, "."))
                            .dateOfChange(LocalDate.now())
                            .size(multipartFile.getSize() / 1000)
                            .isActive(false)
                            .employee(null)
                            .build();
                    writeToFile(path, multipartFile);
                    fileToDataBaseRepository.save(fileToDataBase);
                } else {

                    if (fileToDB.getIsActive() && fileToDB.getEmployee().getId().equals(employeeId)) {
                        writeToFile(path, multipartFile);
                        fileToDB.setDateOfChange(LocalDate.now());
                        fileToDB.setSize(multipartFile.getSize());
                        fileToDB.setIsActive(false);
                        fileToDB.setEmployee(null);
                        fileToDataBaseRepository.save(fileToDB);
                    }
                }
            } catch (EOFException eof) {
                eof.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * возвращает файл в виде массива байт
     * ставит метку isActive=true
     * записывает id сотрудника, который взял в работу файл
     * @param fileId
     * @param employeeId
     * @return
     */
//    public byte[] updateFile(UUID fileId, UUID employeeId) {
//
//        FileToDataBaseDto fileToDataBaseDto = getFileById(fileId);
//        FileToDataBase fileToDataBase = fileToDataBaseMapper.toEntity(fileToDataBaseDto);
//
//        fileToDataBase.setIsActive(true);
//        fileToDataBase.setEmployee(employeeMapper.toEntity(employeeService.getEmployeeById(employeeId)));
//
//        fileToDataBaseRepository.save(fileToDataBase);
//
////        getFileById(fileId)
////                .setIsActive(true);
////        getFileById(fileId)
////                .setEmployee(employeeMapper.toEntity(employeeService.getEmployeeById(employeeId)));
//        downloadFile(fileId);
//    }

    /**
     * получение строки по разделителю
     * на вход принимает строку и символ по которому разделить
     * используется для:
     * 1. получения формата файла для таблицы file_entity
     * 2. получения имени и формата файла при его копировании
     *
     * @param nameFile
     * @param delimiter
     * @return
     */
    private String formatFile(String nameFile, String delimiter) {

        int index = nameFile.lastIndexOf(delimiter);

        return nameFile.substring(index + 1);
    }

    /**
     * записывает файл
     *
     * @param path
     * @param multipartFile
     */
    private void writeToFile(String path, MultipartFile multipartFile) {

        try (FileOutputStream fos = new FileOutputStream(path)) {
            byte[] contentFile = multipartFile.getBytes();
            fos.write(contentFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * возвращает список файлов
     *
     * @return
     */
    public List<FileToDataBaseDto> getAllFiles() {

        List<FileToDataBaseDto> filesDto = fileToDataBaseRepository.findAll().stream()
                .map(fileToDataBaseMapper::toDto)
                .collect(Collectors.toList());

        return filesDto;
    }
}
