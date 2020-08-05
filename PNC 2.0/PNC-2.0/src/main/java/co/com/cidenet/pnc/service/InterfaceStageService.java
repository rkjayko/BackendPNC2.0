/**
 * @author Jaime Mejia
 * @version 1.0
 * @since 4/08/2020
 */
package co.com.cidenet.pnc.service;


import co.com.cidenet.pnc.entity.Stage;

public interface InterfaceStageService {

  public Iterable<Stage> findAll();

  Stage findOneStage(Long id);

  public Stage save(Stage stage);

  public void deleteById(Long id);
}
