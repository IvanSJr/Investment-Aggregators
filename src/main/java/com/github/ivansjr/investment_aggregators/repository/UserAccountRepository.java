package com.github.ivansjr.investment_aggregators.repository;

import com.github.ivansjr.investment_aggregators.entity.User;
import com.github.ivansjr.investment_aggregators.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
}
