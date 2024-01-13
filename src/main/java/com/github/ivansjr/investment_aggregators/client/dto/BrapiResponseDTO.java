package com.github.ivansjr.investment_aggregators.client.dto;

import java.util.List;

public record BrapiResponseDTO (List<StockDTO> results){}
