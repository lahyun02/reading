<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.Date" %>

<%-- 기본 URL --%>
<c:url var="_BASE_PARAM" value="">
	<c:param name="menuNo" value="50" />
	<c:if test="${not empty searchVO.searchCondition}">
		<c:param name="searchCondition" value="${searchVO.searchCondition}" />
	</c:if>
	<c:if test="${not empty searchVO.searchKeyword}">
		<c:param name="searchKeyword" value="${searchVO.searchKeyword}" />
	</c:if>
</c:url>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimun-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>책공간</title>
<!-- 외부스타일시트 -->
<link rel="stylesheet" href="/asset/front/css/review-list.css">
<!-- 제이쿼리 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/main/jsp/menu.jsp"%>
<div id="container">

	
	
	<!-- 섹션 - -->
        <section id="rvList-section">
            <div class="rvList-title">
                <h2>책리뷰</h2>
            </div>
            <div class="rv-container">
                <div class="cnt-page">
                    총 게시물 <strong><c:out value="${paginationInfo.totalRecordCount}"/></strong>건 | 현재페이지 <strong><c:out value="${paginationInfo.currentPageNo}"/></strong>/<c:out value="${paginationInfo.totalPageCount}"/>
                	
                </div>
                <div class="rvList-wrap">
                	<c:forEach var="item" items="${rvList}" varStatus="status" >
	                    <div class="box">
	                    	<div class="num">
	                    		<c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage) - (status.count - 1)}" />
	                    		| ${paginationInfo.recordCountPerPage} + ${rvVO.pageUnit} + ${rvVO.pageIndex}
	                    	</div>
	                        <div class="b-img">
	                        	<c:if test="${not empty item.atchFileNm}">
									<c:url var="thumbUrl" value="/cmm/fms/getThumbImage.do">
										<c:param name="thumbYn" value="Y"/>
										<c:param name="atchFileNm" value="${item.atchFileNm}" />
									</c:url>                        	
									<img src="${thumbUrl}" alt="썸네일" />
	                        	</c:if>
	                        	<c:if test="${empty item.atchFileNm}">
	                        		<img src='<c:url value="/asset/images/reviewList_default_img.jpg"/>' alt="기본썸네일" /> 
	                        	</c:if>
	                        </div>
	                        
	                        <div class="b-wrap">
	                        <div class="b-title">
	                            <a href="/review/select.do?reviewId=${item.reviewId}"><c:out value="${item.reviewSj}" /> </a>
	                            <input type="hidden" name="reviewId" value="${item.reviewId}" />
	                            <input type="hidden" name="pageIndex" value="${rvVO.pageIndex}" />
	                        </div>
	                        <div class="b-content">
	                        	<p><c:out value="${item.reviewCn}" /> </p>
	                        </div>
	                        <div class="b-txt">
	                            <span><c:out value="${item.frstRegisterId}" /></span>
	                            <em><fmt:formatDate value="${item.frstRegistPnttm}" pattern="yyyy-MM-dd"/></em>
	                        </div>
	                        </div>
	                        
	                    </div>
                	</c:forEach>
                    
                </div>
                
                <div id="paging">
                	<c:url var="pageUrl" value="/review/selectList.do${_BASE_PARAM}" />
                	<c:set var="pagingParam"><c:out value="${pageUrl}"/></c:set>
                	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="${pagingParam}"/>
                </div>
                
                <div class="">
                	<a href="/review/reviewRegist.do">글쓰기</a>
                </div>
                
            </div>
            
        </section>
        
	
	
<%@include file="/WEB-INF/jsp/main/jsp/footer.jsp"%>
</div>


<script>
	<c:if test="${not empty message}">
		alert("${message}");
	</c:if>
</script>


</body>
</html>