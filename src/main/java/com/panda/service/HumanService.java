package com.panda.service;

import com.panda.dto.AddressDto;
import com.panda.dto.EmployeeDto;
import com.panda.dto.HumanDto;
import com.panda.mapper.EmployeeMapper;
import com.panda.enums.City;
import com.panda.enums.Gender;
import com.panda.mapper.HumanMapper;
import com.panda.model.Address;
import com.panda.model.Employee;
import com.panda.model.Human;
import com.panda.repository.HumanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HumanService {

    private final HumanRepository humanRepository;
    private final HumanMapper humanMapper;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    /**
     * создание человека по умолчанию
     * тестовый метод
     */
    public void addDefault() {

        Human human = Human.builder()
                .name("ivan")
                .surname("ivanov")
                .patronymic("ivanovich")
                .birthDay(LocalDate.of(1987, 3, 13))
                .email("ivan@yandex.ru")
                .gender(Gender.MALE)
                .numberPhone("8-800-300-33-33")
                .address(Address.builder()
                        .city(City.KZN.getValue())
                        .street("Lenina")
                        .home(13)
                        .apartment(3)
                        .build())
                .build();

        humanRepository.save(human);
        addNewEmployee(human);
    }

    /**
     * добавление новой сущности human в БД
     * @param humanDto
     * @return
     */
    @Transactional
    public HumanDto addHuman(HumanDto humanDto) {

        AddressDto addressDto = humanDto.getAddress();

        Address address = Address.builder()
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .home(addressDto.getHome())
                .apartment(addressDto.getApartment())
                .build();

        Human human = Human.builder()
                .name(humanDto.getName())
                .surname(humanDto.getSurname())
                .patronymic(humanDto.getPatronymic())
                .birthDay(humanDto.getBirthDay())
                .email(humanDto.getEmail())
                .numberPhone(humanDto.getNumberPhone())
                .gender(humanDto.getGender())
                .employee(null)
                .address(address)
                .build();

        Employee employee = addNewEmployee(human);

        human.setEmployee(employee);

        Human savedEntity = humanRepository.save(human);
        employee.setHumanId(savedEntity.getId());

        System.out.println();

        return humanMapper.toDto(savedEntity);
    }

    /**
     * вывод списка людей
     * @return
     */
    public List<HumanDto> getAllHumansDto() {

        List<HumanDto> humanDtos = humanRepository.findAll().stream()
                .map(humanMapper::toDto)
                .collect(Collectors.toList());

        return humanDtos;
    }

    /**
     * редактирование человека
     * @param humanDto
     * @return
     */
    @Transactional
    public HumanDto updateHuman(HumanDto humanDto) {

        Human human = humanMapper.toEntity(getHumanById(humanDto.getId()));

        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(human.getEmployee().getId())
                .name(human.getName())
                .surname(human.getSurname())
                .employmentDate(LocalDate.now())
                .humanId(human.getId())
                .build();
        employeeService.updateEmployee(employeeDto);

        human.setEmployee(employeeMapper.toEntity(employeeDto));

        Human savedHuman = humanRepository.save(humanMapper.merge(humanDto, human));

        return humanMapper.toDto(savedHuman);
    }

    /**
     * получение сущности human по id
     * возвращает dto
     * @param id
     * @return
     */
    public HumanDto getHumanById(UUID id) {

        Human humanById = humanRepository.findById(id).orElseThrow();

        return humanMapper.toDto(humanById);
    }



                //доп методы

    /**
     * создание нового сотрудника параллельно с человеком
     * @param human
     */
    private Employee addNewEmployee(Human human) {

        Employee employee = Employee.builder()
                .name(human.getName())
                .surname(human.getSurname())
                .employmentDate(LocalDate.now())
                .humanId(human.getId())
                .build();

        employeeService.addEmployee(employee);

        System.out.println();
        return employee;
    }

}
