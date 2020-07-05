/**
 * @author Jaime Mejia
 * @version 1.0
 * @since 5/07/2020
 */
package co.com.cidenet.pnc.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Candidate")
public class Candidate {

  enum ProgrammingLanguage {
    JAVA,
    PLSQL,
    GROOVY,
    SWIFT
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
  private String candidateName;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private ProgrammingLanguage programmingLanguage;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private English english;

  @Column(nullable = false)
  @Min(value = 0L, message = "El numero debe de ser positivo")
  private Integer salary;

  /** @return the id */
  public Long getId() {
    return id;
  }

  /** @param id the id to set */
  public void setId(Long id) {
    this.id = id;
  }

  /** @return the candidateName */
  public String getCandidateName() {
    return candidateName;
  }

  /** @param candidateName the candidateName to set */
  public void setCandidateName(String candidateName) {
    this.candidateName = candidateName;
  }

  /** @return the programmingLanguage */
  public ProgrammingLanguage getProgrammingLanguage() {
    return programmingLanguage;
  }

  /** @param programmingLanguage the programmingLanguage to set */
  public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
    this.programmingLanguage = programmingLanguage;
  }

  /** @return the english */
  public English getEnglish() {
    return english;
  }

  /** @param english the english to set */
  public void setEnglish(English english) {
    this.english = english;
  }

  /** @return the salary */
  public Integer getSalary() {
    return salary;
  }

  /** @param salary the salary to set */
  public void setSalary(Integer salary) {
    this.salary = salary;
  }

  /** @return the softSkill */
  public String getSoftSkill() {
    return softSkill;
  }

  /** @param softSkill the softSkill to set */
  public void setSoftSkill(String softSkill) {
    this.softSkill = softSkill;
  }

  @NotEmpty(message = "no puede estar vacio")
  @Column(nullable = false)
  private String softSkill;

  @Override
  public int hashCode() {
    return Objects.hash(candidateName, english, id, programmingLanguage, salary, softSkill);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Candidate)) {
      return false;
    }
    Candidate other = (Candidate) obj;
    return Objects.equals(candidateName, other.candidateName)
        && english == other.english
        && Objects.equals(id, other.id)
        && programmingLanguage == other.programmingLanguage
        && Objects.equals(salary, other.salary)
        && Objects.equals(softSkill, other.softSkill);
  }

  @Override
  public String toString() {
    return "Candidate [id="
        + id
        + ", candidateName="
        + candidateName
        + ", programmingLanguage="
        + programmingLanguage
        + ", english="
        + english
        + ", salary="
        + salary
        + ", softSkill="
        + softSkill
        + "]";
  }
}
