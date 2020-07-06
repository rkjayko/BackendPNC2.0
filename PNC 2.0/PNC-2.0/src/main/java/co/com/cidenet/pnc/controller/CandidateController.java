/**
*
* @author  Jaime Mejia
* @version 1.0
* @since   5/07/2020
*/
package co.com.cidenet.pnc.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import co.com.cidenet.pnc.entity.Candidate;
import co.com.cidenet.pnc.exceptions.EntitiesFilterException;
import co.com.cidenet.pnc.exceptions.ObjectMapperException;
import co.com.cidenet.pnc.service.InterfaceCandidateService;
import co.com.cidenet.pnc.utils.Constants;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CandidateController {
		@Autowired
		private InterfaceCandidateService candidateService;
		
	  /*List all candidates */

	  @GetMapping(value = "/candidates")
		public ResponseEntity<List<Candidate>> getCandidates(@RequestParam Map<String, String> requestParams) 
			throws HttpClientErrorException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, EntitiesFilterException, ObjectMapperException  {
		    List<Candidate> candidate;
		    Map<String, Object> response = new HashMap<>();
		    try {
		      candidate = candidateService.findAll();
		    } catch (DataAccessException e) {
		      response.put("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		      response.put("mensaje",Objects.requireNonNull(e.getMessage()).concat(": ") .concat(e.getMostSpecificCause().getMessage()));
		      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
				return ResponseEntity.ok().body(candidateService.findAll());
			}


	  /*list one candidate by ID, if the id didn't exist then return a error message*/
	  @GetMapping("/candidate/{id}")
	  public ResponseEntity<Candidate> show(@PathVariable Long id) {
	    Candidate candidate;
	    Map<String, Object> response = new HashMap<>();
	    try {
	      candidate = candidateService.findOneCandidate(id);
	    } catch (DataAccessException e) {
	      response.put(Constants.MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
	      response.put(
	          Constants.ERROR,
	          Objects.requireNonNull(e.getMessage())
	              .concat(": ")
	              .concat(e.getMostSpecificCause().getMessage()));
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    if (candidate == null) {
	      response.put(
	          Constants.MESSAGE,
	          "El ID del Anuncio: ".concat(id.toString().concat(Constants.MSG_ERROR_NOT_EXIST)));
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(candidate, HttpStatus.OK);
	  }

	  /*Create one candidate, it verify if the data are valid or if exist a connection to database, else save the candidate*/
	  @PostMapping(value = "/createcandidate")
	  public ResponseEntity<Map<String, Object>> create(
	      @Valid @RequestBody Candidate candidate, BindingResult result) {
	    Candidate newCandidate;
	    Map<String, Object> response = new HashMap<>();
	    if (result.hasErrors()) {
	      response.put(Constants.ERROR, candidateService.listErrors(result));
	      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	    try {
	      newCandidate = candidateService.save(candidate);
	    } catch (DataAccessException e) {
	      response.put(Constants.MESSAGE, Constants.MSG_ERROR_DATABASE);
	      response.put(
	          Constants.ERROR,
	          Objects.requireNonNull(e.getMessage())
	              .concat(": ")
	              .concat(e.getMostSpecificCause().getMessage()));
	      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    response.put(Constants.STATUS, "SUCCESS");
	    response.put(Constants.MESSAGE, "La convocatoria se ha creado correctamente");
	    response.put("candidate", newCandidate);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	  }
	  
		@PutMapping("/editcandidate/{id}")
		public ResponseEntity<Map<String, Object>> update(@RequestBody Candidate candidate, @PathVariable Long id, BindingResult result) {

			Candidate candidateActual = candidateService.findOneCandidate(id);
			Candidate candidateUpdated = null;

			Map<String, Object> response = new HashMap<>();

			if(result.hasErrors()) {

				List<String> errors = result.getFieldErrors()
						.stream()
						.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
						.collect(Collectors.toList());
				
				response.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			
			if (candidateActual == null) {
				response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			try {

				candidateActual.setCandidateName(candidate.getCandidateName());
				candidateActual.setProgrammingLanguage(candidate.getProgrammingLanguage());
				candidateActual.setSalary(candidate.getSalary());
				candidateActual.setEnglish(candidate.getEnglish());
				candidateActual.setSoftSkill(candidate.getSoftSkill());
				
				candidateUpdated = candidateService.save(candidateActual);

			} catch (DataAccessException e) {
				response.put("mensaje", "Error al actualizar el cliente en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			response.put("mensaje", "El cliente ha sido actualizado con éxito!");
			response.put("cliente", candidateUpdated);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}

	  /*delete one candidate */
	  @DeleteMapping("/candidate/{id}")
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	      candidateService.deleteCandidate(id);
	    } catch (DataAccessException e) {
	      response.put(Constants.ERROR, "Error al eliminar el producto de la base de datos");
	      response.put(
	          Constants.ERROR,
	          Objects.requireNonNull(e.getMessage())
	              .concat(": ")
	              .concat(e.getMostSpecificCause().getMessage()));
	      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    response.put(Constants.STATUS, "SUCCESS");
	    response.put(Constants.MESSAGE, "asdasd!");
	    return new ResponseEntity<>(response, HttpStatus.OK);
	  }
}


