package com.example.Users.queue.dto;

import com.example.Users.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    @NotNull(message = "User name is required")
    @Size(min = 3, max = 30)
    String userName;

    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid")
    String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    String password;

    @NotNull(message = "Role is required. Role can be USER or ADMIN")
    Role role;
}
