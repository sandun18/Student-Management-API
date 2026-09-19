package com.example.studentapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>();

    // Initial students
    public StudentController() {
        students.add(new Student(1, "Kamal", "kamal@gmail.com"));
        students.add(new Student(2, "Nimal", "nimal@gmail.com"));
        students.add(new Student(3, "Sunil", "sunil@gmail.com"));
    }

    // GET - Get first student
    @GetMapping("/student")
    public Student getStudent() {
        return students.get(0);
    }

    // GET - Get all students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    // GET - Get student by ID
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {

        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    // POST - Add new student
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    // PUT - Update student
    @PutMapping("/students/{id}")
    public Student updateStudent(
            @PathVariable int id,
            @RequestBody Student updatedStudent) {

        for (Student student : students) {

            if (student.getId() == id) {
                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());

                return student;
            }
        }

        return null;
    }

    // DELETE - Delete student
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                students.remove(student);
                return "Student deleted successfully";
            }
        }

        return "Student not found";
    }
}