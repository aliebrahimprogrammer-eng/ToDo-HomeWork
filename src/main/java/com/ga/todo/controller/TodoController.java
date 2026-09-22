package com.ga.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class TodoController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

}
