package com.majorproj.agrorent.entities;

import java.util.List;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="farmers")
@ToString(callSuper = true,exclude = {"equipmentList","bookings"})
public class Farmer extends BaseEntity{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @Column(length = 20, name = "first_name") 
	 private String firstName;
	 @Column(length = 30, name = "last_name") 
	 private String lastName;
	 @Column(length = 30, unique = true)
	 @NotNull
	 @Email
	 private String email;
	 @Column(nullable = false)
	 private String password;
	 @Enumerated(EnumType.STRING)
	 private Role role; // "ADMIN" or "FARMER" 
	 private boolean active = true;

	 @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Equipment> equipmentList;

	 @OneToMany(mappedBy = "farmer",cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Booking> bookings;
}
