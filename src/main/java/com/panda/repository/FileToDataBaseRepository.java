package com.panda.repository;

import com.panda.model.FileToDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileToDataBaseRepository extends JpaRepository<FileToDataBase, UUID> {

    FileToDataBase findByNameFile(String nameFile);
}
