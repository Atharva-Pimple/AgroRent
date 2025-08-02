package com.majorproj.agrorent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majorproj.agrorent.entities.Booking;

public interface BookingDao extends JpaRepository<Booking, Long> {

}
