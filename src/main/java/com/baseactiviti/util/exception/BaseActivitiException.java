package com.baseactiviti.util.exception;

/**
 * @author zhailz
 *
 *         时间：2017年4月7日 ### 上午9:54:33
 */
public class BaseActivitiException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public static final BaseActivitiException ParaError = new BaseActivitiException(10001,
      "参数缺失或者不正确");
  public static final BaseActivitiException WFDefinitionValidateError = new BaseActivitiException(
      10002, "流程定义文件校验错误");

  public int getCode() {
    return code;
  }

  private int code;
  private String msg;

  public BaseActivitiException(int code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }

  public BaseActivitiException(BaseActivitiException e) {
    this.code = e.code;
    this.msg = e.msg;
  }

  protected BaseActivitiException setCode(int code) {
    this.code = code;
    return this;
  }

  public String getMsg() {
    return msg;
  }

  protected BaseActivitiException setMsg(String msg) {
    this.msg = msg;
    return this;
  }

}
