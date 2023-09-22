package com.example.fullstackapp.controller;

import com.example.fullstackapp.exception.UserNotFoundException;
import com.example.fullstackapp.model.User;
import com.example.fullstackapp.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserContr {


    @Autowired
    private UserRepos userRepos;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepos.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepos.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserId(@PathVariable Long id){
        return userRepos.findById(id).orElseThrow(()-> new UserNotFoundException(id));

    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepos.findById((id)).map(user->{
            user.setUsername(newUser.getUsername());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return userRepos.save(user);
        }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser( @PathVariable Long id){
        if(!userRepos.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepos.deleteById(id);

        return "user with id : "+id+" has been deleted success";
    }



}
