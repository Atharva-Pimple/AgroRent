package com.majorproj.agrorent.services;

import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majorproj.agrorent.customeExceeption.ApiException;
import com.majorproj.agrorent.dao.BookingDao;
import com.majorproj.agrorent.dto.PaymentRespDto;
import com.majorproj.agrorent.entities.Booking;
import com.majorproj.agrorent.entities.Equipment;
import com.majorproj.agrorent.entities.Farmer;
import com.majorproj.agrorent.entities.Payment;
import com.majorproj.agrorent.entities.PaymentStatus;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import lombok.AllArgsConstructor;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	@Value("${razorpay.api.key}")
	private String apiKey;
	
	@Value("${razorpay.api.secret}")
	private String apiSecret;
	
	private final BookingDao bookingDao;
	
	

	public PaymentServiceImpl(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}



	@Override
	public PaymentRespDto createPayment(Long bookingId) {
		try {
			RazorpayClient razorpayClient=new RazorpayClient(apiKey, apiSecret);
			
			Booking booking=bookingDao.findById(bookingId).orElseThrow(()->new ApiException("Invalid Booking Id"));
			Equipment equipment=booking.getEquipment();
			Farmer farmer=booking.getFarmer();
			
			int amountPaid=(int)(booking.getTotalAmount() * 100);
			
			JSONObject orderRequest=new JSONObject();
			orderRequest.put("amount", amountPaid);
	        orderRequest.put("currency", "INR");
	        orderRequest.put("receipt", "booking_" + bookingId);
			
	        Order order= razorpayClient.orders.create(orderRequest);
	        
	        Payment payment=new Payment();
	        payment.setOrderId(order.get("id"));
	        payment.setBooking(booking);
	        payment.setStatus(PaymentStatus.CREATED);
	        payment.setAmount(booking.getTotalAmount());
	        
	        PaymentRespDto responseDto=new PaymentRespDto();
	        responseDto.setRazorpayOrderId(order.get("id"));
	        responseDto.setAmount(booking.getTotalAmount());
	        responseDto.setCurrency("INR");
	        responseDto.setName(farmer.getFirstName()+" "+farmer.getLastName());
	        responseDto.setEmail(farmer.getEmail());
	        responseDto.setEquipmentName(equipment.getName());
	        responseDto.setBookingId(bookingId);
	        
	        return responseDto;
	        
	        
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ApiException("Payment Creation failed. Try later");
		}
		
	}

}
