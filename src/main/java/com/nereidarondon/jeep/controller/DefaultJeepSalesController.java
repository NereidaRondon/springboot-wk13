/**
 * 
 */
package com.nereidarondon.jeep.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nereidarondon.jeep.entity.Jeep;
import com.nereidarondon.jeep.entity.JeepModel;
import lombok.extern.slf4j.Slf4j;

/**
 * This is connected to the interface
 *Have to tell SpringBoot that this controller is a rest controller that implements JeepSalesController
 */
@RestController
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController {

  @Override
  public List<Jeep> fetchJeeps(@RequestParam JeepModel model, @RequestParam String trim) {
    log.info("model={}, trim={}", model, trim);
    return null;
  }

}
