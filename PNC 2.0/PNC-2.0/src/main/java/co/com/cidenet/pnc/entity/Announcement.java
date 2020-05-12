package co.com.cidenet.pnc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


/*
Created by : Jaime Mejia
*/


@Entity
@Table(name = "Announcement")
public class Announcement {
	
	private static final long serialVersionUID = 1L;
	
	enum Job {
		  JAVA,
		  PLSQL,
		  GROOVY,
		  SWIFT
		}
	
	enum Status {
		  OPEN,
		  CLOSED
		}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotEmpty(message = "no puede estar vacio")
    @Column(name = "ANNOUNCEMENT_NAME", nullable = false)
    private String announcementName;

    @NotEmpty(message = "no puede estar vacio")
    @Column(name = "JOB", nullable = false)
    @Enumerated(value = EnumType.STRING)
	private Job job;
    
    @Column(name = "SALARY", nullable = false)
    @Min(value = 0L, message = "El numero debe de ser positivo")
	private Integer salary;
    
    @NotEmpty(message = "no puede estar vacio")
    @Column(name = "STATUS", nullable = false)
    @Enumerated(value = EnumType.STRING)
	private Status status;

    @Temporal(TemporalType.DATE)
	private Date InitialAnnouncementDate;
    
    @Temporal(TemporalType.DATE)
	private Date EndAnnouncementDate;
    
   	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the announcementName
	 */
	public String getAnnouncementName() {
		return announcementName;
	}
	/**
	 * @param announcementName the announcementName to set
	 */
	public void setAnnouncementName(String announcementName) {
		this.announcementName = announcementName;
	}
	
	public Enum<Job> getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}
	
	public Integer getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	/**
	 * @return the status
	 */
	public Enum<Status> getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	
	public Date getInitialAnnouncementDate() {
		return InitialAnnouncementDate;
	}
	/**
	 * @param initialAnnouncementDate the initialAnnouncementDate to set
	 */
	public void setInitialAnnouncementDate(Date initialAnnouncementDate) {
		InitialAnnouncementDate = initialAnnouncementDate;
	}
	
	/**
	 * @return the endAnnouncementDate
	 */
	public Date getEndAnnouncementDate() {
		return EndAnnouncementDate;
	}
	/**
	 * @param endAnnouncementDate the endAnnouncementDate to set
	 */
	public void setEndAnnouncementDate(Date endAnnouncementDate) {
		EndAnnouncementDate = endAnnouncementDate;
	}
	
}
