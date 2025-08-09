package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public List<User> findUsersByName(@RequestParam("name") String name) {
        return userService.findAllByName(name);
    }

    @DeleteMapping
    public void deleteByName(@RequestParam("name") String name) {
        userService.deleteByName(name);
    }

    @GetMapping("/{id}")
    public void updateName(@PathVariable("id") int id, @RequestParam("name") String name) {
        userService.updateNameById(id, name);
    }

    @GetMapping
    public List<User> getAll(@RequestParam int offset, @RequestParam int limit) {
        return userService.getAll(offset, limit);
    }
}
