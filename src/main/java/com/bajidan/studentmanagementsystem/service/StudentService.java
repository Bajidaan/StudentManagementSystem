package com.bajidan.studentmanagementsystem.service;


import com.bajidan.studentmanagementsystem.model.Student;
import com.bajidan.studentmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> allStudent() {
        return studentRepository.findAll();
    }

    public Optional<Student> FindStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Integer id) {
         //studentRepository.findAll().removeIf(c -> c.getId().equals(id));
         studentRepository.deleteById(id);
    }

    public boolean existById(Integer id) {
        return studentRepository.findAll()
                .stream()
                .filter(c -> c.getId().equals(id))
                .count() == 1;
    }



}
