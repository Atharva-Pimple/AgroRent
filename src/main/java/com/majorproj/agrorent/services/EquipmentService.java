package com.majorproj.agrorent.services;

import com.majorproj.agrorent.dto.ApiResponse;
import com.majorproj.agrorent.dto.EquipmentDto;


public interface EquipmentService {

	ApiResponse addEquipment(EquipmentDto dto, String email);

}
