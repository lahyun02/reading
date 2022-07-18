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
<title>게시판</title>
<!-- BBS Style -->
<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />

<!-- 공통 Style -->
<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

</head>
<body>
<%@include file="/WEB-INF/jsp/main/jsp/menu.jsp"%>

<%-- 기본 URL --%>
<c:url var="_BASE_PARAM" value="">
	<c:param name="menuNo" value="50" />
	<c:param name="pageIndex" value="${searchVO.pageIndex}" />
	<c:if test="${not empty searchVO.searchCondition}">
		<c:param name="searchCondition" value="${searchVO.searchCondition}" />
	</c:if>
	<c:if test="${not empty searchVO.searchKeyword}">
		<c:param name="searchKeyword" value="${searchVO.searchKeyword}" />
	</c:if>
</c:url>

<div class="container">
	<div id="contents">
		<div class="list-title">
	        <h2>책문구</h2>
	    </div>
		<div id="bbs_wrap">
			<div class="board_view">
				<dl class="tit_view">
					<dt>제목</dt>
					<dd><c:out value="${result.sentcSj}"/></dd>
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
				
				<div class="view_cont">
					<c:out value="${result.sentcCn}" escapeXml="false" />
				</div>
			</div>
			
			<div class="btn-cont ar">
				<c:choose>
					<c:when test="${not empty searchVO.sentcId}">
						<c:url var="uptUrl" value="/sentc/sentcRegist.do${_BASE_PARAM}">
							<c:param name="sentcId" value="${result.sentcId}"/>						
						</c:url>
						<a href="${uptUrl}" class="btn">수정</a>
						
						<c:url var="delUrl" value="/sentc/delete.do${_BASE_PARAM}">
							<c:param name="sentcId" value="${result.sentcId}"/>	
						</c:url>
						<a href="${delUrl}" id="btn-del" class="btn"><i class="ico-del"></i> 삭제</a>
					</c:when>
					<c:otherwise>
						<a href="#none" id="btn-reg" class="btn spot">등록</a>
					</c:otherwise>
				</c:choose>
				<c:url var="listUrl" value="/sentc/selectList.do${_BASE_PARAM}" />
				<a href="${listUrl}" class="btn">목록</a>
			</div>
			
			<form action="/reply/list.do" method="post">
					<input type="hidden" id="repSentcId" name="repSentcId" value="${result.sentcId}" />
					<input type="hidden" id="repWriter" name="repWriter" value="${USER_INFO.id}" />
					<textarea id="repContent" name="repContent" rows="4" cols="80"></textarea>
					<input type="button" id="saveBtn" value="저장" />  
			</form>
			<div id="reply-box"></div> 
				
		</div>
	</div>
</div>
<%@include file="/WEB-INF/jsp/main/jsp/footer.jsp"%>

<script>

$(document).ready(function(){
	//게시글 삭제
	$("#btn-del").click(function(){
		if(!confirm("삭제하시겠습니까?")){
			return false;
		}
	});
});


function refreshReplyList() {
	$.ajax({
		url: "/reply/list.do",
		method: "POST",
		data: { 
			repSentcId : $('#repSentcId').val(),
		},
		dataType: "json"
		  
	}).done(function( data ) {
		$('#reply-box').empty();
		console.log(data);
		console.log("repContent : " + data.includes('name="repContent"') ); 
		
		if(data[0].repContent) {
			for(var i = 0; i < data.length; i++) {
				var vo = data[i];
				console.log(vo.repReviewId, vo.repWriter, vo.repContent);
				$('<div>').text( vo.repWriter ).appendTo('#reply-box');
				$('<div>').text( vo.repContent ).appendTo('#reply-box');
				$('<div>').text( moment( vo.repRegDate ).format("YYYY-MM-DD HH:mm:ss") ).appendTo('#reply-box');
				console.log(vo.repWriter);
				console.log('${USER_INFO.id}');
				if(vo.repWriter == '${USER_INFO.id}') {
					$('<button>').attr('data-no', vo.repNo).addClass('btn-del').text('삭제').appendTo( '#reply-box' ); 
				}
				
				$('<hr>').appendTo('#reply-box'); 
			}  
		}
		
	}).fail(function( jqXHR, textStatus ) {  //요청이 실패한 경우 실행할 함수 
		alert( "Request failed: " + textStatus );
	});	
}


refreshReplyList();


$("#saveBtn").click(function(){
	$.ajax({
	  url: "/reply/insert.do",
	  method: "POST",
	  data: { 
		  repSentcId : $('#repSentcId').val()
		  , repWriter : $('#repWriter').val()
		  , repContent : $('#repContent').val()  
	  },
	  dataType: "json",
	  
	}).done(function(msg) {
		
		alert(msg.no + '개의 댓글을 저장했습니다.');  
		$('#repContent').val('');
		refreshReplyList();
	}).fail(function( jqXHR, textStatus ) {  //요청이 실패한 경우 실행할 함수 
		alert( "Request failed: " + textStatus );
	});	
});	

$('#reply-box').on('click', '.btn-del', function(){
	alert("댓글이 삭제되었습니다.");
	$.ajax({
		url: "/reply/delete.do",
		method: "GET", 
		data: { 
			repNo : $(this).attr('data-no')
		},
		dataType: "json"
		  
	}).done(function( data ) {
		console.log(data);
		console.log("data.repNo :" + $('#repNo').val() );
		refreshReplyList();
		
	}).fail(function( jqXHR, textStatus ) {  //요청이 실패한 경우 실행할 함수 
		alert( "Request failed: " + textStatus );
	});	
	
	
});
	


</script>

</body>
</html>