package com.majorproj.agrorent.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majorproj.agrorent.customeExceeption.ApiException;
import com.majorproj.agrorent.dao.FarmerDao;
import com.majorproj.agrorent.entities.Farmer;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class FarmerServiceImpl implements FarmerService {
	
	private final FarmerDao farmerDao;
	
	@Override
	public Farmer getByEmail(String email) {
		
		return farmerDao.findByEmail(email).orElseThrow(()->new ApiException("Inavlid Farmer email"));
	}

}
