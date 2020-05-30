package co.com.cidenet.pnc.repository;

/** @author jmejia */
import org.springframework.data.repository.CrudRepository;

import co.com.cidenet.pnc.entity.Announcement;

public interface AnnouncementRepository extends CrudRepository<Announcement, Long> { }
