package co.com.cidenet.pnc.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/*
Created by : Jaime Mejia
*/

@Entity
@Table(name = "ANNOUNCEMENTS")
public class Announcement {

  public enum Job {
    JAVA,
    PLSQL,
    GROOVY,
    SWIFT
  }

  public enum Status {
    OPEN,
    CLOSED
  }

  public enum English {
    YES,
    NO
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "announcement_id")
  private Long id;

  @NotEmpty(message = "no puede estar vacio")
  @Column(nullable = false)
  private String announcementName;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Job job;

  @Column(nullable = false)
  @Min(value = 0L, message = "El numero debe de ser positivo")
  private Integer salary;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Status status;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private English english;

  @JoinTable(
      name = "ANNOUCEMENT_STAGE",
      joinColumns = {@JoinColumn(name = "announcement_id")},
      inverseJoinColumns = {@JoinColumn(name = "stage_id")})
  @ManyToMany
  private Set<Stage> annoStages = new HashSet<>();

  @ManyToMany(mappedBy = "applAnnouncements")
  private Set<Candidate> annoApplicants = new HashSet<>();

  /** @return the id */
  public Long getId() {
    return id;
  }
  /** @param id the id to set */
  public void setId(Long id) {
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

  public Job getJob() {
    return job;
  }
  /** @param job the job to set */
  public void setJob(Job job) {
    this.job = job;
  }

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
  /*** @return the english*/
  public English getEnglish() {
    return english;
  }
  /*** @param english the english to set*/
  public void setEnglish(English english) {
    this.english = english;
  }

  public Set<Stage> getAnnouncementStages() {
    return annoStages;
  }

  public void setAnnouncementStages(Set<Stage> annoStages) {
    this.annoStages = annoStages;
  }

  public Set<Candidate> getAnnouncementCandidates() {
    return annoApplicants;
  }

  public void setAnnouncementCandidate(Set<Candidate> annoApplicants) {
    this.annoApplicants = annoApplicants;
  }

  @Override
  public int hashCode() {
    return Objects.hash(announcementName, english, id, job, salary, status);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Announcement)) {
      return false;
    }
    Announcement other = (Announcement) obj;
    return Objects.equals(announcementName, other.announcementName)
        && english == other.english
        && Objects.equals(id, other.id)
        && job == other.job
        && Objects.equals(salary, other.salary)
        && status == other.status;
  }

  @Override
  public String toString() {
    return "Announcement [id="
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

  private static final long serialVersionUID = 1L;
}
