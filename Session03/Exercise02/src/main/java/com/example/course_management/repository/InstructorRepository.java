package com.example.course_management.repository;

import com.example.course_management.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {

    private final List<Instructor> instructors = new ArrayList<>(List.of(
            new Instructor(1, "Nguyen Van A", "a@gmail.com"),
            new Instructor(2, "Tran Van B", "b@gmail.com")
    ));

    public List<Instructor> findAll() {
        return instructors;
    }

    public Instructor findById(int id) {
        for (Instructor instructor : instructors) {
            if (instructor.getId() == id) {
                return instructor;
            }
        }
        return null;
    }

    public Instructor create(Instructor instructor) {
        instructors.add(instructor);
        return instructor;
    }

    public Instructor update(int id, Instructor newInstructor) {
        Instructor instructor = findById(id);

        if (instructor == null) {
            return null;
        }

        instructor.setName(newInstructor.getName());
        instructor.setEmail(newInstructor.getEmail());

        return instructor;
    }

    public Instructor deleteById(int id) {
        Instructor instructor = findById(id);

        if (instructor != null) {
            instructors.remove(instructor);
        }

        return instructor;
    }
}