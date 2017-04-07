package com.baseactiviti.server;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EngineServiceTest {

  @Resource
  private EngineService engineService;

  @Test
  public void testDeployWorkFlow() {
    System.out.println(engineService.toString());
  }

}
