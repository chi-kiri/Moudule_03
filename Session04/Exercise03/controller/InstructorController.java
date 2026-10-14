package com.example.course_management.controller;

import com.example.course_management.dto.InstructorCreateRequest;
import com.example.course_management.model.Instructor;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAll() {
        List<Instructor> instructors = instructorService.findAllInstructors();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách giảng viên thành công",
                        instructors
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getById(
            @PathVariable Long id) {

        try {
            Instructor instructor = instructorService.findInstructorById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Lấy giảng viên thành công",
                            instructor
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> create(
            @RequestBody InstructorCreateRequest req) {

        Instructor created = instructorService.createInstructor(req);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Tạo giảng viên thành công",
                                created
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> update(
            @PathVariable Long id,
            @RequestBody InstructorCreateRequest req) {

        try {
            Instructor newInstructor = new Instructor();
            newInstructor.setName(req.getName());
            newInstructor.setEmail(req.getEmail());

            Instructor updated =
                    instructorService.updateInstructor(id, newInstructor);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Cập nhật giảng viên thành công",
                            updated
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> delete(
            @PathVariable Long id) {

        try {
            Instructor deleted = instructorService.findInstructorById(id);

            instructorService.deleteInstructor(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Xóa giảng viên thành công",
                            deleted
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }
}