package com.project.hrms.business.abstracts;

import java.util.List;

import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.entities.concretes.JobPosting;

public interface JobPostingService {
	Result add(JobPosting jobPosting);

	DataResult<List<JobPosting>> getAll();

	DataResult<JobPosting> getById(int id);

	DataResult<List<JobPosting>> getByStatus(boolean status);

	DataResult<List<JobPosting>> getByStatusOrderByReleaseDateDesc(boolean status);

	DataResult<List<JobPosting>> getByStatusAndCompanyName(boolean status, String companyName);

	DataResult<List<JobPosting>> getByEmployerId(int employerId);

	Result updateJobPostingStatus(boolean status, int id);
}
