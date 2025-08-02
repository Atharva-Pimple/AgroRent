package com.majorproj.agrorent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentRespDto {
	private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private double rentalPrice;
    private boolean available;
}
