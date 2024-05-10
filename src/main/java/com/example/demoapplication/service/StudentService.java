package com.example.demoapplication.service;

import com.example.demoapplication.domain.Student;
import com.example.demoapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository REPO;

    public List<Student> listAll(){
        return REPO.findAll();
    }

    public void save(Student S){
       REPO.save(S);
    }

    public Student get(long id){
        return REPO.findById(id).get();
    }

    public void delete(long id){
         REPO.deleteById(id);
    }
}
