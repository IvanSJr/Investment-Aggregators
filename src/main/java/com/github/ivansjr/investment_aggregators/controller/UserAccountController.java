package com.github.ivansjr.investment_aggregators.controller;

import com.github.ivansjr.investment_aggregators.service.UserAccountService;
import com.github.ivansjr.investment_aggregators.service.dto.UserAccountCreateRequestDTO;
import com.github.ivansjr.investment_aggregators.service.dto.UserAccountResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-accounts")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> createAccountToUser(@PathVariable("userId") UUID userId,
                                                    @RequestBody UserAccountCreateRequestDTO userAccountCreateRequestDTO) {
        userAccountService.createAccount(userId, userAccountCreateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserAccountResponseDTO>> listAccountsToUser(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok().body(userAccountService.listUserAccountsByIdUser(userId));
    }

}
