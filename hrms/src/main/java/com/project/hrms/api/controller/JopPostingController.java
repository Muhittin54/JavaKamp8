package com.project.hrms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hrms.business.abstracts.JobPostingService;
import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.entities.concretes.JobPosting;

@RestController
@RequestMapping("/api/postings")
public class JopPostingController {
	private JobPostingService jobPostingService;

	@Autowired
	public JopPostingController(JobPostingService jobPostingService) {
		this.jobPostingService = jobPostingService;
	}

	@GetMapping("/getall")
	public DataResult<List<JobPosting>> getAll() {
		return this.jobPostingService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody JobPosting jobPosting) {
		return this.jobPostingService.add(jobPosting);
	}

	@GetMapping("/getByStatus")
	public DataResult<List<JobPosting>> getByStatus(boolean status) {
		return this.jobPostingService.getByStatus(status);
	}

	@GetMapping("/getByStatusOrderByReleaseDateDesc")
	public DataResult<List<JobPosting>> getByStatusOrderByReleaseDateDesc(boolean status) {
		return this.jobPostingService.getByStatusOrderByReleaseDateDesc(status);
	}

	@GetMapping("/getByStatusAndCompanyName")
	public DataResult<List<JobPosting>> getBySatatusAndCompanyName(boolean status, String companyName) {
		return this.jobPostingService.getByStatusAndCompanyName(status, companyName);
	}

	@PutMapping("/updateJobPostingStatus")
	public Result updateJobPostingStatus(@RequestParam boolean status, @RequestParam int id) {
		return this.updateJobPostingStatus(status, id);
	}
}