package com.maiyeuem.hellospring.service;

import com.maiyeuem.hellospring.entity.Student;
import com.maiyeuem.hellospring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student account) {
        return studentRepository.save(account);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

}
