package egovframework.let.main.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.*;
import java.util.*;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.cop.bbs.service.BoardVO;
import egovframework.let.cop.bbs.service.EgovBBSManageService;
import egovframework.let.review.service.ReviewService;
import egovframework.let.review.service.ReviewVO;
import egovframework.let.sentc.service.SentcService;
import egovframework.let.sentc.service.SentcVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 템플릿 메인 페이지 컨트롤러 클래스(Sample 소스)
 * @author 실행환경 개발팀 JJY
 * @since 2011.08.31
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.08.31  JJY            최초 생성
 *
 * </pre>
 */
@Controller@SessionAttributes(types = ComDefaultVO.class)
public class EgovMainController {
	
	//책공간 추가 
	@Resource(name = "reviewService")
	private ReviewService reviewService;
	
	//책문구 추가
	@Resource(name = "sentcService")
	private SentcService sentcService;
	
	/**
	 * EgovBBSManageService
	 */
	@Resource(name = "EgovBBSManageService")
    private EgovBBSManageService bbsMngService;

	/**
	 * 메인 페이지에서 각 업무 화면으로 연계하는 기능을 제공한다.
	 *
	 * @param request
	 * @param commandMap
	 * @exception Exception Exception
	 */
	@RequestMapping(value = "/cmm/forwardPage.do")
	public String forwardPageWithMenuNo(HttpServletRequest request, @RequestParam Map<String, Object> commandMap)
	  throws Exception{
		return "";
	}

	/**
	 * 템플릿 메인 페이지 조회
	 * @return 메인페이지 정보 Map [key : 항목명]
	 *
	 * @param request
	 * @param model
	 * @exception Exception Exception
	 */
	@RequestMapping(value = "/cmm/main/mainPage.do")
	public String getMgtMainPage(HttpServletRequest request, ModelMap model)
	  throws Exception{

		// 공지사항 메인 컨텐츠 조회 시작 ---------------------------------
		BoardVO boardVO = new BoardVO();
		boardVO.setPageUnit(5);
		boardVO.setPageSize(10);
		boardVO.setBbsId("BBSMSTR_AAAAAAAAAAAA");

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = bbsMngService.selectBoardArticles(boardVO, "BBSA02");
		model.addAttribute("notiList", map.get("resultList"));

		boardVO.setBbsId("BBSMSTR_BBBBBBBBBBBB");
		map = bbsMngService.selectBoardArticles(boardVO, "BBSA02");
		model.addAttribute("galList", map.get("resultList"));

		// 공지사항 메인컨텐츠 조회 끝 -----------------------------------

		return "main/EgovMainView";
	}
	
	@RequestMapping(value = "/index.do")
	public String index(@ModelAttribute("rv") ReviewVO rvVO, SentcVO stVO, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("USER_INFO", user);
		
		rvVO.setMainAt("Y");
		stVO.setMainAt("Y"); 
		
		List<EgovMap> resultList = reviewService.selectReviewList(rvVO);
		model.addAttribute("resultList", resultList); 
		
		List<EgovMap> sentcList = sentcService.selectSentcList(stVO);
		model.addAttribute("sentcList", sentcList); 
		
		
		return "main/Index";
	}
	
	@RequestMapping(value = "/kakaomap.do")
	public String kakaomap(@ModelAttribute("rv") ReviewVO rvVO, SentcVO stVO, HttpServletRequest request, ModelMap model) throws Exception {
//		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//		model.addAttribute("USER_INFO", user);
//		
//		rvVO.setMainAt("Y");
//		stVO.setMainAt("Y"); 
//		
//		List<EgovMap> resultList = reviewService.selectReviewList(rvVO);
//		model.addAttribute("resultList", resultList); 
//		
//		List<EgovMap> sentcList = sentcService.selectSentcList(stVO);
//		model.addAttribute("sentcList", sentcList); 
		
		
		return "kakaomap";
	}
	
	@RequestMapping(value = "/ajaxExample.do")
	public String ajax_example(@ModelAttribute("rv") ReviewVO rvVO, SentcVO stVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		return "ajax_example";
	}
	
	@RequestMapping(value = "/ajaxResponse.do")
	@ResponseBody  // jsp를 리턴하는 것이 아닌 그냥 순수 string 자체를 응답으로 리턴함 
	public String ajaxResponse(@RequestParam String btnNum) throws Exception {
		System.out.println("btnNum = " + btnNum); 
		
		return "btnNum";
	}
	
	
	@RequestMapping(value = "/ajaxExample2.do")
	public String ajax_example2() throws Exception {
		
		return "ajax_example2";
	}
	
	@RequestMapping(value = "/timeAjax.do")
	@ResponseBody  // jsp를 리턴하는 것이 아닌 그냥 순수 string 자체를 응답으로 리턴함 
	public String timeAjax() throws Exception {
		Date date = Calendar.getInstance().getTime(); //현재시간
		DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //형식지정
		String strDate = dataFormat.format(date); //date -> string으로 변환(위의 형식에 맞게) 
		
		return strDate;
	}
	

}