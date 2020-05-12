/** */
package co.com.cidenet.pnc.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import co.com.cidenet.pnc.entity.Announcement;

import co.com.cidenet.pnc.repository.AnnouncementRepository;

/** @author jmejia */
@Service
public class AnnouncementServiceImplement implements InterfaceAnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

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
    //@Transactional(readOnly = true)
    public Announcement findOneAnnouncement(Long id) {
        return announcementRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }

    public Object listErrors(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
