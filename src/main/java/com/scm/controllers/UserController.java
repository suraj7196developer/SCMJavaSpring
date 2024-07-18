package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    
    // User Dashboard Page
    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        System.out.println("User Dashboard page Loading...............");
        return "user/dashboard";
    }

    // User Profile Page
    @RequestMapping(value="/profile")
    public String userProfile() {
        System.out.println("User Profile page Loading...............");
        return "user/profile";
    }
}
