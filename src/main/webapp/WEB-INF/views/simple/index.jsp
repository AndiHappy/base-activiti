<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="head.jsp"></jsp:include>
<meta name="viewport" content="width=device-width, initial-scale=1,width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<div class="container">
    <div class="row">
     <div class="col-md-2">
     	 <div class="panel panel-primary">
     	  <div class="panel-heading">上传流程定义</div>
		  <div class="panel-body">
		  <a href="#uploadpi">上传流程定义</a>
		  </div>
		 </div>
		 <div class="panel panel-primary">
		  <div class="panel-heading">流程定义列表</div>
		  <div class="panel-body">
		  <a href="#pilist">流程定义列表</a>
		  </div>
		 </div>
		 <div class="panel panel-primary">
		 <div class="panel-heading">认领任务</div>
		  <div class="panel-body">
		    <a href="#claimtasklist">认领任务</a>
		  </div>
		 </div>
		 <div class="panel panel-primary">
		 <div class="panel-heading">待办任务</div>
		  <div class="panel-body">
		    <a href="#tasklist">待办任务</a>
		  </div>
		 </div>
		 <div class="panel panel-primary">
		 <div class="panel-heading">任务流转状况</div>
		  <div class="panel-body">
		    <a href="#taskhistory">任务流转状况</a>
		  </div>
		 </div>
		 <div class="panel panel-primary">
		 <div class="panel-heading">历史流程</div>
		  <div class="panel-body">
		    <a href="#taskhistory">历史流程</a>
		  </div>
		 </div>
     </div>
     <div class="col-md-9">
	     <div id="uploadpi">
	         <form action="${path}/baseactiviti/deploy" method="post" enctype="multipart/form-data" class="form-inline">
	             <div class="fileinput fileinput-new" data-provides="fileinput">
				  <div class="input-group">
				    <div class="form-control span3" data-trigger="fileinput"><i class="glyphicon glyphicon-file fileinput-exists"></i> <span class="fileinput-filename"></span></div>
				    <span class="input-group-addon btn btn-default btn-file"><span class="fileinput-new">选择文件</span><span class="fileinput-exists">重选</span><input type="file" name="file"></span>
				    <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">移除</a>
				    <button type="submit" class="btn btn-default">提交流程定义</button>
				  </div>
				</div>
	         </form>
	     </div>
	
	
	     <!-- 流程列表-->
	     <div id="pilist">
	        <table class="table table-bordered table-hover">
	            <caption><strong>流程定义</strong></caption>
	            <thead>
	            <tr>
	                <th>流程ID</th>
	                <th>流程名称</th>
	                <th>流程版本</th>
	                <th>操作</th>
	            </tr>
	            </thead>
	            <tbody>
	            <c:forEach var="pd" items="${pdList}"  >
	                <tr>
	                    <td>${pd.id}</td>
	                    <td>${pd.name}</td>
	                    <td>${pd.version}</td>
	                    <td>
	                    	<a href="${path}/baseactiviti/start?processDefId=${pd.id}&userId=${user.id}&orgId=${user.revision}&resId=${user.id}">启动流程</a>
	                    	&nbsp;|&nbsp;
	                        <a href="${path}/baseactiviti/viewprocessDef?processDefId=${pd.id}" target="_blank">查看流程定义</a>
	                        &nbsp;|&nbsp;
	                        <a href="javascript:showWin(800,600,'${path}/baseactiviti/viewprocessDefImage?processDefId=${pd.id}'
	                        ,'tag');">流程图</a>                   
	                        &nbsp;|&nbsp;
	                        <a href="${path}/baseactiviti/remove?deployId=${pd.deploymentId}">删除</a>
	                    </td>
	                </tr>
	            </c:forEach>
	            </tbody>
	        </table>
	     </div>
	     
	     <script type="text/javascript">
	     
	   //w:宽，h:高，url：地址，tag：标记 
	     function showWin(w, h, url, tag) { 
	     var t = (screen.height - h) / 2; //离顶部距离 
	     var l = (screen.width - w) / 2; //离左边距离 
	     window.open(url, tag, "width=" + w + ",height=" + h + ",top=" + t + ",left=" + l + ",location=0"); 
	     } 
	     
	   //路径地址，window对象，宽，高 
	     function open_Dialog(url, win,Width, Height) { 
	     var return_Value; 
	     var iTop2 = (window.screen.availHeight - 20 - Height) / 2; 
	     var iLeft2 = (window.screen.availWidth - 10 - Width) / 2; 
	     var height2 = Height - 50; 
	     if (document.all && window.print) { 
	     return_Value = window.showModalDialog(url, win, "dialogLeft:" + iLeft2 + "px;dialogTop:" + iTop2 + "px;dialogWidth:" + 
	     Width + "px;dialogHeight:" + Height + "px;center:yes;status:no;scroll:no;help:no;"); 
	     } 
	     else { 
	     window.open(url, win, "top=" + iTop2 + ",left=" + iLeft2 + ",width=" + Width + "px,height=" + height2 + 
	     "px,resizable=0,scrollbars=0,location=0"); 
	     } 
	     }
