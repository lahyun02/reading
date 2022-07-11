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
<!-- BBS Style -->
<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />

<!-- 공통 Style -->
<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/main/jsp/menu.jsp"%>
<div class="container">
	<div id="contents">
		<div id="bbs_wrap"> 
			<div class="board_view">
				<dl class="tit_view">
					<dt>제목</dt>
					<dd><c:out value="${result.reviewSj}" /></dd>
				</dl>
				<dl class="info_view2">
					<dt>작성자ID</dt>
					<dd><c:out value="${result.frstRegisterId}"></c:out></dd>
					<dt>작성일</dt>
					<dd><fmt:formatDate value="${result.frstRegistPnttm}" pattern="yyyy-MM-dd"/></dd>
					<dt>조회수</dt>
					<dd><c:out value="${result.inqireCo}"></c:out></dd>
				</dl>
				<dl class="tit_view"> 
					<dt>첨부파일목록</dt>
					<dd>
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${result.atchFileId}" />	
						</c:import> 
					</dd>
				</dl>
				
				<%-- <c:if test="${not empty result.atchFileNm}">
					<c:url var="thumbUrl" value="/cmm/fms/getSelectImage.do">  
						<c:param name="thumbYn" value="Y" />
						<c:param name="atchFileNm" value="${result.atchFileNm}"/>
					</c:url>
					<img src="${thumbUrl}" alt=""/>
				</c:if> --%>
				<c:choose>
					<c:when test="${not empty result.atchFileNm}">
						<c:url var="selectUrl" value="/cmm/fms/getSelectImage.do">  
							<c:param name="thumbYn" value="Y" />
							<c:param name="atchFileNm" value="${result.atchFileNm}"/>
						</c:url>
						<img src="${selectUrl}" alt=""/>
					</c:when>
					<c:otherwise>
						<img src='<c:url value="/asset/images/reviewList_default_img.jpg"/>' alt="기본썸네일" /> 
					</c:otherwise>				
				</c:choose>			
				<div class="view_cont">
					<c:out value="${result.reviewCn}" escapeXml="false" />
				</div>
			</div>
			
			<div class="btn-cont ar">
				
			</div>
		</div>
	</div>
	
	<%-- 제목 : ${result.reviewSj} 
	
	작성자 id : ${result.frstRegisterId} 
	
	내용 : ${result.reviewCn}
	
	조회수 : ${result.inqireCo}
	
	작성일 : <fmt:formatDate value="${result.frstRegistPnttm}" pattern="yyyy-MM-dd" />  
	
	첨부파일목록 : 
	<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
		<c:param name="param_atchFileId" value="${result.atchFileId}" />	
	</c:import> 
	--%>
	
	<c:url var="uptUrl" value="/review/reviewRegist.do">
		<c:param name="reviewId" value="${result.reviewId}" />
	</c:url>
	<a href="${uptUrl}" class="btn">수정</a>
	
	<c:url var="delUrl" value="/review/delete.do">
		<c:param name="reviewId" value="${result.reviewId}" />
		<c:param name="frstRegisterId" value="${result.frstRegisterId}" />
	</c:url>
	<a href="${delUrl}" id="btn-del" class="btn"><i class="ico-del"></i> 삭제</a>
	
	<c:url var="listUrl" value="/review/selectList.do" /> 
	<a href="${listUrl}" class="btn">목록</a>	
	
</div>

<%@include file="/WEB-INF/jsp/main/jsp/footer.jsp"%>

<script>
$(document).ready(function(){
	//게시글 삭제
	$("#btn-del").click(function(){
		if(!confirm("삭제하시겠습니까?")) {
			return false;
		}		
	});
});
</script>

</body>
</html>