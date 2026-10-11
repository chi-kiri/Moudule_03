package com.example.course_management.controller;

import com.example.course_management.model.Instructor;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAll() {
        List<Instructor> instructors = instructorService.getAllInstructors();

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy danh sách giảng viên thành công", instructors)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getById(@PathVariable int id) {
        Instructor instructor = instructorService.getInstructorById(id);

        if (instructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy giảng viên", null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy giảng viên thành công", instructor)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> create(
            @RequestBody Instructor instructor) {

        Instructor created = instructorService.createInstructor(instructor);

        if (created == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Dữ liệu không hợp lệ", null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Tạo giảng viên thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> update(
            @PathVariable int id,
            @RequestBody Instructor instructor) {

        Instructor updated = instructorService.updateInstructor(id, instructor);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy giảng viên", null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Cập nhật giảng viên thành công", updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> delete(@PathVariable int id) {
        Instructor deleted = instructorService.deleteInstructorById(id);

        if (deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Không tìm thấy giảng viên", null));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Xóa giảng viên thành công", deleted)
        );
    }
}