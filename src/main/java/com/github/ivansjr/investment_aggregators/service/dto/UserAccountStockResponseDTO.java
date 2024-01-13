package com.github.ivansjr.investment_aggregators.service.dto;

import com.github.ivansjr.investment_aggregators.entity.Stock;

import java.util.UUID;

public record UserAccountStockResponseDTO(UUID accountId, Stock stock, Double total) {
}
