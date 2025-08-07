package com.majorproj.agrorent.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.majorproj.agrorent.dto.EquipmentRespDto;
import com.majorproj.agrorent.entities.Equipment;

public interface EquipmentDao extends JpaRepository<Equipment, Long> {
	
	@Query("SELECT new com.majorproj.agrorent.dto.EquipmentRespDto(e.id, e.name, e.description, e.imageUrl, e.rentalPrice, e.available) " +
	           "FROM Equipment e WHERE e.owner.email = :email")
	List<EquipmentRespDto> findOwnerEquipments(@Param("email") String email);
}
