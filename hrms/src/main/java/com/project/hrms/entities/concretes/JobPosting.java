package com.project.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "job_postings")
@AllArgsConstructor
@NoArgsConstructor
public class JobPosting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne()
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	@Column(name = "job_position")
	private String jobPosition;
	
	@Column(name = "job_description")
	private String jobDescription;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "salary")
	private String salary;
	
	@Column(name = "number_of_position")
	private String numberOfPosition;
	
	@Column(name = "application_deadline")
	private Date applicationDeadline;
	
	@Column(name = "release_date")
	private Date releaseDate;
	
	@Column(name = "status")
	private boolean status = true;
}
