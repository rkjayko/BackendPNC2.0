/** */
package co.com.cidenet.pnc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import co.com.cidenet.pnc.entity.Announcement;
import co.com.cidenet.pnc.entity.Announcement.English;
import co.com.cidenet.pnc.entity.Announcement.Job;
import co.com.cidenet.pnc.repository.AnnouncementRepository;

/** @author jmejia */
@Service
public class AnnouncementServiceImplement implements InterfaceAnnouncementService {

  @Autowired private AnnouncementRepository announcementRepository;

  public AnnouncementServiceImplement(AnnouncementRepository announcementRepository) {
    this.announcementRepository = announcementRepository;
  }

  @Override
  @Transactional
  public List<Announcement> findAll() {
    return (List<Announcement>) announcementRepository.findAll();
  }

  @Override
  @Transactional
  public Announcement save(Announcement announcement) {
    return announcementRepository.save(announcement);
  }

  @Override
  // @Transactional(readOnly = true)
  public Announcement findOneAnnouncement(Long id) {
    return announcementRepository.findById(id).orElse(null);
  }

  @Override
  public void deleteAnnouncement(Long id) {
    announcementRepository.deleteById(id);
  }
  
  //@Transactional(readOnly = true)
  public boolean isValidSalary(Announcement announcement) {
	    Map<String, Object> response = new HashMap<>();
	  if((announcement.getSalary() < 1000000  && announcement.getJob() == Job.JAVA) 
			  || (announcement.getSalary() < 2000000 && announcement.getJob() == Job.PLSQL)
			  || (announcement.getSalary() < 3000000 && announcement.getJob() == Job.GROOVY)
			  || (announcement.getSalary() < 3000000 && announcement.getJob() == Job.SWIFT)) {
		  return true;
	  }
      	throw new IllegalArgumentException("El salario es mayor al pedido");
  }
  
  //@Transactional(readOnly = true)
  public boolean isValidEnglish(Announcement announcement) {
	  if((announcement.getEnglish() == English.NO  && (announcement.getJob() == Job.GROOVY || announcement.getJob() == Job.SWIFT))){
	    	throw new IllegalArgumentException("Se necesita ingles para esta convocatoria");
	  }
	  	return true;
  }  
      
  public Object listErrors(BindingResult result) {
    return result.getFieldErrors().stream()
        .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
        .collect(Collectors.toList());
  }
}
