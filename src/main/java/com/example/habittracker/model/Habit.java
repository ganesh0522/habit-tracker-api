package com.example.habittracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "habits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Habit name cannot be empty")
    @Column(nullable = false)
    private String name;

    private String description;

    private LocalDate createdDate = LocalDate.now();

    @OneToMany(mappedBy = "habit",
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HabitEntry> entries;
}