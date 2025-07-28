package com.majorproj.agrorent.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majorproj.agrorent.customeExceeption.ApiException;
import com.majorproj.agrorent.dao.FarmerDao;
import com.majorproj.agrorent.dto.ApiResponse;
import com.majorproj.agrorent.dto.UserSignUpDto;
import com.majorproj.agrorent.entities.Farmer;
import com.majorproj.agrorent.entities.Role;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
	
	private final FarmerDao farmerDao;
	private final ModelMapper mapper;
 

	@Override
	public ApiResponse userSignUp(UserSignUpDto dto) {
		if(farmerDao.existsByEmail(dto.getEmail())) {
			throw new ApiException("Email already exist!!");
		}
		
		Farmer farmer=mapper.map(dto, Farmer.class);
		farmer.setRole(Role.ROLE_FARMER);
		farmer.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		farmer.setActive(true);
		
		farmerDao.save(farmer);
		return new ApiResponse("Registration Successfull");
	}

}
