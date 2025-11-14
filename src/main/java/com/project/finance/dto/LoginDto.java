package com.project.finance.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty
    private String password;
}
