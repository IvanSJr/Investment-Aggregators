package com.github.ivansjr.investment_aggregators.controller;

import com.github.ivansjr.investment_aggregators.entity.User;
import com.github.ivansjr.investment_aggregators.service.UserService;
import com.github.ivansjr.investment_aggregators.service.dto.UserCreateRequestDTO;
import com.github.ivansjr.investment_aggregators.service.dto.UserUpdateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        User userCreated = userService.create(userCreateRequestDTO);
        return ResponseEntity.created(URI.create("/api/v1/users/" + userCreated.getId())).body(userCreated);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        return ResponseEntity.ok().body(userService.update(id, userUpdateRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
