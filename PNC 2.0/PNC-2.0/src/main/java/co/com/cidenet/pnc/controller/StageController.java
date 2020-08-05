/**
 * @author Jaime Mejia
 * @version 1.0
 * @since 4/08/2020
 */
package co.com.cidenet.pnc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cidenet.pnc.entity.Stage;
import co.com.cidenet.pnc.service.InterfaceStageService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StageController {

  @Autowired private InterfaceStageService stageService;
  static final Logger logger = Logger.getLogger(StageController.class);

  @GetMapping(value = "/stages")
  public Iterable<Stage> toList() {
    return stageService.findAll();
  }

  @GetMapping("/stage/{id}")
  public Stage toListById(@PathVariable("id") Long id) {
    return stageService.findOneStage(id);
  }

  @PostMapping(value = "/createstage")
  public Stage add(@RequestBody Stage stage) {
    return stageService.save(stage);
  }

  @PutMapping("/editstage/{id}")
  public Stage edit(@RequestBody Stage stage, @PathVariable("id") Long id) {
    stage.setId(id);
    return stageService.save(stage);
  }

  @DeleteMapping("/stage/{id}")
  public void delete(@PathVariable("id") Long id) {
    stageService.deleteById(id);
  }
}
