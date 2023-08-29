package com.bajidan.studentmanagementsystem.controller;

import com.bajidan.studentmanagementsystem.model.Student;
import com.bajidan.studentmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

//    @GetMapping("student")
//    public ModelAndView tableD(Model model) {
//        model.addAttribute("key", "My Java skills is improving");
//
//        return new ModelAndView("student");
//    }

    @GetMapping
    public ModelAndView getAllStudent(Model model){
        model.addAttribute("studentView", studentService.allStudent());
        //return new ResponseEntity<List<Student>>(studentService.allStudent(), HttpStatus.OK);
        return new ModelAndView("student");
    }

    @GetMapping("/create")
    public ModelAndView create(Student student, Model model) {


        model.addAttribute("studentForm", new Student());
        return new ModelAndView("createStudent");
    }

    //Create
    @PostMapping("/addStudent")
    public ModelAndView createStudent(@Valid Student student, BindingResult bindingResult, Model model) {
        student.setId(null);
        //return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);

        studentService.saveStudent(student);
        List<Student> studentList = studentService.allStudent();

        model.addAttribute("studentView", studentList);
        return new ModelAndView("student");
    }

    //Read
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Integer id) {
        return new ResponseEntity<Optional<Student>>(studentService.FindStudentById(id), HttpStatus.OK);
    }

    //Update
    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        if(!studentService.existById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        studentService.saveStudent(student);
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.delete(id);
    }
}
