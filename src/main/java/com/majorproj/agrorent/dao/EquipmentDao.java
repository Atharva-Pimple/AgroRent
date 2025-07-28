package com.majorproj.agrorent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majorproj.agrorent.entities.Equipment;

public interface EquipmentDao extends JpaRepository<Equipment, Long> {
	
}
