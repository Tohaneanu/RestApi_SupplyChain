package com.example.projectfirsttry.controllers;

import com.example.projectfirsttry.entities.User;
import com.example.projectfirsttry.repository.UserRepository;
import com.example.projectfirsttry.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class AdminJobController {

    private final UserRepository userRepository;
    private final UserService userService;

//    @GetMapping(path = "/admin/users",produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<User> getUsers(){
//        return this.userRepository.findAll();
//    }
 @GetMapping("/user/{userId}")
public ResponseEntity<User> getUserById(@PathVariable int userId) {
     User userById = userService.getUserById(userId);
    return new ResponseEntity<>(userById, HttpStatus.OK);
}

}
