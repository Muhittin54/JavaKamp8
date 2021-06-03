package com.project.hrms.business.abstracts;

import java.util.List;

import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.entities.concretes.Employer;

public interface EmployerService {
	DataResult<List<Employer>> getAll();

	Result add(Employer employer);
	
	DataResult<Employer> getByEmail(String email);
	
	DataResult<Employer> getById(int id);
	
	DataResult<List<Employer>> getByName(String name);
}
