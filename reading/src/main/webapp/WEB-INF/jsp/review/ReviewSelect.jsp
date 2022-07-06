<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimun-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>책공간</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>

<div class="container">
	
	
	제목 : ${result.reviewSj} 
	
	작성자 id : ${result.frstRegisterId} 
	
	내용 : ${result.reviewCn}
	
	조회수 : ${result.inqireCo}
	
	작성일 : <fmt:formatDate value="${result.frstRegistPnttm}" pattern="yyyy-MM-dd" /> 
	
	<c:url var="uptUrl" value="/review/reviewRegist.do">
		<c:param name="reviewId" value="${result.reviewId}" />
	</c:url>
	<a href="${uptUrl}">수정</a>
	
	<c:url var="delUrl" value="/review/delete.do">
		<c:param name="reviewId" value="${result.reviewId}" />
	</c:url>
	<a href="${delUrl}">삭제</a>
	
	
</div>

</body>
</html>