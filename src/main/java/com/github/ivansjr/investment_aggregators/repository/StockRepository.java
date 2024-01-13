package com.github.ivansjr.investment_aggregators.repository;

import com.github.ivansjr.investment_aggregators.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
