package com.project.hrms.business.abstracts;

import java.util.List;

import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.entities.concretes.SystemPersonnel;

public interface SystemPersonnelService {
	DataResult<List<SystemPersonnel>> getAll();

	Result add(SystemPersonnel systemPersonnel);

	Result staffApproval(boolean isActive, int id);
	
	DataResult<SystemPersonnel> getById(int id);
	
	DataResult<SystemPersonnel> getByEmail(String email);
	
	DataResult<List<SystemPersonnel>> getByDepartmentId(int departmentId);
	
	DataResult<List<SystemPersonnel>> getByDepartmentIdIn(List<Integer> departments);
}
