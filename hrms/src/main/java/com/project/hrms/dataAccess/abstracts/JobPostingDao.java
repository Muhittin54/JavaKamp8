package com.project.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.hrms.entities.concretes.JobPosting;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {
	JobPosting getById(int id);

	List<JobPosting> getByStatus(boolean status);

	List<JobPosting> getByStatusOrderByReleaseDateDesc(boolean status);

	@Query("Select " + "e.company_name, j.job_position, j.number_of_position, "
			+ "j.release_date, j.application_deadline " + "From job_postings as j " + "inner join employers as e "
			+ "on e.id = j.employer_id and status = :status and company_name = :companyName")
	List<JobPosting> getByStatusAndCompanyName(boolean status, String companyName);

	@Modifying
	@Query("update job_postings set status = :status where id = :id")
	int updateJobPostingStatus(boolean status, int id);

	List<JobPosting> getByEmployer(int employerId);
}
