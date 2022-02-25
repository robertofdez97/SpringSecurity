package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String dni;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	private String fullName;
	
	private Boolean enabled = true;
	
	private Integer attemps = 0;
	
	@Enumerated(EnumType.STRING)
	private Provider provider;
}
