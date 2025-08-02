package com.majorproj.agrorent.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majorproj.agrorent.customeExceeption.ApiException;
import com.majorproj.agrorent.dao.BookingDao;
import com.majorproj.agrorent.dao.EquipmentDao;
import com.majorproj.agrorent.dao.FarmerDao;
import com.majorproj.agrorent.dto.ApiResponse;
import com.majorproj.agrorent.dto.BookingReqDto;
import com.majorproj.agrorent.entities.Booking;
import com.majorproj.agrorent.entities.BookingStatus;
import com.majorproj.agrorent.entities.Equipment;
import com.majorproj.agrorent.entities.Farmer;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
	private final BookingDao bookingDao;
	private final FarmerDao farmerDao;
	private final EquipmentDao equipmentDao;
	
	@Override
	public ApiResponse addBooking(String email, BookingReqDto dto) {
		Farmer farmer=farmerDao.findByEmail(email).orElseThrow(()->new ApiException("Invalid Farmer"));
		Equipment equipment=equipmentDao.findById(dto.getEquipmentId()).orElseThrow(()->new ApiException("Invalid equipment id"));
		
		Booking booking=new Booking();
		booking.setStartDate(dto.getStartDate());
		booking.setEndDate(dto.getEndDate());
		booking.setStatus(BookingStatus.PENDING);
		booking.setEquipment(equipment);
		equipment.addBooking(booking);
		booking.setFarmer(farmer);
		farmer.addBooking(booking);
		long totalDays=getDaysBetween(dto.getStartDate(), dto.getEndDate());
		booking.setTotalAmount(dto.getTotalAmount() * totalDays);
		
		bookingDao.save(booking);
		return new ApiResponse("Booking request submitted successfully.");
	}
	
	public static long getDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

	@Override
	public ApiResponse rejectBooking(Long id) {
		Booking booking=bookingDao.findById(id).orElseThrow(()->new ApiException("Invalid booking id"));
		booking.setStatus(BookingStatus.REJECTED);
		return new ApiResponse("Booking request rejected");
	}

	@Override
	public ApiResponse acceptBooking(Long id) {
		Booking booking=bookingDao.findById(id).orElseThrow(()->new ApiException("Invalid booking id"));
		booking.setStatus(BookingStatus.ACCEPTED);
		return new ApiResponse("Booking request accepted");
		
		//todo can send email if time permits
	}
	
	
}
