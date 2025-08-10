package com.project.finance.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private Long userId;

    private String name;

    private String email;

    private String password;

    private Timestamp createdAt;
}
