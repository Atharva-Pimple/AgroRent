package com.majorproj.agrorent.services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majorproj.agrorent.customeExceeption.ApiException;
import com.majorproj.agrorent.dao.EquipmentDao;
import com.majorproj.agrorent.dao.FarmerDao;
import com.majorproj.agrorent.dto.ApiResponse;
import com.majorproj.agrorent.dto.EquipmentDto;
import com.majorproj.agrorent.entities.Equipment;
import com.majorproj.agrorent.entities.Farmer;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
	private final EquipmentDao equipmentDao;
	private final FarmerDao farmerDao;
	private final ModelMapper mapper;
	private final ImageService imageService;
	
	
	@Override
	public ApiResponse addEquipment(EquipmentDto dto, String email) {
		Farmer farmer= farmerDao.findByEmail(email).orElseThrow(()->new ApiException("Invalid farmer"));
		
		Equipment equipment=mapper.map(dto, Equipment.class);
		equipment.setOwner(farmer);
		
		String fileName=UUID.randomUUID().toString();
		String imageUrl=imageService.uploadImage(dto.getImage(), fileName);
		
		equipment.setImageUrl(imageUrl);
		equipment.setCloudinaryPublicId(fileName);
		
		if(equipmentDao.save(equipment)==null) {
			throw new ApiException("Unable to add new Equipment");
		}
		
		return new ApiResponse("Equipment added successfully");
	}
	
}
