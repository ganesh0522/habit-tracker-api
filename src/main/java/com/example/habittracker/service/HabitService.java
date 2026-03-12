package com.example.habittracker.service;

import com.example.habittracker.dto.HabitRequestDTO;
import com.example.habittracker.model.Habit;
import com.example.habittracker.model.HabitEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface HabitService {

    Habit createHabit(HabitRequestDTO dto);

    Page<Habit> getAllHabits(Pageable pageable);

    void markCompleted(Long habitId);

    Page<HabitEntry> getHabitHistory(Long habitId, Pageable pageable);

    Map<String, Object> getWeeklyProgress(Long habitId);
}