</script>
		
		<!-- Modal -->
		<div class="modal fade" id="pdfModal" tabindex="-1" role="dialog" aria-labelledby="pdfModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="pdfModalLabel">请假流程图</h4>
		      </div>
		      <div class="modal-body">
		        
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->	
		
	     <!--可认领任务列表 -->
	     <c:if test="${fn:length(groupTasks)>0}">
	     <div id="claimtasklist">
	         <table  class="table table-bordered table-hover">
	             <caption><h3><strong>可认领任务</strong></h3></caption>
	             <thead>
	             <tr>
	                 <th>任务ID</th>
	                 <th>任务名称</th>
	                 <th>&nbsp;</th>
	             </tr>
	             </thead>
	             <tbody>
	
	             <c:forEach var="task" items="${groupTasks}"  >
	                 <tr>
	                     <td>${task.id}</td>
	                     <td>${task.name}</td>
	                     <td><a href="${path}/baseactiviti/claim/${task.id}?userId=${user.id}">认领</a></td>
	                 </tr>
	             </c:forEach>
	             </tbody>
	         </table>
	     </div>
	     </c:if>
	
	    <!-- 待办任务列表-->
	    <c:if test="${fn:length(userTasks)>0}">
	        <div id="tasklist">
	             <table  class="table table-bordered table-hover">
	                 <caption><h3><strong>待办任务</strong></h3></caption>
	                 <thead>
	                 <tr>
	                     <th>任务ID</th>
	                     <th>创建时间</th>
	                     <th>代办人</th>
	                     <th>企业</th>
	                     <th>任务名称</th>
	                     <th>流程实例ID</th>
	                     <th>操作</th>
	                 </tr>
	                 </thead>
	                 <tbody>
	
	                 <c:forEach var="task" items="${userTasks}"  >
	                     <tr>
	                         <td>${task.id}</td>
	                         <td>${task.createTime}</td>
	                         <td>${task.assignee}</td>
	                         <td>${task.tenantId } </td>
	                         <td>${task.name}</td>
	                         <td>${task.processInstanceId}</td>
	                         <td><a href="${path}/baseactiviti/form?taskId=${task.id}">执行</a></td>
	                     </tr>
	                 </c:forEach>
	                 </tbody>
	             </table>
	        </div>
	    </c:if>
	
	     <div id="taskinfo">
	         <table  class="table table-bordered table-hover">
	             <caption><h3><strong>任务流转状况</strong></h3></caption>
	             <thead>
	             <tr>
	                 <th>流程实例ID</th>
	                 <th>当前任务ID</th>
	                 <th>当前任务</th>
	                 <th>任务执行人</th>
	                 <th>查看</th>
	             </tr>
	             </thead>
	             <tbody>
	             <c:forEach var="task" items="${taskList}"  >
	                 <tr>
	                     <td>${task.processInstanceId}</td>
	                     <td>${task.id}</td>
	                     <td>${task.name}</td>
	                     <td><c:out value="${task.assignee}" default="未认领"/></td>
	                     <td><a href="${path}/baseactiviti/view?executionId=${task.executionId}" target="_blank">流程图</a></td>
	                 </tr>
	             </c:forEach>
	             </tbody>
	         </table>
	     </div>
	
	     <div id="taskhistory">
	         <table  class="table table-bordered table-hover">
	             <caption><h3><strong>历史流程</strong></h3></caption>
	             <thead>
	             <tr>
	                 <th>id</th>
	                 <th>发起人</th>
	                 <th>开始时间</th>
	                 <th>结束时间</th>
	             </tr>
	             </thead>
	             <tbody>
	             <c:forEach var="hpi" items="${hpiList}">
	                 <tr>
	                     <td>${hpi.id}</td>
	                     <td>${hpi.startUserId}</td>
	                     <td><fmt:formatDate value="${hpi.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                     <td><fmt:formatDate value="${hpi.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                 </tr>
	             </c:forEach>
	             </tbody>
	         </table>
	     </div>
	     
	     <div id="taskinstancehistory">
	         <table  class="table table-bordered table-hover">
	             <caption><h3><strong>历史任务</strong></h3></caption>
	             <thead>
	             <tr>
	                 <th>id</th>
	                 <th>审批人</th>
	                 <th>claimTime时间</th>
	                 <th>结束时间</th>
	                 <th>结束原因</th>
	                 <th>关联的流程实例ID</th>
	             </tr>
	             </thead>
	             <tbody>
	              <c:forEach var="hptask" items="${hpTaskIns}">
	                 <tr>
	                     <td>${hptask.id}</td>
	                     <td>${hptask.assignee}</td>
	                     <td><fmt:formatDate value="${hptask.claimTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                     <td><fmt:formatDate value="${hptask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                     <td>${hptask.deleteReason}</td>
	                     <td>${hptask.processInstanceId}</td>
	                 </tr>
	             </c:forEach>
	             </tbody>
	         </table>
	     </div>
	</div>
</div>    
</div>
<jsp:include page="foot.jsp"></jsp:include>