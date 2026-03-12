package com.example.habittracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HabitRequestDTO {

    @NotBlank(message = "Habit name is required")
    @Size(min = 3, max = 50, message = "Habit name must be between 3 and 50 characters")
    private String name;

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;
}