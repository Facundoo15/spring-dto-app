package com.crymuzz.springdtoapplication.repository;

import com.crymuzz.springdtoapplication.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {



}
