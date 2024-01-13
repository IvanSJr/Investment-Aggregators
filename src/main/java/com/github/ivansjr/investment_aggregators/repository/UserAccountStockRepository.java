package com.github.ivansjr.investment_aggregators.repository;

import com.github.ivansjr.investment_aggregators.entity.UserAccountStock;
import com.github.ivansjr.investment_aggregators.entity.UserAccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountStockRepository extends JpaRepository<UserAccountStock, UserAccountStockId> {
}
