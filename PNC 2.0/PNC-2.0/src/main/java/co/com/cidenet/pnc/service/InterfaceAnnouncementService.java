/** */
package co.com.cidenet.pnc.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import co.com.cidenet.pnc.entity.Announcement;

/** @author jmejia */
public interface InterfaceAnnouncementService {

  List<Announcement> findAll();

  Announcement save(Announcement announcement);

  Announcement findOneAnnouncement(Long id);

  void deleteAnnouncement(Long id);

  Object listErrors(BindingResult result);
}
