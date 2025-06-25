package com.sanket.tender_management.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
