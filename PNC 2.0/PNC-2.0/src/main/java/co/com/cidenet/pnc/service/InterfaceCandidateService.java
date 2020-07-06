/**
*
* @author  Jaime Mejia
* @version 1.0
* @since   5/07/2020
*/
package co.com.cidenet.pnc.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import co.com.cidenet.pnc.entity.Candidate;


public interface InterfaceCandidateService {
	  
	List<Candidate> findAll();

	Candidate save(Candidate candidate);

	Candidate findOneCandidate(Long id);

	void deleteCandidate(Long id);

	Object listErrors(BindingResult result);
}


