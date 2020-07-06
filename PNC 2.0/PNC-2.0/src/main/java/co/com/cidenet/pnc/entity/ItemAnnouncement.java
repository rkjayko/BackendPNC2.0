/**
 * @author Jaime Mejia
 * @version 1.0
 * @since 5/07/2020
 */
package co.com.cidenet.pnc.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "candidate_items")
public class ItemAnnouncement implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  /** @return the id */
  public Long getId() {
    return id;
  }

  /** @param id the id to set */
  public void setId(Long id) {
    this.id = id;
  }

  /** @return the candidate */
  public Candidate getCandidate() {
    return candidate;
  }

  /** @param candidate the candidate to set */
  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(candidate, id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ItemAnnouncement)) {
      return false;
    }
    ItemAnnouncement other = (ItemAnnouncement) obj;
    return Objects.equals(candidate, other.candidate) && Objects.equals(id, other.id);
  }

  @Override
  public String toString() {
    return "ItemAnnouncement [id=" + id + ", candidate=" + candidate + "]";
  }

  private static final long serialVersionUID = 1L;
}
