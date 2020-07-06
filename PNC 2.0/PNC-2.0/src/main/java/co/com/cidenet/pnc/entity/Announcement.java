package co.com.cidenet.pnc.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

  enum English {
    YES,
    NO
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "announcement_id")
  private List<ItemAnnouncement> candidate;

  public Announcement() {
    candidate = new ArrayList<>();
  }

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

  public List<ItemAnnouncement> getItems() {
    return candidate;
  }

  public void setItems(List<ItemAnnouncement> items) {
    this.candidate = candidate;
  }

  public int getTotalCandidate() {
    int total = 0;
    for (ItemAnnouncement item : candidate) {
      total += 1;
    }
    return total;
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
