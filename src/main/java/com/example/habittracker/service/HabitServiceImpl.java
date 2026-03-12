package com.example.habittracker.service;

import com.example.habittracker.dto.HabitRequestDTO;
import com.example.habittracker.exception.DuplicateEntryException;
import com.example.habittracker.exception.HabitNotFoundException;
import com.example.habittracker.model.Habit;
import com.example.habittracker.model.HabitEntry;
import com.example.habittracker.repository.HabitEntryRepository;
import com.example.habittracker.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final HabitEntryRepository habitEntryRepository;

    // 1️⃣ Create Habit
    @Override
    public Habit createHabit(HabitRequestDTO dto) {

        Habit habit = new Habit();
        habit.setName(dto.getName());
        habit.setDescription(dto.getDescription());

        return habitRepository.save(habit);
    }

    // 2️⃣ Get All Habits (Pagination)
    @Override
    public Page<Habit> getAllHabits(Pageable pageable) {
        return habitRepository.findAll(pageable);
    }

    // 3️⃣ Mark Habit as Completed
    @Override
    public void markCompleted(Long habitId) {

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() ->
                        new HabitNotFoundException("Habit not found with id: " + habitId));

        LocalDate today = LocalDate.now();

        if (habitEntryRepository
                .findByHabitIdAndDate(habitId, today)
                .isPresent()) {

            throw new DuplicateEntryException("Habit already marked for today");
        }

        HabitEntry entry = new HabitEntry();
        entry.setDate(today);
        entry.setCompleted(true);
        entry.setHabit(habit);

        habitEntryRepository.save(entry);
    }

    // 4️⃣ Get Habit History
    @Override
    public Page<HabitEntry> getHabitHistory(Long habitId, Pageable pageable) {

        if (!habitRepository.existsById(habitId)) {
            throw new HabitNotFoundException("Habit not found with id: " + habitId);
        }

        return habitEntryRepository.findAll(pageable);
    }

    // 5️⃣ Weekly Progress Calculation
    @Override
    public Map<String, Object> getWeeklyProgress(Long habitId) {

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() ->
                        new HabitNotFoundException("Habit not found with id: " + habitId));

        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.minusDays(6);

        List<HabitEntry> entries =
                habitEntryRepository.findByHabitIdAndDateBetween(
                        habitId, weekStart, today);

        long completedDays = entries.stream()
                .filter(HabitEntry::isCompleted)
                .count();

        double percentage = (completedDays / 7.0) * 100;

        Map<String, Object> response = new HashMap<>();
        response.put("habitName", habit.getName());
        response.put("completedDays", completedDays);
        response.put("progressPercentage", percentage);

        return response;
    }
}