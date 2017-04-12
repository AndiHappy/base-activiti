package com.baseactiviti.server;

import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

@Service("engineFindServer")
public class EngineFindServer {

  public List<ProcessDefinition> getAllProcessDefinition(String orgId) {
    // TODO Auto-generated method stub
    return null;
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
