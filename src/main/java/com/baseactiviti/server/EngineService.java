package com.baseactiviti.server;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baseactiviti.util.constant.BPMNConstant;
import com.baseactiviti.util.exception.BaseActivitiException;

@Service("engineService")
public class EngineService {

  private Logger log = LoggerFactory.getLogger(getClass());
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

  @Autowired
  private EngineFindServer engineFindServer;

  /**
   * 部署流程定义
   * 
   * @param userId
   *          部署人员
   * @param orgId
   *          该流程定义对应的企业
   * @param workFlowDefinitionText
   *          流程定义的string
   * @param prodefName
   *          流程定义的名称
   * @param resourceName
   *          资源的名称
   * @throws BaseActivitiException
   */
  public ProcessDefinition deployWorkFlow(String userId, String orgId,
      String workFlowDefinitionText, String prodefName, String resourceName)
      throws BaseActivitiException {
    log.debug("部署流程,para userId:{},orgId:{},prodefName:{},resourceName:{}", userId, orgId,
        prodefName, resourceName);
    if (StringUtils.isBlank(workFlowDefinitionText) || StringUtils.isBlank(resourceName)) {
      throw BaseActivitiException.ParaError;
    }
    if (!BPMNConstant.isBpmnResource(resourceName)) {
      resourceName = resourceName + BPMNConstant.BPMN_RESOURCE_SUFFIXES[0];
    }
    // 校验流程定义文件
    boolean value = BPMNConstant.validate(workFlowDefinitionText);
    if (value) {
      Deployment de = repositoryService.createDeployment().tenantId(orgId).name(prodefName)
          .addString(resourceName, workFlowDefinitionText).deploy();
      /**
       * 检验部署是够成功
       */
      ProcessDefinition prodef = repositoryService.createProcessDefinitionQuery().deploymentId(de
          .getId()).singleResult();
      return prodef;
    }
    return null;
  }

  /**
   * 部署流程
   * 
   * @param proDefName
   *          流程名称
   * @param resourceName
   *          流程定义对应的资源名称（按照bpmn20.xml或者 bpmn结尾）
   * @param newinputStream
   *          流
   * @param orgId
   *          企业标识
   * @throws BaseActivitiException
   */
  public ProcessDefinition deploy(String proDefName, String resourceName,
      InputStream newinputStream, String orgId) throws BaseActivitiException {
    // 校验流程定义文件
    boolean value = BPMNConstant.validate(newinputStream);
    if (value) {
      Deployment de = repositoryService.createDeployment().addInputStream(resourceName,
          newinputStream).name(proDefName).tenantId(orgId).deploy();
      // 检验部署是够成功
      ProcessDefinition prodef = repositoryService.createProcessDefinitionQuery().deploymentId(de
          .getId()).singleResult();
      return prodef;
    }
    return null;
  }

}
