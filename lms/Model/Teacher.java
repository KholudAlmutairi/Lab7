package com.example.lms.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {


    @NotNull(message = "ID cannot be null")
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Email
    private String email;


    @NotNull(message = "Salary should not be empty")
    private Double salary;
}
