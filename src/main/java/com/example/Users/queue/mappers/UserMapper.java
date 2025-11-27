package com.example.Users.queue.mappers;

import com.example.Users.entity.Users;
import com.example.Users.queue.dto.UserDTO;

public class UserMapper {
    public static  Users toEntity(UserDTO userDTO){
        Users user = new Users();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        return user;
    }
}
