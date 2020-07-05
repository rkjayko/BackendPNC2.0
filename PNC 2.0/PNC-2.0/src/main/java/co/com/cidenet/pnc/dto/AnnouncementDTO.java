/**
 * @author Jaime Mejia
 * @version 1.0
 * @since 30/05/2020
 */
package co.com.cidenet.pnc.dto;

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

  enum English {
    YES,
    NO
  }

  private UUID id;
  private String announcementName;
  private Job job;
  private Integer salary;
  private Status status;
  private English english;

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
  /** @return the english */
  public English getEnglish() {
    return english;
  }

  /** @param english the english to set */
  public void setEnglish(English english) {
    this.english = english;
  }

  @Override
  public String toString() {
    return "AnnouncementDTO [id="
        + id
        + ", announcementName="
        + announcementName
        + ", job="
        + job
        + ", salary="
        + salary
        + ", status="
        + status
        + ", english="
        + english
        + "]";
  }
}
