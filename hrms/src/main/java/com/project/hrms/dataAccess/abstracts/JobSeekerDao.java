package com.project.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hrms.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {
	JobSeeker getByIdentificationNumber(String identificationNumber);

	JobSeeker getByEmail(String email);

	JobSeeker getById(int id);
	
	List<JobSeeker> getByName(String name);
}