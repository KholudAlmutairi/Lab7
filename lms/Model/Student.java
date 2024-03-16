package com.example.lms.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {

    @NotNull(message = "ID cannot be null ")
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 2)
    private String name;

    @PositiveOrZero(message = "Age must be positive or zero")
    private int age;

    private ArrayList<Course> registeredCourses;

    private boolean attended;
    private boolean passed;





}
