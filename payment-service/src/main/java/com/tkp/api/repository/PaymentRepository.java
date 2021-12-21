package com.tkp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tkp.api.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Payment findByOrderId(int orderId);
}

