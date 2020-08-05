/**
 * @author Jaime Mejia
 * @version 1.0
 * @since 4/08/2020
 */
package co.com.cidenet.pnc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.cidenet.pnc.entity.Stage;
import co.com.cidenet.pnc.repository.StageRepository;

@Service
public class StageServiceImplement implements InterfaceStageService {

  @Autowired private StageRepository stageRepository;

  public StageServiceImplement(StageRepository stageRepository) {
    this.stageRepository = stageRepository;
  }

  @Override
  @Transactional
  public List<Stage> findAll() {
    return (List<Stage>) stageRepository.findAll();
  }

  @Override
  @Transactional
  public Stage save(Stage stage) {
    return stageRepository.save(stage);
  }

  @Override
  public Stage findOneStage(Long id) {
    return stageRepository.findById(id).orElse(null);
  }

  @Override
  public void deleteById(Long id) {
    stageRepository.deleteById(id);
  }
}
