package com.project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.hrms.business.abstracts.JobPostingService;
import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.ErrorResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.core.utilities.results.SuccessDataResult;
import com.project.hrms.core.utilities.results.SuccessResult;
import com.project.hrms.core.utilities.validations.abstracts.ValidationService;
import com.project.hrms.dataAccess.abstracts.JobPostingDao;
import com.project.hrms.entities.concretes.JobPosting;

@Service
public class JobPostingManager implements JobPostingService {

	private JobPostingDao jobPostingDao;
	private ValidationService<JobPosting> validationService;

	@Autowired
	public JobPostingManager(JobPostingDao jobPostingDao, ValidationService<JobPosting> validationService) {
		this.jobPostingDao = jobPostingDao;
		this.validationService = validationService;
	}

	@Override
	public DataResult<List<JobPosting>> getAll() {
		return new SuccessDataResult<List<JobPosting>>
		(this.jobPostingDao.findAll(), "Tum is ilanları listelendi");
	}
	
	@Override
	public DataResult<List<JobPosting>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.findAll(pageable).getContent());
	}
	
	@Override
	public DataResult<List<JobPosting>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.DESC, "release_date");
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.findAll(sort));
	}

	@Override
	public Result add(JobPosting jobPosting) {
		if (!this.validationService.validation(jobPosting)) {
			return new ErrorResult("Lutfen bos alan birakmayiniz.");
		}
		this.jobPostingDao.save(jobPosting);
		return new SuccessResult("Is ilanı eklendi.");
	}

	@Override
	public DataResult<JobPosting> getById(int id) {
		return new SuccessDataResult<JobPosting>
		(this.jobPostingDao.getById(id), "Ilan listelendi");
	}
	
	@Override
	public DataResult<List<JobPosting>> getByStatus(boolean status) {
		return new SuccessDataResult<List<JobPosting>>
		(this.jobPostingDao.getByStatus(status), "Aktif ilanlar listelendi");
	}

	@Override
	public DataResult<List<JobPosting>> getByStatusOrderByReleaseDateDesc(boolean status) {
		return new SuccessDataResult<List<JobPosting>>
		(this.jobPostingDao.getByStatusOrderByReleaseDateDesc(status), "Tarihe gore ilanlar listelendi");
	}

	@Override
	public DataResult<List<JobPosting>> getByStatusAndCompanyName(boolean status, String companyName) {
		return new SuccessDataResult<List<JobPosting>>
		(this.jobPostingDao.getByStatusAndCompanyName(status, companyName), "Sirkete gore ilanlar listelendi.");
	}
	
	@Override
	public Result updateJobPostingStatus(boolean status, int id) {
		return new SuccessResult("Ilan durumu basariyla guncellendi.");
	}

	@Override
	public DataResult<List<JobPosting>> getByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobPosting>>
		(this.jobPostingDao.getByEmployer(employerId), "Is veren ilanlari listelendi");
	}
}