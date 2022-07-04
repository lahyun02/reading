package egovframework.let.review.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
		model.addAttribute("rvList", list);
		return "review/ReviewSelectList";
	}
	
	
}
