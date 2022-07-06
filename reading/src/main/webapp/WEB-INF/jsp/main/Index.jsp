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

<title>책공간 </title>
<!-- Link Swiper's CSS -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
<!-- 외부스타일시트 -->
<link rel="stylesheet" href="/asset/front/css/swiper-source.css">
<link rel="stylesheet" href="/asset/front/css/style.css">
<!-- 제이쿼리 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script> 
</head>
<body>
<%@include file="/WEB-INF/jsp/main/jsp/menu.jsp"%>
<div id="container">
        
        <!-- 섹션 -메인 -->
        <section id="main-section">
            <div class="main-bg">
                <div class="title">
                    <h2>
                        책이 있는 공간
                    </h2>
                </div>
                <!-- Swiper -->
                <div class="swiper mySwiper">
                    <div class="swiper-wrapper">
                    <div class="swiper-slide s-img1">
                        <div class="s-bg">
                            <span class="s-btn"><a href="#">바로가기</a></span>
                        </div>
                    </div>
                    <div class="swiper-slide s-img2">
                        <div class="s-bg">
                            <span class="s-btn"><a href="#">바로가기</a></span>
                        </div>
                    </div>
                    <div class="swiper-slide s-img3">
                        <div class="s-bg">
                            <span class="s-btn"><a href="#">바로가기</a></span>
                        </div>
                    </div>
                    <div class="swiper-slide s-img4">
                        <div class="s-bg">
                            <span class="s-btn"><a href="#">바로가기</a></span>
                        </div>
                    </div>
                    <div class="swiper-slide s-img5">
                        <div class="s-bg">
                            <span class="s-btn"><a href="#">바로가기</a></span>
                        </div>
                    </div>
                    <div class="swiper-slide s-img6">
                        <div class="s-bg">
                            <span class="s-btn"><a href="#">바로가기</a></span>
                        </div>
                    </div>
                    
                    
                    </div>
                    <div class="swiper-pagination"></div>
                </div>
            </div>
        </section>
        <!-- 섹션2 : 컨텐츠1 -->
        <section id="qt-section">
            <div class="cn-title">
                <strong>기록하고 싶은 책 문구</strong>
            </div>
            <div class="quotes-box">
                <div class="quotes">
                    <strong>불편한 편의점</strong>
                    <p>
                        불편한 편의점에서 기록하고 싶은 인용구를 적는
                        공간입니다. 불편한 편의점에서 기록하고 싶은 인용구를 적는 공간입니다.
                    </p>
                    <span><a href="#">바로가기</a></span>
                </div>
                <div class="quotes">
                    <strong>불편한 편의점</strong>
                    <p>
                        불편한 편의점에서 기록하고 싶은 인용구를 적는
                        공간입니다. 불편한 편의점에서 기록하고 싶은 인용구를 적는 공간입니다.
                    </p>
                    <span><a href="#">바로가기</a></span>
                </div>
                <div class="quotes">
                    <strong>불편한 편의점</strong>
                    <p>
                        불편한 편의점에서 기록하고 싶은 인용구를 적는
                        공간입니다. 불편한 편의점에서 기록하고 싶은 인용구를 적는 공간입니다.
                    </p>
                    <span><a href="#">바로가기</a></span>
                </div>
            </div>
        </section>
        
    </div>
    
    <%@include file="/WEB-INF/jsp/main/jsp/footer.jsp"%>



    <!-- Swiper JS -->
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <!-- 외부 JS -->
    <script src="/asset/front/js/main.js"></script>
    
    
</body>
</html>