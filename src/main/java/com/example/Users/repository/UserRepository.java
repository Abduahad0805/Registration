package com.example.Users.repository;

import com.example.Users.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    boolean existsByEmailAndUserName(@NotNull(message = "Email is required") @Email(message = "Email is not valid") String email, @NotNull(message = "User name is required") @Size(min = 3, max = 30) String userName);

    void deleteByEmail(String email);
}
