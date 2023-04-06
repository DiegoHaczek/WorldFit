package com.C10G14.WorldFitBackend.controller;


import com.C10G14.WorldFitBackend.dto.Exercise_RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.RoutineResponseDto;
import com.C10G14.WorldFitBackend.service.RoutineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @Operation(summary = "Create a new routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new routine",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<RoutineResponseDto> createRoutine(@RequestBody RoutineRequestDto routine) {
        RoutineResponseDto createdRoutine = routineService.createRoutine(routine);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoutine);
    }

    @Operation(summary = "Get all routines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of routines or a empty list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<RoutineResponseDto>> getAllRoutines() {
        List<RoutineResponseDto> routines = routineService.getAllRoutines();
        return ResponseEntity.ok(routines);
    }

    @Operation(summary = "Get a routine by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A routine",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: Routine don't exist",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<RoutineResponseDto> getRoutineById(@PathVariable Long id) {
        RoutineResponseDto routine = routineService.getRoutineById(id);
        return ResponseEntity.ok(routine);
    }

    @Operation(summary = "Update an existing routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A updated routine",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: Routine don't exist",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<RoutineResponseDto> updateRoutine(@PathVariable Long id, @RequestBody RoutineRequestDto routine) {
        //routine.setId(id);
        RoutineResponseDto updatedRoutine = routineService.updateRoutine(id, routine);
        return ResponseEntity.ok(updatedRoutine);
    }

    @Operation(summary = "Delete a routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error: Routine don't exist",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return new ResponseEntity<>("Routine successfully deleted",HttpStatus.OK);
    }

    @Operation(summary = "Add a exercise to a routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A routine with the new exercise added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Cannot add empty exercise",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PostMapping("/{id}/exercises")
    public ResponseEntity<RoutineResponseDto> addExercise(@PathVariable("id") long routineId,
                                                  @RequestBody Exercise_RoutineRequestDto exercise) {
        RoutineResponseDto updatedRoutine = routineService.addExercise(routineId,exercise);
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }

    @Operation(summary = "Update a exercise in a existing routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A routine with a updated exercise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Cannot add empty exercise",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PutMapping("/{id}/exercises")
    public ResponseEntity<RoutineResponseDto> updateExercise(@PathVariable("id") long routineId,
                                                  @RequestBody Exercise_RoutineRequestDto exercise) {
        RoutineResponseDto updatedRoutine = routineService.updateExercise(routineId,exercise);
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }

    @Operation(summary = "Remove a exercise from a existing routine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A routine without the removed exercise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoutineController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: exercise does not exist in this routine",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @DeleteMapping("/{id}/exercises")
    public ResponseEntity<RoutineResponseDto> removeExercise(@PathVariable("id") long routineId,
                                                  @RequestBody Map <String,Long> exerciseId) {
        RoutineResponseDto updatedRoutine = routineService.removeExercise(routineId,exerciseId.get("exerciseId"));
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }
}
