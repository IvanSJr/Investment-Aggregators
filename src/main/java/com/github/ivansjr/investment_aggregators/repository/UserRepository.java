package com.github.ivansjr.investment_aggregators.repository;

import com.github.ivansjr.investment_aggregators.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
