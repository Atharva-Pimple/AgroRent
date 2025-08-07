package com.majorproj.agrorent.services;

import com.majorproj.agrorent.dto.ApiResponse;
import com.majorproj.agrorent.dto.UserSignUpDto;


public interface AuthService {

	ApiResponse userSignUp(UserSignUpDto dto);

	ApiResponse verifyToken(String token);

}
