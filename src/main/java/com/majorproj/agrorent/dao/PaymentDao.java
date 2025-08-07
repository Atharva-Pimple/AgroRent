package com.majorproj.agrorent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.majorproj.agrorent.entities.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long> {
	Optional<Payment> findByBookingId(Long bookingId);
}
