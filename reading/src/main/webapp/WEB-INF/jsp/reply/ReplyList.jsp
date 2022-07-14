<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.Date" %>


<table>
	<thead>
		<tr>
			<th>작성자</th>
			<th>내용</th>
			<th>작성일</th>
			<th>관리</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${resultList}">
			<tr>
				<td><c:out value="${result.repWriter}" /></td>
				<td><c:out value="${result.repContent}" /></td>
				<td><fmt:formatDate value="${result.repRegDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				<td> 
					${result.repNo} 
					<form action="/reply/delete.do" method="post" id="delFrm">
						<input type="hidden" id="repReviewId" name="repReviewId" value="${result.repReviewId}" />
						<input type="hidden" id="repNo" name="repNo" value="${result.repNo}" />
						<input type="hidden" id="repContent" name="repContent" value="${result.repContent}" />
						<input type="button" class="btn-del" value="삭제" />  
					</form>
				</td>
				
				<%-- <c:url var="delUrl" value="/reply/delete.do">
					<c:param name="repReviewId" value="${result.repReviewId}" />
					<c:param name="repNo" value="${result.repNo}" />
				</c:url>
				<td><a href="${delUrl}" class="btn-del">삭제</a></td> --%>
			</tr>
		</c:forEach>
	</tbody>
	
</table>

