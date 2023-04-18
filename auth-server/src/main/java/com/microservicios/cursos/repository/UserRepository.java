package com.microservicios.cursos.repository;

import com.microservicios.cursos.model.dto.UserDto;
import com.microservicios.cursos.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String name);
}
