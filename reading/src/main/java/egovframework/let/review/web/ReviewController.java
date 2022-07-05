package egovframework.let.review.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.board.service.BoardService;
import egovframework.let.review.service.ReviewService;
import egovframework.let.review.service.ReviewVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class ReviewController {
	
	@Resource(name = "reviewService")
	private ReviewService reviewService;
	
	//Review 목록 가져오기
	@RequestMapping(value = "/review/selectList.do")
	public String selectList(@ModelAttribute("rv") ReviewVO rvVO,HttpServletRequest request, ModelMap model) throws Exception {
		
		List<EgovMap> list = reviewService.selectReviewList(rvVO);
		System.out.println(list); 
		model.addAttribute("rvList", list);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("UserInfo", user); 
//		model.addAttribute("rv", rvVO); 
		return "review/ReviewSelectList";
	}
	
	//Review 글 등록/수정하는 폼
	@RequestMapping(value = "/review/reviewRegist.do")
	public String reviewRegist(@ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		
		return "review/ReviewRegist";
	}
	
	//Review 글 등록하기
	@RequestMapping(value = "/review/insert.do")
	public String insert(@ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		System.out.println(rvVO.getReviewSj());  
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		
		rvVO.setCreatIp(request.getRemoteAddr());
		rvVO.setUserId(user.getId());
		
		reviewService.insertReview(rvVO);
		
		
		return "forward:/review/selectList.do";
	}
	
	//Review글 상세조회
	@RequestMapping(value = "/review/select.do")
	public String select(@ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		
		return "review/select.do";
	}
	
	//Review 글 수정하기
	@RequestMapping(value = "/review/update.do")
	public String update(@ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		rvVO.setUserId(user.getId()); 
//		reviewService.updateReview(rvVO); 
		
		return "forward:/review/selectList.do";
	}
	
	
}
