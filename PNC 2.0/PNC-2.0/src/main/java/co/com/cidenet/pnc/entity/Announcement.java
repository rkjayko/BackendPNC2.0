package co.com.cidenet.pnc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
Created by : Jaime Mejia
*/


@Entity
@Table(name = "Announcement")
public class Announcement {
	
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
	
	private String announcementName;
	
	private Enum<Job> job;
	
	private Integer salary;
	
	private Enum<Status> status;

	private Date InitialAnnouncementDate;
	
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
	/**
	 * @return the job
	 */
	public Enum<Job> getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(Enum<Job> job) {
		this.job = job;
	}
	/**
	 * @return the initialAnnouncementDate
	 */
	/**
	 * @return the salary
	 */
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
	public void setStatus(Enum<Status> status) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EndAnnouncementDate == null) ? 0 : EndAnnouncementDate.hashCode());
		result = prime * result + ((InitialAnnouncementDate == null) ? 0 : InitialAnnouncementDate.hashCode());
		result = prime * result + ((announcementName == null) ? 0 : announcementName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Announcement other = (Announcement) obj;
		if (EndAnnouncementDate == null) {
			if (other.EndAnnouncementDate != null)
				return false;
		} else if (!EndAnnouncementDate.equals(other.EndAnnouncementDate))
			return false;
		if (InitialAnnouncementDate == null) {
			if (other.InitialAnnouncementDate != null)
				return false;
		} else if (!InitialAnnouncementDate.equals(other.InitialAnnouncementDate))
			return false;
		if (announcementName == null) {
			if (other.announcementName != null)
				return false;
		} else if (!announcementName.equals(other.announcementName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
}
