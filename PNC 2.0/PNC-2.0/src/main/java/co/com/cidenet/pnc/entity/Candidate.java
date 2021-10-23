/**
 * @author Jaime Mejia
 * @version 1.0
 * @since 5/07/2020
 */
package co.com.cidenet.pnc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "APPLICANT")
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
  private String firstName;

  @NotEmpty(message = "no puede estar vacio")
  @Column(nullable = false)
  private String lastName;

  @NotEmpty(message = "no puede estar vacio")
  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private ProgrammingLanguage programmingLanguage;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private English english;

  @Column(nullable = false)
  @Min(value = 0L, message = "El numero debe de ser positivo")
  private Integer salary;

  @NotEmpty(message = "no puede estar vacio")
  @Column(nullable = false)
  private String softSkill;

  @ManyToMany private Set<Announcement> applAnnouncements = new HashSet<>();

  /** @return the id */
  public Long getId() {
    return id;
  }

  /** @param id the id to set */
  public void setId(Long id) {
    this.id = id;
  }

  /** @return the firstName */
  public String getFirstName() {
    return firstName;
  }

  /** @param firstName the firstName to set */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /** @return the lastName */
  public String getLastName() {
    return lastName;
  }

  /** @param lastName the lastName to set */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /** @return the email */
  public String getEmail() {
    return email;
  }

  /** @param email the email to set */
  public void setEmail(String email) {
    this.email = email;
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
}
