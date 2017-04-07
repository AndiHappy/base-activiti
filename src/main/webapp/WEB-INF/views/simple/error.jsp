<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="head.jsp"></jsp:include>
<div class="wrap">
    <div class="container">
       <p>error: "${error}"</p>
    </div>
</div>
<jsp:include page="foot.jsp"></jsp:include>