package com.example.demoapplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/Welcome")
    public String welcome(){
        return "Weclome to the Dummy Application";
    }
}
