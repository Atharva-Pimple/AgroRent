package com.majorproj.agrorent.services;

import com.majorproj.agrorent.dto.ApiResponse;
import com.majorproj.agrorent.dto.BookingReqDto;

public interface BookingService {

	ApiResponse addBooking(String email, BookingReqDto dto);

	ApiResponse rejectBooking(Long id);
	
	ApiResponse acceptBooking(Long id);
	

}
