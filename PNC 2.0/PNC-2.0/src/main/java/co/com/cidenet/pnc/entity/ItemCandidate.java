/**
*
* @author  Jaime Mejia
* @version 1.0
* @since   5/07/2020
*/
package co.com.cidenet.pnc.entity;

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
@Table(name = "announcement_items")
public class ItemCandidate {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;

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
	 * @return the announcement
	 */
	public Announcement getAnnouncement() {
		return announcement;
	}

	/**
	 * @param announcement the announcement to set
	 */
	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	@Override
	public int hashCode() {
		return Objects.hash(announcement, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ItemCandidate)) {
			return false;
		}
		ItemCandidate other = (ItemCandidate) obj;
		return Objects.equals(announcement, other.announcement) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ItemCandidate [id=" + id + ", announcement=" + announcement + "]";
	}
    
	private static final long serialVersionUID = 1L;

}


