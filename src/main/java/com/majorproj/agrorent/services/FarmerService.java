package com.majorproj.agrorent.services;

import com.majorproj.agrorent.entities.Farmer;

public interface FarmerService {
	Farmer getByEmail(String email);
}
