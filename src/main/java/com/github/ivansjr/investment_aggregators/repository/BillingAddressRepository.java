package com.github.ivansjr.investment_aggregators.repository;

import com.github.ivansjr.investment_aggregators.entity.BillingAddress;
import com.github.ivansjr.investment_aggregators.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}
