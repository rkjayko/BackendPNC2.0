package co.com.cidenet.pnc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cidenet.pnc.entity.Announcement;
import co.com.cidenet.pnc.service.InterfaceAnnouncementService;


@RestController
@RequestMapping("/api")
public class AnnouncementController {
	
    @Autowired
    private InterfaceAnnouncementService announcementService;
	
    /*List all announcements */
   
	@GetMapping(value = "/announcements")
    public ResponseEntity<List<Announcement>> index() {
        List<Announcement> announcement;
        Map<String, Object> response = new HashMap<>();
        try {
           announcement = announcementService.findAll();
        } catch (DataAccessException e) {
            response.put("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put("mensaje", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(announcement, HttpStatus.OK);
    }

}
