var swiper = new Swiper(".mySwiper", {
  effect: "coverflow",
  grabCursor: true,
  centeredSlides: true,
  slidesPerView: "auto",
  coverflowEffect: {
    rotate: 50,
    stretch: 0,
    depth: 100,
    modifier: 1,
    slideShadows: true,
  },
  pagination: {
    el: ".swiper-pagination",
  },
});


function vali(){
	  if(!$("#loginId").val()){
	    alert("아이디를 입력해주세요.");
	    $("#loginId").focus();
	    return false;
	  }
	    	
	  if(!$("#loginPw").val()){
	    alert("비밀번호를 입력해주세요.");
	    $("#loginPw").focus();
	    return false;
	  }
	}

	