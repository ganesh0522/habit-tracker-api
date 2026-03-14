package com.example.habittracker.controller;

import com.example.habittracker.dto.HabitRequestDTO;
import com.example.habittracker.model.Habit;
import com.example.habittracker.model.HabitEntry;
import com.example.habittracker.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public ResponseEntity<Habit> createHabit(
            @Valid @RequestBody HabitRequestDTO dto) {

        Habit createdHabit = habitService.createHabit(dto);
        return ResponseEntity.ok(createdHabit);
    }

    // 2️⃣ Get All Habits (Pagination + Sorting)
    @GetMapping
    public ResponseEntity<Page<Habit>> getAllHabits(Pageable pageable) {

        Page<Habit> habits = habitService.getAllHabits(pageable);
        return ResponseEntity.ok(habits);
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<String> markCompleted(@PathVariable Long id) {

        habitService.markCompleted(id);
        return ResponseEntity.ok("Habit marked as completed for today");
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<Page<HabitEntry>> getHabitHistory(
            @PathVariable Long id,
            Pageable pageable) {

        Page<HabitEntry> history = habitService.getHabitHistory(id, pageable);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{id}/weekly-progress")
    public ResponseEntity<Map<String, Object>> getWeeklyProgress(
            @PathVariable Long id) {

        Map<String, Object> progress =
                habitService.getWeeklyProgress(id);

        return ResponseEntity.ok(progress);
    }
}
