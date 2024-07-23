package com.panda.repository;

import com.panda.model.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HumanRepository extends JpaRepository<Human, UUID> {
}
