package com.baseactiviti.util.constant;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.converter.util.InputStreamProvider;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.impl.util.io.StringStreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baseactiviti.util.exception.BaseActivitiException;

/**
 * @author zhailz
 *
 *         时间：2017年4月7日 ### 上午10:21:57
 */
public class BPMNConstant {

  public static final String[] BPMN_RESOURCE_SUFFIXES = new String[] { "bpmn20.xml", "bpmn" };
  private static Logger log = LoggerFactory.getLogger(BPMNConstant.class);

  public static boolean isBpmnResource(String resourceName) {
    for (String suffix : BPMN_RESOURCE_SUFFIXES) {
      if (resourceName.endsWith(suffix)) {
        return true;
      }
    }
    return false;
  }

  public static boolean validate(String workFlowDefinitionText) throws BaseActivitiException {
    try {
      BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
      InputStreamProvider inputStreamProvider = new StringStreamSource(workFlowDefinitionText);
      bpmnXMLConverter.convertToBpmnModel(inputStreamProvider, true, true);
      return true;
    } catch (Throwable e) {
      log.error("parse bpmn file error:{}", e);
      throw BaseActivitiException.WFDefinitionValidateError;
    }
  }

}
