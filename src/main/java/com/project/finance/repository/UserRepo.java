package com.project.finance.repository;

import com.project.finance.dto.User;
import com.project.finance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    public Optional<User> findByName(String name);

}
