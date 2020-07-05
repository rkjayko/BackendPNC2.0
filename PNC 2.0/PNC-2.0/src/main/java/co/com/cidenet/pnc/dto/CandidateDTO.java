/**
*
* @author  Jaime Mejia
* @version 1.0
* @since   5/07/2020
*/
package co.com.cidenet.pnc.dto;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class CandidateDTO extends DTO{
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

		  private UUID id;
		  private String programmingLanguage;
		  private English english;
		  private Integer salary;
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
		 * @return the programmingLanguage
		 */
		public String getProgrammingLanguage() {
			return programmingLanguage;
		}
		/**
		 * @param programmingLanguage the programmingLanguage to set
		 */
		public void setProgrammingLanguage(String programmingLanguage) {
			this.programmingLanguage = programmingLanguage;
		}
		/**
		 * @return the english
		 */
		public English getEnglish() {
			return english;
		}
		/**
		 * @param english the english to set
		 */
		public void setEnglish(English english) {
			this.english = english;
		}
		/**
		 * @return the salary
		 */
		public Integer getSalary() {
			return salary;
		}
		/**
		 * @param salary the salary to set
		 */
		public void setSalary(Integer salary) {
			this.salary = salary;
		}
		@Override
		public int hashCode() {
			return Objects.hash(english, id, programmingLanguage, salary);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof CandidateDTO)) {
				return false;
			}
			CandidateDTO other = (CandidateDTO) obj;
			return english == other.english && Objects.equals(id, other.id)
					&& Objects.equals(programmingLanguage, other.programmingLanguage)
					&& Objects.equals(salary, other.salary);
		}
		@Override
		public String toString() {
			return "CandidateDTO [id=" + id + ", programmingLanguage=" + programmingLanguage + ", english=" + english
					+ ", salary=" + salary + "]";
		}
}


