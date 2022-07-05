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
<div id="contents">
	<form action="/review/insert.do" method="post" id="" name="">
		<input type="hidden" name="" value="" />
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
						<input type="text" id="reviewSj" name="reviewSj" title="" class="" value="" />
					</td>
				</tr>
				<tr>
					<th>작성자ID</th>
					<td></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea id="reviewCn" name="reviewCn" rows="30" cols="50" title=""></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="">
			<input type="submit" value="등록" /> 
		</div>
	</form>
</div>

</div>

</body>
</html>