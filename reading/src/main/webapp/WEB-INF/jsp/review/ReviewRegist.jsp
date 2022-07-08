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

<c:choose>
	<c:when test="${not empty result.reviewId}">
		<c:set var="actionUrl" value="/review/update.do" /> 
	</c:when>
	<c:otherwise>
		<c:set var="actionUrl" value="/review/insert.do" /> 
	</c:otherwise>
</c:choose>


<div class="container"> 
<div id="contents">
	<form action="${actionUrl}" method="post" id="frm" name="" onsubmit="return regist()" enctype="multipart/form-data"> 
		<input type="hidden" name="reviewId" value="${rv.reviewId }" />
		<input type="hidden" name="returnUrl" value="/board/boardRegist.do" />
		<table>
			<caption></caption>
			<colgroup>
				<col style="width:120px" />
				<col />
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" id="reviewSj" name="reviewSj" title="" class="" value="${result.reviewSj}" />
					</td>
				</tr>
				<tr>
					<th>작성자ID</th>
					<td><c:out value="${result.frstRegisterId }" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea id="reviewCn" name="reviewCn" rows="30" cols="50" title=""><c:out value="${result.reviewCn}" /></textarea>
					</td>
				</tr>
				
				<c:if test="${not empty result.atchFileId}">
					<tr>
						<th scope="row">기존<br/>첨부파일목록</th>		
						<td>
							<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${result.atchFileId }" />
							</c:import>
						</td>			
					</tr>
				</c:if>
				
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="file_1" />
					</td>
				</tr>
			</tbody>
		</table>
		<div class="">
			
			<c:choose>
				<c:when test="${not empty result.reviewId }">
					<c:url var="udtUrl" value="/review/update.do">
						<c:param name="reviewId" value="${result.reviewId}" />
					</c:url>
					<a href="${udtUrl}" id="btn-rgt">수정</a>
					
					<c:url var="delUrl" value="/review/delete.do">
						<c:param name="reviewId" value="${result.reviewId}" />
					</c:url>
					<a href="${delUrl}" id="btn-del">삭제</a>
				</c:when>
				<c:otherwise>
					<c:url var="istUrl" value="/review/insert.do">
						<c:param name="reviewId" value="${result.reviewId}" />
					</c:url>
					<a href="${istUrl}" id="btn-rgt">등록</a> 
				</c:otherwise>
			</c:choose>

		</div>
	</form>
	
</div>

</div>

<script>
	$(document).ready(function() {
		$("#btn-rgt").click(function(){
			alert(00);
			$("#frm").submit();
			return false;
		});
		
		$("#btn-del").click(function(){
			if(!confirm("삭제하시겠습니까?")){
				return false;
			}
		});
	});
	
	function regist() {
		if(!$("#reviewSj").val()) {
			alert("제목을 입력하세요.");
			return false;
		};
	};
</script>

</body>
</html>