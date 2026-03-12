package com.example.habittracker.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;

@Entity
@Table(name = "habit_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HabitEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    @JsonBackReference
    private Habit habit;
}