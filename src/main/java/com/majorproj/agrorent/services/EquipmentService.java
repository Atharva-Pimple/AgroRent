package com.majorproj.agrorent.services;

import java.util.List;

import com.majorproj.agrorent.dto.ApiResponse;
import com.majorproj.agrorent.dto.EquipmentDto;
import com.majorproj.agrorent.dto.EquipmentRespDto;
import com.majorproj.agrorent.dto.EquipmentUpdateDto;

import jakarta.validation.Valid;


public interface EquipmentService {

	ApiResponse addEquipment(EquipmentDto dto, String email);

	List<EquipmentRespDto> getAllEquipments();

	ApiResponse updateEquipment(Long equipmentId, @Valid EquipmentUpdateDto dto);

	ApiResponse deleteEquipment(Long equipmentId);

	EquipmentRespDto getEquipmentById(Long equipmentId);

}
