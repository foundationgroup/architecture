package com.tkp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tkp.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
