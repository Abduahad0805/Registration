package com.example.Users.exception.classes;

import lombok.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;

    private String path;

    private LocalDateTime timestamp;
}