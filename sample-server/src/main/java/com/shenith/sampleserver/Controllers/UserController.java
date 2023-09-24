package com.shenith.sampleserver.Controllers;

import com.shenith.sampleserver.Models.User;
import com.shenith.sampleserver.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/allusers")
    public ResponseEntity<List<User> >getUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/one-user/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable int id){
        return userService.getOneUser(id);
    }
    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id){
         userService.deleteUser(id);
         return id + " deleted";
    }

    @PutMapping("/update")
    public  User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }




}
