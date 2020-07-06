/**
*
* @author  Jaime Mejia
* @version 1.0
* @since   5/07/2020
*/
package co.com.cidenet.pnc.repository;

import org.springframework.data.repository.CrudRepository;

import co.com.cidenet.pnc.entity.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {}


