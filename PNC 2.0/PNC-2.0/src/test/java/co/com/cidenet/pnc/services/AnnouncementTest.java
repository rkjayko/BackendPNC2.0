package co.com.cidenet.pnc.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import co.com.cidenet.pnc.entity.Announcement;
import co.com.cidenet.pnc.repository.AnnouncementRepository;
import co.com.cidenet.pnc.service.AnnouncementServiceImplement;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AnnouncementTest {

    @Mock
    AnnouncementRepository announcementRepository;

    @InjectMocks
    AnnouncementServiceImplement announcementService ;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createProductTest() {
        Announcement announcementOne = new Announcement();
        announcementOne.setId(1L);
        announcementOne.setJob(null);
        when(announcementRepository.save(any(Announcement.class))).thenReturn(announcementOne);
        Announcement announcementFinal = announcementService.save(announcementOne);
        verify(announcementRepository, times(1)).save(announcementOne);
        assertNotNull(announcementFinal);
    }
}
