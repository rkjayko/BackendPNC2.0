/**
 * @author Jaime Mejia
 * @version 1.0
 * @since 30/05/2020
 */
package co.com.cidenet.pnc.dto;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class AnnouncementDTO extends DTO {

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

  private UUID id;
  private String announcementName;
  private Job job;
  private Integer salary;
  private Status status;
  private Date initialAnnouncementDate;
  private Date endAnnouncementDate;

  /** @return the id */
  public UUID getId() {
    return id;
  }
  /** @param id the id to set */
  public void setId(UUID id) {
    this.id = id;
  }
  /** @return the announcementName */
  public String getAnnouncementName() {
    return announcementName;
  }
  /** @param announcementName the announcementName to set */
  public void setAnnouncementName(String announcementName) {
    this.announcementName = announcementName;
  }
  /** @return the job */
  public Job getJob() {
    return job;
  }
  /** @param job the job to set */
  public void setJob(Job job) {
    this.job = job;
  }
  /** @return the salary */
  public Integer getSalary() {
    return salary;
  }
  /** @param salary the salary to set */
  public void setSalary(Integer salary) {
    this.salary = salary;
  }
  /** @return the status */
  public Status getStatus() {
    return status;
  }
  /** @param status the status to set */
  public void setStatus(Status status) {
    this.status = status;
  }
  /** @return the initialAnnouncementDate */
  public Date getInitialAnnouncementDate() {
    return initialAnnouncementDate;
  }
  /** @param initialAnnouncementDate the initialAnnouncementDate to set */
  public void setInitialAnnouncementDate(Date initialAnnouncementDate) {
    this.initialAnnouncementDate = initialAnnouncementDate;
  }
  /** @return the endAnnouncementDate */
  public Date getEndAnnouncementDate() {
    return endAnnouncementDate;
  }
  /** @param endAnnouncementDate the endAnnouncementDate to set */
  public void setEndAnnouncementDate(Date endAnnouncementDate) {
    this.endAnnouncementDate = endAnnouncementDate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        announcementName, endAnnouncementDate, id, initialAnnouncementDate, job, salary, status);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof AnnouncementDTO)) {
      return false;
    }
    AnnouncementDTO other = (AnnouncementDTO) obj;
    return Objects.equals(announcementName, other.announcementName)
        && Objects.equals(endAnnouncementDate, other.endAnnouncementDate)
        && Objects.equals(id, other.id)
        && Objects.equals(initialAnnouncementDate, other.initialAnnouncementDate)
        && job == other.job
        && Objects.equals(salary, other.salary)
        && status == other.status;
  }
}
