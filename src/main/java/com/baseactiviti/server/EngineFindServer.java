package com.baseactiviti.server;

import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("engineFindServer")
public class EngineFindServer {

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

  public List<ProcessDefinition> getAllProcessDefinition(String orgId) {
    return repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(orgId).list();
  }

  public List<Task> getAllTask(String orgId) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<HistoricProcessInstance> getAllHistoricProcessInstance(String orgId) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<HistoricTaskInstance> getAllHistoricTaskInstance(String orgId) {
    // TODO Auto-generated method stub
    return null;
  }

}
