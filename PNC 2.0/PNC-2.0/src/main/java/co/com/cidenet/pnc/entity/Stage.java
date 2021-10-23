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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "STAGES")
public class Stage {

  @Id
  @Column(name = "stage_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "no puede estar vacio")
  @Column(nullable = false)
  private String description;

  @ManyToMany(mappedBy = "annoStages")
  Set<Announcement> stagAnnouncements = new HashSet<>();

  /** @return the id */
  public Long getId() {
    return id;
  }

  /** @param id the id to set */
  public void setId(Long id) {
    this.id = id;
  }

  /** @return the description */
  public String getDescription() {
    return description;
  }

  /** @param description the description to set */
  public void setDescription(String description) {
    this.description = description;
  }
}
