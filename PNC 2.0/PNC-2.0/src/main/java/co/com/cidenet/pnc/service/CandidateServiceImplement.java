/**
*
* @author  Jaime Mejia
* @version 1.0
* @since   5/07/2020
*/
package co.com.cidenet.pnc.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import co.com.cidenet.pnc.entity.Candidate;
import co.com.cidenet.pnc.repository.CandidateRepository;

@Service
public class CandidateServiceImplement implements InterfaceCandidateService {
	  
	@Autowired private CandidateRepository candidateRepository;

	  public CandidateServiceImplement(CandidateRepository candidateRepository) {
	    this.candidateRepository = candidateRepository;
	  }

	  @Override
	  @Transactional
	  public List<Candidate> findAll() {
	    return (List<Candidate>) candidateRepository.findAll();
	  }

	  @Override
	  @Transactional
	  public Candidate save(Candidate candidate) {
	    return candidateRepository.save(candidate);
	  }

	  @Override
	  // @Transactional(readOnly = true)
	  public Candidate findOneCandidate(Long id) {
	    return candidateRepository.findById(id).orElse(null);
	  }

	  @Override
	  public void deleteCandidate(Long id) {
	    candidateRepository.deleteById(id);
	  }

	  public Object listErrors(BindingResult result) {
	    return result.getFieldErrors().stream()
	        .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
	        .collect(Collectors.toList());
	  }
}


