package com.example.Users.service.domain;

import com.example.Users.config.hashPassword.PasswordEncode;
import com.example.Users.entity.Users;
import com.example.Users.exception.exceptions.NotFoundException;
import com.example.Users.exception.exceptions.NotSaveInDb;
import com.example.Users.exception.exceptions.UserFoundException;
import com.example.Users.queue.dto.UserDTO;
import com.example.Users.queue.mappers.UserMapper;
import com.example.Users.queue.producer.WalletProducer;
import com.example.Users.repository.UserRepository;

import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncode passwordEncode;

    @Transactional
    public Users saveUser(UserDTO userDTO, String ip, String devace ) {
        if (userRepository.existsByEmailAndUserName(userDTO.getEmail(), userDTO.getUserName())) {
            throw new UserFoundException("User already exists");
        }
        Users user = UserMapper.toEntity(userDTO);
        user.setPassword(passwordEncode.argon2PasswordEncoder().encode(userDTO.getPassword()));
        user.setIp(ip);
        user.setDevice(devace);
        Users savedUser = userRepository.save(user);

//        try {
//            WalletProducer.sendWallet(userDTO.getUserName());
//        }catch (NotSaveInDb ex){
//            throw new NotSaveInDb("Ошибка при отправка через продюсер");
//        }
        return savedUser;
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Users getUserByEmai(String email){
        return userRepository.findByEmail(email);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deletUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public Users updateUser(Long id, UserDTO userDTO) {
        Users user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        if (user != null) {
            user.setUserName(userDTO.getUserName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncode.argon2PasswordEncoder().encode(userDTO.getPassword()));
            user.setRole(userDTO.getRole());
            return userRepository.save(user);
        }
        return null;
    }

}
