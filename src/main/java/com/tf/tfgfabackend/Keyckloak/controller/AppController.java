package com.tf.tfgfabackend.Keyckloak.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

  @PostMapping("/users")
  public String users() {
    System.out.print("users");
    return "Get user access";
  }
  @PostMapping("/api/v1/users/account")
  public String usersInsert() {
    System.out.print("users");
    return "usersInsert";
  }
  @PostMapping("/api/v1/services/gestionnaire")
  public String gestionnaire() {
    return "Hello services";
  }
  @PostMapping("/api/v1/sites/admin")
  public String admin() {
    return "Hello sites";
  }

  @PostMapping("/api/v1/statistics/superadmin")
  public String superadmin() {
    return "Hello superadmin";
  }

}
