package com.github.ivansjr.investment_aggregators.service;

import com.github.ivansjr.investment_aggregators.entity.Stock;
import com.github.ivansjr.investment_aggregators.repository.StockRepository;
import com.github.ivansjr.investment_aggregators.service.dto.StockCreateRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void create(StockCreateRequestDTO stockCreateRequestDTO) {
        Stock stock = new Stock(stockCreateRequestDTO.stockId(), stockCreateRequestDTO.description());
        stockRepository.save(stock);
    }
}
