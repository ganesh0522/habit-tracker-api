package com.example.habittracker.repository;

import com.example.habittracker.model.HabitEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitEntryRepository extends JpaRepository<HabitEntry, Long> {

    Optional<HabitEntry> findByHabitIdAndDate(Long habitId, LocalDate date);

    List<HabitEntry> findByHabitIdAndDateBetween(
            Long habitId, LocalDate startDate, LocalDate endDate);
}