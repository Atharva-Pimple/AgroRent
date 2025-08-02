package com.majorproj.agrorent.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majorproj.agrorent.dto.BookingReqDto;
import com.majorproj.agrorent.services.BookingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {
	private final BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<?> createNewBooikng(@RequestBody BookingReqDto dto,Authentication authentication){
		String email=authentication.getName();
		return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.addBooking(email,dto));
	}
	
	@PatchMapping("/{id}/reject")
	public ResponseEntity<?> rejectBooking(@PathVariable Long id){
		return ResponseEntity.ok(bookingService.rejectBooking(id));
	}
	
	@PatchMapping("/{id}/accept")
	public ResponseEntity<?> acceptBooking(@PathVariable Long id){
		return ResponseEntity.ok(bookingService.acceptBooking(id));
	}
}
