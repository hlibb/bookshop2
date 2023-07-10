package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/user-api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // admin api

    @PostMapping("/add")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        var savingResponse = userService.save(user);
        return ResponseEntity.ok(savingResponse);
    }

    @GetMapping("/find-by-name-contain")
    public ResponseEntity<List<User>> getAllUsersContain(@RequestParam("n") String name) {
        var requestedName = name.replaceAll("[+]", " ");
        return ResponseEntity.ok(userService.findByNameContaining(requestedName));
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<User> getById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/find-all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/find-by-name/")
    public ResponseEntity<User> getUsersByName(@RequestParam("n") String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

    @GetMapping("/find-by-surname")
    public ResponseEntity<List<User>> getUsersBySurname(@RequestParam("s") String surname) {
        return ResponseEntity.ok(userService.findBySurname(surname));
    }

    @GetMapping("/find-by-age")
    public ResponseEntity<List<User>> getUsersByAge(@RequestParam("a") short age) {
        return ResponseEntity.ok(userService.findByAge(age));
    }

    @GetMapping("/find-by-city")
    public ResponseEntity<List<User>> getUserByCity(@RequestParam(value = "c") String city) {
        var requestedCity = city.replaceAll("[+]", " ");
        return ResponseEntity.ok(userService.findByCity(requestedCity));
    }

    // registration

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }
}

