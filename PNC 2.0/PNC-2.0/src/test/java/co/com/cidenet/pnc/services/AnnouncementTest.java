package co.com.cidenet.pnc.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ObjectInputFilter.Status;
import java.util.Optional;

import co.com.cidenet.pnc.entity.Announcement;
import co.com.cidenet.pnc.entity.Announcement.English;
import co.com.cidenet.pnc.entity.Announcement.Job;
import co.com.cidenet.pnc.repository.AnnouncementRepository;
import co.com.cidenet.pnc.service.AnnouncementServiceImplement;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnnouncementTest {

  @Mock AnnouncementRepository announcementRepository;

  @InjectMocks AnnouncementServiceImplement announcementService;

  @BeforeAll
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createAnnouncementTest() {
    Announcement announcementOne = new Announcement();
    announcementOne.setId(1L);
    announcementOne.setJob(null);
    when(announcementRepository.save(any(Announcement.class))).thenReturn(announcementOne);
    Announcement announcementFinal = announcementService.save(announcementOne);
    verify(announcementRepository, times(1)).save(announcementOne);
    assertNotNull(announcementFinal);
  }
  
  @Test
  public void findAnnouncementByIdAnnouncementExistTest() {
	  Announcement announcementSaved = new Announcement();
	  announcementSaved.setAnnouncementName("Testing Announcement");
	  announcementSaved.setId(1L);
	  announcementSaved.setEnglish(English.YES);
	  announcementSaved.setJob(Job.JAVA);
	  announcementSaved.setSalary(90000);
      when(announcementRepository.findById(announcementSaved.getId())).thenReturn(Optional.of(announcementSaved));
      Announcement productTest = announcementService.findOneAnnouncement(announcementSaved.getId());
      verify(announcementRepository, times(1)).findById(announcementSaved.getId());
      assertEquals(announcementSaved, productTest);
  } 
  
  @Test(expected = IllegalArgumentException.class) 
  public void createAnnouncementWithSalaryInvalidTest(){
	  Announcement announcementSaved = new Announcement();
	  announcementSaved.setAnnouncementName("Testing Announcement");
	  announcementSaved.setId(1L);
	  announcementSaved.setEnglish(English.YES);
	  announcementSaved.setJob(Job.JAVA);
	  announcementSaved.setSalary(900000000);
      announcementService.isValidSalary(announcementSaved);
  }
  
  @Test(expected = IllegalArgumentException.class) 
  public void createAnnouncementWithEnglishInvalidTest(){
	  Announcement announcementSaved = new Announcement();
	  announcementSaved.setAnnouncementName("Testing Announcement");
	  announcementSaved.setId(1L);
	  announcementSaved.setEnglish(English.NO);
	  announcementSaved.setJob(Job.SWIFT);
	  announcementSaved.setSalary(1000000);
      announcementService.isValidEnglish(announcementSaved);
  }  
}
