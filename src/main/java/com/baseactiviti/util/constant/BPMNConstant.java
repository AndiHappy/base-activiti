package com.baseactiviti.util.constant;

import java.io.IOException;
import java.io.InputStream;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.converter.util.InputStreamProvider;
import org.activiti.engine.impl.util.io.InputStreamSource;
import org.activiti.engine.impl.util.io.StringStreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

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

  /**
   * 校验流程定义
   * 
   * @param workFlowResourse
   *          流程定义的资源（String类型，InputStream流类型,MultipartFile类型）
   */
  public static boolean validate(Object workFlowResourse) throws BaseActivitiException {
    InputStreamProvider inputStreamProvider = null;
    try {
      if (workFlowResourse instanceof String) {
        inputStreamProvider = new StringStreamSource((String) workFlowResourse);
      } else if (workFlowResourse instanceof InputStream) {
        inputStreamProvider = new InputStreamSource((InputStream) workFlowResourse);
      } else if (workFlowResourse instanceof MultipartFile) {
        InputStream inputStream = ((MultipartFile) workFlowResourse).getInputStream();
        inputStreamProvider = new InputStreamSource(inputStream);
      } else {
        throw BaseActivitiException.Validate_UnSupport_Type_Resource;
      }
      BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
      bpmnXMLConverter.convertToBpmnModel(inputStreamProvider, true, true);
      return true;
    } catch (Throwable e) {
      log.error("parse bpmn file error:{}", e);
      throw BaseActivitiException.WFDefinition_Validate_Error;
    } finally {
      if (inputStreamProvider instanceof InputStreamSource) {
        try {
          inputStreamProvider.getInputStream().close();
        } catch (IOException e) {
        }
      }
    }
  }
}
