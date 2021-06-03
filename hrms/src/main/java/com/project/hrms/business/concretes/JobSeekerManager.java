package com.project.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hrms.business.abstracts.JobSeekerService;
import com.project.hrms.core.adapters.mernis.MernisServiceCheck;
import com.project.hrms.core.utilities.results.DataResult;
import com.project.hrms.core.utilities.results.ErrorResult;
import com.project.hrms.core.utilities.results.Result;
import com.project.hrms.core.utilities.results.SuccessDataResult;
import com.project.hrms.core.utilities.results.SuccessResult;
import com.project.hrms.core.utilities.validations.abstracts.EmailService;
import com.project.hrms.core.utilities.validations.abstracts.ValidationService;
import com.project.hrms.dataAccess.abstracts.JobSeekerDao;
import com.project.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {
	private JobSeekerDao jobSeekerDao;
	private MernisServiceCheck mernisServiceCheck;
	private ValidationService<JobSeeker> validationService;
	private EmailService emailService;

	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao, MernisServiceCheck mernisServiceCheck,
			ValidationService<JobSeeker> validationService, EmailService emailService) {
		this.jobSeekerDao = jobSeekerDao;
		this.mernisServiceCheck = mernisServiceCheck;
		this.validationService = validationService;
		this.emailService = emailService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>
		(this.jobSeekerDao.findAll(), "Tum is arayanlar listelendi");
	}

	@Override
	public Result add(JobSeeker jobSeeker) {
		if (!(this.mernisServiceCheck.isRealPersonCheck(jobSeeker))) {
			return new ErrorResult("Kisi dogrulanamadi");
		} else if (!(this.validationService.validation(jobSeeker))) {
			return new ErrorResult("Lutfen bos alan birakmayin");
		} else if (!(this.emailService.emailCheck(jobSeeker.getEmail()))) {
			return new ErrorResult("Lutfen gecerli bir email adresi giriniz.");
		} else if (this.getByEmail(jobSeeker.getEmail()) != null) {
			return new ErrorResult("Bu email adresi zaten kullanilmis");
		} else if (this.getByIdentificationNumber(jobSeeker.getIdentificationNumber()) != null) {
			return new ErrorResult("Bu kimlik numarasi zaten kullanilmis");
		} else {
			this.jobSeekerDao.save(jobSeeker);
			this.emailService.sendEmail(jobSeeker.getEmail());
			return new SuccessResult(
					"Is arayan kaydedildi. Kaydin tamamlanabilmesi için eposta adresinize gönderilen maile tiklayin.");
		}
	}

	@Override
	public DataResult<JobSeeker> getByIdentificationNumber(String identificationNumber) {
		return new SuccessDataResult<JobSeeker>
		(this.jobSeekerDao.getByIdentificationNumber(identificationNumber), "Kimlik numarasina gore is arayanlar getirildi");
	}

	@Override
	public DataResult<JobSeeker> getByEmail(String email) {
		return new SuccessDataResult<JobSeeker>
		(this.jobSeekerDao.getByEmail(email), "Eposta adresine gore is arayan getirildi");
	}

	@Override
	public DataResult<JobSeeker> getById(int id) {
		return new SuccessDataResult<JobSeeker>
		(this.jobSeekerDao.getById(id), "Id ye gore is arayan getirildi");
	}

	@Override
	public DataResult<List<JobSeeker>> getByName(String name) {
		return new SuccessDataResult<List<JobSeeker>>
		(this.jobSeekerDao.getByName(name), "Isme gore is arayanlar listelendi");
	}
}
