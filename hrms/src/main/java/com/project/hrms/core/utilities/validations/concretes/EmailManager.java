package com.project.hrms.core.utilities.validations.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.project.hrms.core.utilities.validations.abstracts.EmailService;
import com.project.hrms.entities.concretes.Employer;

@Service
public class EmailManager implements EmailService {
	@Override
	public boolean emailCheck(String email) {
		String regex = "^[\\w]+(?:\\.[\\w]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean emailCheck(Employer employer) {
		String regex = "^[\\w]+(?:\\.[\\w]+)*@(?:[" + employer.getWebSite().substring(4) + "-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(employer.getEmail());
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	@Override
	public void sendEmail(String email) {
		return;
	}
}
