package com.baseactiviti.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhailz
 *
 *         时间：2017年3月15日 ### 下午12:30:36
 */

@Controller
@RequestMapping("/baseactiviti")
public class WebController {

  @Autowired
  protected RepositoryService repositoryService;

  @Autowired
  protected RuntimeService runtimeService;

  @Autowired
  protected TaskService taskService;

  @Autowired
  protected HistoryService historyService;

  @Autowired
  protected IdentityService identityService;

  @Autowired
  ProcessEngineFactoryBean processEngine;

  @Autowired
  protected FormService formService;

  @RequestMapping(value = "/state", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> state(HttpServletRequest httpServletRequest) throws IOException {
    List<ProcessDefinition> wfpeodef = repositoryService.createProcessDefinitionQuery().list();
    return new ResponseEntity<Object>(wfpeodef, HttpStatus.OK);
  }

  @RequestMapping(value = "/gpf", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getProcessDefinition(HttpServletRequest httpServletRequest,
      @RequestParam(value = "from", required = true) String from,
      @RequestParam(value = "to", required = true) String to) throws Exception {
    List<ProcessDefinition> wfpeodef = repositoryService.createProcessDefinitionQuery().list();
    return new ResponseEntity<Object>(wfpeodef, HttpStatus.OK);
  }
}
