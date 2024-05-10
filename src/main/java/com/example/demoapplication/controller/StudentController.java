package com.example.demoapplication.controller;

import com.example.demoapplication.domain.Student;
import com.example.demoapplication.service.StudentService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@CrossOrigin(origins="*",allowedHeaders = "*")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/")

    public String viewHomePage(Model model) {
        List<Student> liststudent = service.listAll();
        System.out.println(liststudent.size());
        model.addAttribute("liststudent", liststudent);
        System.out.println("Get / ");
        JSONArray JSA = new JSONArray();
        for(Student ST:liststudent){
            JSONObject JSO = new JSONObject();
            JSO.put("Student Name",ST.getStudentname());
            JSA.put(JSO);
        }

        Gson Gs = new Gson();
        String GsonString=Gs.toJson(JSA);
        System.out.println(GsonString);
        return "liststudent";
    }



    @GetMapping("/SourceAll")
    public ResponseEntity<List<Student>> LaodAll() {
        List<Student> liststudent = service.listAll();
        System.out.println(liststudent.size());


        JSONArray JSA = new JSONArray();
        for(Student ST:liststudent){
            JSONObject JSO = new JSONObject();
            JSO.put("Student Name",ST.getStudentname());
            JSA.put(JSO);
        }

        Gson Gs = new Gson();
        String GsonString=Gs.toJson(JSA);
        System.out.println(GsonString);
        return ResponseEntity.ok(liststudent);
    }



    @GetMapping("/new")
    public ResponseEntity add(Model model) {
        model.addAttribute("student", new Student());
        return ResponseEntity.ok("new");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveStudent(@ModelAttribute("student") Student std) {
        service.save(std);
        System.out.print("Inside One / ");
        return ResponseEntity.ok("redirect:SuccessFullMsg.html");
    }

    @RequestMapping("/edit/{id}")
    public ResponseEntity<ModelAndView> showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Student std = service.get(id);
        mav.addObject("student", std);
        return ResponseEntity.ok(mav);

    }
    @RequestMapping("/delete/{id}")
    public ResponseEntity<String> deletestudent(@PathVariable(name = "id") int id) {
        service.delete(id);
        return ResponseEntity.ok("redirect:/");
    }

}
