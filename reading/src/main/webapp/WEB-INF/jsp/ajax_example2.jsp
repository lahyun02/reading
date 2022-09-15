<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>AJAX Test</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-4"> <%--왼쪽 --%>
            <button class="left-btn btn btn-info" style="width:150px"; data-time="2022-09-14">
                    시간 버튼
            </button>
            <br/><br/>
    </div>
    <div class="col-md-4"> <%--오른쪽 --%>
        <div id="result-div">
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>

<script>
    $(document).ready(function() {
        $(".left-btn").click(function() {
            //어떤 요소를 클릭했을 떄 거기에 대한 행동을 하기 위함
            //백그라운드로 서버(컨트롤러)에 데이터/요청를 보내고 답장/응답을(이것도 백그라운드로) 받음
            //이때 URL이 바뀌거나 화면이 새로고침되지 않음
            
            var date = '2022-09-15';
            $.ajax({
                url: '/timeCheck.do?date=' + date, //백그라운드로 서버(컨트롤러)에 데이터/요청를 보내고
                success: function(res) { // 뭔가를 처리한 후  답장/응답을(이것도 백그라운드로) 받음
                	alert(res); //timeCheck메소드의 return값은 Reservation Success가 res에 담겨짐.
				
                }
            });
            
//             $.ajax({
//                 url: '/timeAjax.do', // URL에서 처리
//                 success: function(res) { // 응답으로 res 변수를 줌 
//                     $("#result-div").text(res);
//                 }
//             });
        });
    });
</script>

</body>
</html>
