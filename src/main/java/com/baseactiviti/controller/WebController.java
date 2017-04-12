package com.baseactiviti.controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.IdentityServiceImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.baseactiviti.custom.command.LogCommand;
import com.baseactiviti.server.EngineFindServer;
import com.baseactiviti.server.EngineService;
import com.baseactiviti.util.constant.BPMNConstant;

/**
 * @author zhailz
 *
 *         时间：2017年3月15日 ### 下午12:30:36
 */

@Controller
@RequestMapping("/baseactiviti")
public class WebController {

  private static Logger logger = LoggerFactory.getLogger(WebController.class);

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
  private EngineService engineService;

  @Autowired
  private EngineFindServer engineFindServer;

  /**
   * 登录页面
   * 
   * @param request
   * @param response
   * @param model
   * @return
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
    return "/simple/login";
  }

  /**
   * 验证用户
   */
  @RequestMapping("/checkLogin")
  public String checkLogin(HttpServletRequest request, HttpServletResponse response,
      @RequestParam("userName") String userName, @RequestParam("passWord") String passWord,
      @RequestParam("userName") String userId, Model model) throws Exception {
    logger.debug("logon request: {username={}, password={}}", userName, passWord);
    HttpSession session = request.getSession(true);
    // firstName 作为员工的名称，lastName 作为公司的名称
    User value = identityService.createUserQuery().userId(userId).singleResult();
    if (value != null) {
      session.setAttribute("user", value);
      return "redirect:/baseactiviti/index";
    } else {
      LogCommand log2 = new LogCommand();
      ((IdentityServiceImpl) identityService).getCommandExecutor().execute(log2);
      return "redirect:/baseactiviti/login";
    }
  }

  /**
   * 部署流程文件
   */
  @RequestMapping(value = "/deploy")
  public String deploy(HttpServletRequest request, HttpServletResponse response, Model model,
      @RequestParam(value = "file", required = false) MultipartFile file,
      @RequestParam(value = "orgId", required = false) String orgId) {
    String resourceName = file.getOriginalFilename();
    String proDefName = file.getName();
    try {
      User user = request.getSession(true).getAttribute("user") == null ? null
          : (User) request.getSession(true).getAttribute("user");
      if (null == user) {
        return "redirect:/simple/login";
      }
      model.addAttribute("user", user);
      orgId = user.getLastName();
      if (StringUtils.isBlank(orgId)) {
        model.addAttribute("error", "orgId is null");
        return "/simple/error";
      }

      InputStream inputStream = file.getInputStream();
      ProcessDefinition definiton = engineService.deploy(proDefName, resourceName, inputStream,
          orgId);
      model.addAttribute("definiton", definiton);
    } catch (Exception e) {
      logger.error("流程部署出现错误，请检查流程文件");
      return dealError(model, e.getMessage());
    }
    return "redirect:/simple/index";
  }

  /**
   * 个人任务首页
   * 
   * @return
   */
  @RequestMapping("/index")
  public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

    User user = request.getSession(true).getAttribute("user") == null ? null
        : (User) request.getSession(true).getAttribute("user");

    if (null == user) {
      return "redirect:/simple/login";
    } else {
      model.addAttribute("user", user);
      String orgId = user.getLastName();
      if (StringUtils.isBlank(orgId)) {
        String msg = "orgId is null";
        return dealError(model, msg);
      }
      /**
       * 所有部署的流程
       */
      List<ProcessDefinition> pdList = engineFindServer.getAllProcessDefinition(orgId);
      model.addAttribute("pdList", pdList);
      /**
       * 该用户所有可以认领的任务
       */
      List<Task> userTasks = engineFindServer.getAllTask(user.getId());
      model.addAttribute("userTasks", userTasks);

      /**
       * 查看任务实例
       */
      List<Task> taskList = engineFindServer.getAllTask(orgId);
      model.addAttribute("taskList", taskList);
      /**
       * 历史流程
       */
      List<HistoricProcessInstance> hpiList = engineFindServer.getAllHistoricProcessInstance(orgId);
      List<HistoricTaskInstance> hpTaskIns = engineFindServer.getAllHistoricTaskInstance(orgId);
      model.addAttribute("hpiList", hpiList);
      model.addAttribute("hpTaskIns", hpTaskIns);
    }

    return "/simple/index";
  }

  private String dealError(Model model, String msg) {
    model.addAttribute("error", msg);
    return "/simple/error";
  }

}
