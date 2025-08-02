package com.majorproj.agrorent.services;

import com.majorproj.agrorent.dto.PaymentRespDto;

public interface PaymentService {

	PaymentRespDto createPayment(Long bookingId);
	

}
