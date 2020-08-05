/**
*
* @author  Jaime Mejia
* @version 1.0
* @since   4/08/2020
*/
package co.com.cidenet.pnc.dto;

import java.util.UUID;



public class StageDTO {

	private UUID id;
	private String description;
	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}


