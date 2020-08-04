package co.com.cidenet.pnc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
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

import co.com.cidenet.pnc.entity.Announcement;

import co.com.cidenet.pnc.service.InterfaceAnnouncementService;

import co.com.cidenet.pnc.utils.Constants;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AnnouncementController {

  @Autowired private InterfaceAnnouncementService announcementService;
  static final Logger logger = Logger.getLogger(AnnouncementController.class);

  /*List all announcements */

  @GetMapping(value = "/announcements")
  public ResponseEntity<List<Announcement>> getAnnouncements(
      @RequestParam Map<String, String> requestParams) {
    Map<String, Object> response = new HashMap<>();
    try {
      announcementService.findAll();
    } catch (DataAccessException e) {
      logger.error(e);	
      response.put(Constants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
      response.put(
          Constants.MESSAGE,
          Objects.requireNonNull(e.getMessage())
              .concat(": ")
              .concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return ResponseEntity.ok().body(announcementService.findAll());
  }

  /*list one announcement by ID, if the id didn't exist then return a error message*/
  @GetMapping("/announcement/{id}")
  public ResponseEntity<Announcement> show(@PathVariable Long id) {
    Announcement announcement;
    Map<String, Object> response = new HashMap<>();
    try {
    	announcement = announcementService.findOneAnnouncement(id);
    } catch (DataAccessException e) {
    	logger.error(e);		
    	response.put(Constants.MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    	response.put(
          Constants.ERROR,
          Objects.requireNonNull(e.getMessage())
              .concat(": ")
              .concat(e.getMostSpecificCause().getMessage()));
    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    if (announcement == null) {
    	logger.error(announcement);	
    	response.put(
          Constants.MESSAGE,
          Constants.ANNOUNCEMENT_ID.concat(id.toString().concat(Constants.MSG_ERROR_NOT_EXIST)));
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(announcement, HttpStatus.OK);
  }

  /*Create one announcement, it verify if the data are valid or if exist a connection to database, else save the announcement*/
  @PostMapping(value = "/createannouncement")
  public ResponseEntity<Map<String, Object>> create(
      @Valid @RequestBody Announcement announcement, BindingResult result) {

    Announcement newAnnouncement;
    Map<String, Object> response = new HashMap<>();
    if (result.hasErrors()) {
      response.put(Constants.ERROR, announcementService.listErrors(result));
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    try {
      announcementService.isValidSalary(announcement);
      announcementService.isValidEnglish(announcement);
      newAnnouncement = announcementService.save(announcement);   
    } catch (DataAccessException e) {
      logger.error(e);		
      response.put(Constants.MESSAGE, Constants.MSG_ERROR_DATABASE);
      response.put(
          Constants.ERROR,
          Objects.requireNonNull(e.getMessage())
              .concat(": ")
              .concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    catch (Exception e) {
        logger.error(e);		
        response.put(Constants.ERROR,Objects.requireNonNull(e.getMessage()));
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
      }    
    
    response.put(Constants.STATUS, Constants.SUCCESSFULL);
    response.put(Constants.MESSAGE, Constants.CREATE_ANNOUNCEMENT);
    response.put("announcement", newAnnouncement);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("/editannouncement/{id}")
  public ResponseEntity<Map<String, Object>> update(
		  @Valid @RequestBody Announcement announcement, BindingResult result, @PathVariable Long id) {

    Announcement announcementActual = announcementService.findOneAnnouncement(id);
    Announcement announcementUpdated;
    Map<String, Object> response = new HashMap<>();
    if (result.hasErrors()) {
        response.put("esta fallando aca", announcementService.listErrors(result));
        logger.error(response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    if (announcementActual == null) {
      response.put(Constants.MESSAGE,Constants.ERROR_EDITING_CANDIDATE.concat(
      id.toString().concat(Constants.MSG_ERROR_NOT_EXIST)));
      logger.error(response);
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    try {
        if (announcementService.isValidSalary(announcement) && announcementService.isValidEnglish(announcement)){
            announcementActual.setAnnouncementName(announcement.getAnnouncementName());
            announcementActual.setJob(announcement.getJob());
            announcementActual.setSalary(announcement.getSalary());
            announcementActual.setStatus(announcement.getStatus());
            announcementActual.setEnglish(announcement.getEnglish());

            announcementUpdated = announcementService.save(announcementActual);
        } else {
            response.put(Constants.ERROR,Constants.VALIDATE_FIELDS);
            logger.error(response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }    	

    } catch (DataAccessException e) {
      logger.error(e);		
      response.put(Constants.MESSAGE, Constants.ERROR_EDITING_CANDIDATE);
      response.put(Constants.ERROR,
          e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put(Constants.MESSAGE, Constants.EDIT_CANDIDATE);
    response.put(Constants.ANNOUNCEMENT, announcementUpdated);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  /*delete one announcement */
  @DeleteMapping("/announcement/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
    Map<String, Object> response = new HashMap<>();
    try {
      announcementService.deleteAnnouncement(id);
    } catch (DataAccessException e) {
      logger.error(e);		
      response.put(Constants.ERROR, Constants.MSG_ERROR_DATABASE);
      response.put(
          Constants.ERROR,
          Objects.requireNonNull(e.getMessage())
              .concat(": ")
              .concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    response.put(Constants.STATUS, Constants.SUCCESSFULL);
    response.put(Constants.MESSAGE, Constants.DELETE_ANNOUNCEMENT);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
