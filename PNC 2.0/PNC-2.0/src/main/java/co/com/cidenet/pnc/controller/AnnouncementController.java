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

import co.com.cidenet.pnc.entity.Announcement;
import co.com.cidenet.pnc.exceptions.EntitiesFilterException;
import co.com.cidenet.pnc.exceptions.ObjectMapperException;
import co.com.cidenet.pnc.service.InterfaceAnnouncementService;

import co.com.cidenet.pnc.utils.Constants;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AnnouncementController {

  @Autowired private InterfaceAnnouncementService announcementService;

  /*List all announcements */

  @GetMapping(value = "/announcements")
  public ResponseEntity<List<Announcement>> getAnnouncements(
      @RequestParam Map<String, String> requestParams)
      throws HttpClientErrorException, IllegalAccessException, IllegalArgumentException,
          InvocationTargetException, EntitiesFilterException, ObjectMapperException {
    List<Announcement> announcement;
    Map<String, Object> response = new HashMap<>();
    try {
      announcement = announcementService.findAll();
    } catch (DataAccessException e) {
      response.put("Error", HttpStatus.INTERNAL_SERVER_ERROR);
      response.put(
          "mensaje",
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
      response.put(Constants.MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
      response.put(
          Constants.ERROR,
          Objects.requireNonNull(e.getMessage())
              .concat(": ")
              .concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    if (announcement == null) {
      response.put(
          Constants.MESSAGE,
          "El ID del Anuncio: ".concat(id.toString().concat(Constants.MSG_ERROR_NOT_EXIST)));
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
      newAnnouncement = announcementService.save(announcement);
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
    response.put("announcement", newAnnouncement);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("/editannouncement/{id}")
  public ResponseEntity<Map<String, Object>> update(
      @RequestBody Announcement announcement, @PathVariable Long id, BindingResult result) {

    Announcement announcementActual = announcementService.findOneAnnouncement(id);
    Announcement announcementUpdated = null;

    Map<String, Object> response = new HashMap<>();

    if (result.hasErrors()) {

      List<String> errors =
          result.getFieldErrors().stream()
              .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
              .collect(Collectors.toList());

      response.put("errors", errors);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    if (announcementActual == null) {
      response.put(
          "mensaje",
          "Error: no se pudo editar, el cliente ID: "
              .concat(id.toString().concat(" no existe en la base de datos!")));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    try {

      announcementActual.setAnnouncementName(announcement.getAnnouncementName());
      announcementActual.setJob(announcement.getJob());
      announcementActual.setSalary(announcement.getSalary());
      announcementActual.setStatus(announcement.getStatus());
      announcementActual.setEnglish(announcement.getEnglish());

      announcementUpdated = announcementService.save(announcementActual);

    } catch (DataAccessException e) {
      response.put("mensaje", "Error al actualizar el cliente en la base de datos");
      response.put(
          "error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "El cliente ha sido actualizado con éxito!");
    response.put("cliente", announcementUpdated);

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

  /*delete one announcement */
  @DeleteMapping("/announcement/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
    Map<String, Object> response = new HashMap<>();
    try {
      announcementService.deleteAnnouncement(id);
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
