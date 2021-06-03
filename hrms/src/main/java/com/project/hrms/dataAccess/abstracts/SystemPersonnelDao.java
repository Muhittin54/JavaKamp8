package com.project.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.hrms.entities.concretes.SystemPersonnel;

public interface SystemPersonnelDao extends JpaRepository<SystemPersonnel, Integer>{
	SystemPersonnel getById(int id);
	
	SystemPersonnel getByEmail(String email);
	
	List<SystemPersonnel> getByDepartment(int departmentId);
	
	List<SystemPersonnel> getByDepartmentIn(List<Integer> departments);
	
	@Modifying
	@Query("update employers set personnel_verification = :isActive where id = :id")
	SystemPersonnel staffApproval(boolean isActive, int id);
	
}