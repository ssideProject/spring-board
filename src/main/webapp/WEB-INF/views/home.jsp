<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Home</title>
<%@ include file="include/header.jsp" %>
</head>
<body>
<%@ include file="include/menu.jsp" %>
    <c:if test="${msg == 'success'}">
    	<h2>${sessionScope.name}(${sessionScope.id})님 환영합니다.</h2>
    </c:if>
    <h1><a href = "${path }/board/list.do"> 게시판 입쟝!</a></h1>
</body>
</html>