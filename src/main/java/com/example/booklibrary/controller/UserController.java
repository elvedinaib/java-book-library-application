package com.example.booklibrary.controller;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.User;
import com.example.booklibrary.service.BookService;
import com.example.booklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) throws NoSuchElementException{
        try {
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>("User with id=" + id + " does not exist in our database.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public String addNewUser(@RequestBody User user){
        userService.addNewUser(user);
        return "New user is added successfully.";
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody User user) throws NoSuchElementException{
        try {
            return new ResponseEntity<>(userService.updateById(id, user), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>("User with id=" + id + " does not exist in our database so it cannot be updated.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws NoSuchElementException{
        try {
            return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("User with id=" + id + " does not exist in our database so it cannot be deleted.", HttpStatus.NOT_FOUND);
        }
    }
}
