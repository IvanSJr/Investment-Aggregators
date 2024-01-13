package com.github.ivansjr.investment_aggregators.controller;

import com.github.ivansjr.investment_aggregators.service.UserAccountStockService;
import com.github.ivansjr.investment_aggregators.service.dto.UserAccountStockCreateRequestDTO;
import com.github.ivansjr.investment_aggregators.service.dto.UserAccountStockResponseDTO;
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
@RequestMapping("api/v1/account-stocks")
public class UserAccountStockController {

    private final UserAccountStockService userAccountStockService;

    public UserAccountStockController(UserAccountStockService userAccountStockService) {
        this.userAccountStockService = userAccountStockService;
    }

    @PostMapping("/{accountId}")
    public ResponseEntity<Void> relatedStockWithAccount(@PathVariable("accountId") UUID accountId,
                                                        @RequestBody UserAccountStockCreateRequestDTO userAccountStockCreateRequestDTO) {
        userAccountStockService.relatedStockWithAccount(accountId, userAccountStockCreateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<UserAccountStockResponseDTO>> getAllRelatedStocksByAccount(@PathVariable("accountId") UUID accountId) {
        return ResponseEntity.ok().body(userAccountStockService.getAllRelatedStocksByAccount(accountId));
    }
}
