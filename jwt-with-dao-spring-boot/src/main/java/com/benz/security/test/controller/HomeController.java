package com.benz.security.test.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/home")
public class HomeController {


    @GetMapping("/all")
    public String all()
    {
        return "Welcome All";
    }

    @GetMapping("/user")
    public String user()
    {
        return "Welcome User";
    }

    @GetMapping("/admin")
    public String admin()
    {
        return "Welcome Admin";
    }
}
