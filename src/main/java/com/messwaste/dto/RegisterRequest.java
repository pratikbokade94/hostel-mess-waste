package com.messwaste.dto;

import com.messwaste.entity.User;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private User.Role role;
    private String roomNo;
}