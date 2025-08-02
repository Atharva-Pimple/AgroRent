package com.majorproj.agrorent.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majorproj.agrorent.services.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {
	
	private final PaymentService paymentService;
	
	@PostMapping("/create-payment/{bookingId}")
	public ResponseEntity<?> createPayment(@PathVariable Long bookingId){
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(bookingId));
		
	}
}
