package com.github.ivansjr.investment_aggregators.controller;

import com.github.ivansjr.investment_aggregators.service.StockService;
import com.github.ivansjr.investment_aggregators.service.dto.StockCreateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody StockCreateRequestDTO stockCreateRequestDTO) {
        stockService.create(stockCreateRequestDTO);
        return ResponseEntity.ok().build();
    }
}
