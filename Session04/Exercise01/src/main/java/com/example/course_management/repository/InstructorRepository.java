package com.example.course_management.repository;

import com.example.course_management.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstructorRepository {

    private final List<Instructor> instructors = new ArrayList<>(List.of(
            new Instructor(1L, "Nguyen Van A", "a@gmail.com"),
            new Instructor(2L, "Tran Van B", "b@gmail.com")
    ));

    public List<Instructor> findAll() {
        return instructors;
    }

    public Optional<Instructor> findById(long id) {
        return instructors.stream()
                .filter(instructor -> instructor.getId().equals(id))
                .findFirst();
    }

    public Instructor create(Instructor instructor) {
        instructors.add(instructor);
        return instructor;
    }

    public Instructor update(long id, Instructor newInstructor) {
        Instructor instructor = findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        instructor.setName(newInstructor.getName());
        instructor.setEmail(newInstructor.getEmail());

        return instructor;
    }

    public Instructor deleteById(long id) {
        Instructor instructor = findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        instructors.remove(instructor);
        return instructor;
    }
}