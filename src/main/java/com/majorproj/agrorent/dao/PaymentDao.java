package com.majorproj.agrorent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majorproj.agrorent.entities.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long> {

}
