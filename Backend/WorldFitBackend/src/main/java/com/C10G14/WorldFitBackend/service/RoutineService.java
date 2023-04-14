package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.Exercise_RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RemoveExerciseRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineResponseDto;

import java.util.List;

public interface RoutineService {

    List<RoutineResponseDto> getAllRoutines();

    RoutineResponseDto getRoutineById (long id);

    RoutineResponseDto createRoutine (RoutineRequestDto routineDto);

    RoutineResponseDto updateRoutine (long id, RoutineRequestDto routineDto);
    void deleteRoutine (long id);
    RoutineResponseDto addExercise(long routineId, Exercise_RoutineRequestDto exerciseId);

    RoutineResponseDto updateExercise(long routineId, Exercise_RoutineRequestDto exercise);
    RoutineResponseDto removeExercise(long routineId, RemoveExerciseRequestDto exerciseId);
}
