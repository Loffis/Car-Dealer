package se.ecutb.cardealer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.ecutb.cardealer.entities.User;
import se.ecutb.cardealer.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<User>> findAllUser(@RequestParam(required = false) String name, boolean sort){
        return ResponseEntity.ok(userService.findAll(name, sort));
    }
    @Secured({"ROLE_DEALER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<User> saveUser(@Validated @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser (@PathVariable String id, @Validated @RequestBody User user){
        userService.update(id, user);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }
}
