package com.example.validation.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionDetails {
    private String host;
    private int port;
    private String username;
    private String password;
}
