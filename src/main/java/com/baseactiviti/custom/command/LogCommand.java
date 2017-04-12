package com.baseactiviti.custom.command;

import java.io.Serializable;

import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class LogCommand implements Command<UserQuery>, Serializable {

  private static final long serialVersionUID = 3501641497452997071L;

  @Override
  public UserQuery execute(CommandContext commandContext) {
    System.out.println(commandContext);
    DbSqlSession dbo = commandContext.getDbSqlSession();
    String value = dbo.createExecutionQuery().getActivityId();
    // commandContext.getProcessEngineConfiguration().getTaskService().complete(taskId);
    System.out.println(value);
    return null;
  }

}