package com.project.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "system_personnels")
@AllArgsConstructor
@NoArgsConstructor
public class SystemPersonnel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne()
	@JoinColumn(name = "department_id")
	private Department department;
	// @Column(name = "department_id")
	// private int departmentId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
}