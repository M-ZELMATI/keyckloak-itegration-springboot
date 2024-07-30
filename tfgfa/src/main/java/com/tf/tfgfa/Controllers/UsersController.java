package com.tf.tfgfa.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tfgfa")
public class UsersController {

    @GetMapping("/users")
    public String getUsers(){
        return "User List";
    }
    
}
