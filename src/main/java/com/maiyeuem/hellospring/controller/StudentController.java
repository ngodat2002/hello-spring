package com.maiyeuem.hellospring.controller;

import com.maiyeuem.hellospring.entity.Student;
import com.maiyeuem.hellospring.repository.StudentRepository;
import com.maiyeuem.hellospring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * http://localhost:8080/api/v1/students | GET | return list student
 * http://localhost:8080/api/v1/students | POST | create new student
 * http://localhost:8080/api/v1/students | DELETE | remove student
 * http://localhost:8080/api/v1/students/1 | GET | find student by id
 * http://localhost:8080/api/v1/students/1 | PUT | update student
 */
@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    @Autowired
    StudentService studentService;
    //GET-list students
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getList(){
        return ResponseEntity.ok(studentService.findAll());
    }
    //GET-list students by id
    @RequestMapping(method = RequestMethod.GET, path = "{studentId}")
    public ResponseEntity<?> getDetail(@PathVariable Long studentId){
        Optional<Student> optionalStudent = studentService.findById(studentId);//? la bat ky the loai nao cung co the nhan
        if (!optionalStudent.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalStudent.get());
    }
    //POST- students
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student));
    }

    //DELETE- students
    @RequestMapping(method = RequestMethod.DELETE, path = "{studentId}")
    public ResponseEntity<?> delete(@PathVariable Long studentId) {//? la bat ky the loai nao cung co the nhan
        if (!studentService.findById(studentId).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        studentService.deleteById(studentId);
        return ResponseEntity.ok().build();
    }

    //UPDATE students by id
    @RequestMapping(method = RequestMethod.PUT, path = "{studentId}")
    public ResponseEntity<Student> update(@PathVariable Long studentId, @RequestBody Student student) {
        Optional<Student> optionalAccount = studentService.findById(studentId);
        if (!optionalAccount.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Student existAccount = optionalAccount.get();
        existAccount.setFullName(student.getFullName());
        existAccount.setPhone(student.getPhone());
        return ResponseEntity.ok(studentService.save(existAccount));
    }

}
