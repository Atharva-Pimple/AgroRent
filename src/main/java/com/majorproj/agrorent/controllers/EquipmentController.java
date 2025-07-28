package com.majorproj.agrorent.controllers;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.majorproj.agrorent.dto.EquipmentDto;
import com.majorproj.agrorent.services.EquipmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/equipment")
@AllArgsConstructor
public class EquipmentController {
	
	private final EquipmentService equipmentService;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addEquipment(@Valid @ModelAttribute EquipmentDto dto,Authentication authentication){
		String email=authentication.getName();
		
		return ResponseEntity.ok(equipmentService.addEquipment(dto,email));
	}
}
