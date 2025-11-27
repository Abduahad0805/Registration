package com.example.Users.controller;

import com.example.Users.entity.Users;
import com.example.Users.queue.dto.UserDTO;
import com.example.Users.service.domain.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmai(email), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Users> saveUser(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) {

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }


        String userAgent = request.getHeader("User-Agent");

        return new ResponseEntity<>(userService.saveUser(userDTO, ipAddress, userAgent), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUserByEmail(@RequestBody String email){
        userService.deletUserByEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);
    }
}
