package egovframework.let.review.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.board.service.BoardService;
import egovframework.let.review.service.ReviewService;
import egovframework.let.review.service.ReviewVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.let.utl.fcc.service.FileMngUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ReviewController {
	
	@Resource(name = "reviewService")
	private ReviewService reviewService;
	
	@Resource(name = "EgovFileMngService")
	protected EgovFileMngService fileMngService;
	
	@Resource(name = "fileMngUtil")
	private FileMngUtil fileUtil;
	
	
	//Review 목록 가져오기
	@RequestMapping(value = "/review/selectList.do")
	public String selectList(@ModelAttribute("rv") ReviewVO rvVO,HttpServletRequest request, ModelMap model) throws Exception {
//		model.addAttribute("rv", rvVO); 
		System.out.println("rvVO의 pageIndex: " + rvVO.getPageIndex());
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(rvVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rvVO.getPageUnit());
		paginationInfo.setPageSize(rvVO.getPageSize());
		
		rvVO.setFirstIndex(paginationInfo.getFirstRecordIndex()); 
		rvVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rvVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage()); 
		
		System.out.println(rvVO.getPageUnit());
		System.out.println(paginationInfo.getRecordCountPerPage()); 
//		System.out.println("pageIndex: " + rvVO.getPageIndex());
		System.out.println(paginationInfo.getCurrentPageNo());
		
		List<EgovMap> list = reviewService.selectReviewList(rvVO);
		System.out.println(list); 
		model.addAttribute("rvList", list);
		
		int totCnt = reviewService.selectReviewListCnt(rvVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo); 
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("USER_INFO", user); 
		
		return "review/ReviewSelectList";
	}
	
	//Review 글 등록/수정하는 폼
	@RequestMapping(value = "/review/reviewRegist.do")
	public String reviewRegist(@ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/review/selectList.do";
		} else {
			model.addAttribute("USER_INFO", user); 
		}
		
		ReviewVO result = new ReviewVO();
		if(rvVO.getReviewId()!=null || !("").equals(rvVO.getReviewId())) { 
			result = reviewService.selectReview(rvVO);
		}
		model.addAttribute("result", result); 
		
		request.getSession().removeAttribute("sessionReview"); 
		
		return "review/ReviewRegist";
	}    
	
	//Review 글 등록하기
	@RequestMapping(value = "/review/insert.do")
	public String insert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		if(request.getSession().getAttribute("sessionReview") != null ) {
			return "forward:/review/selectList.do";
		}
		
		System.out.println(rvVO.getReviewSj());  
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		//---- 첨부파일 추가
		List<FileVO> result = null;
		String atchFileId = "";
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if(!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "REVIEW_", 0, "", "review.fileStorePath");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		rvVO.setAtchFileId(atchFileId); 
		//
		
		rvVO.setCreatIp(request.getRemoteAddr());
		rvVO.setUserId(user.getId());
		
		reviewService.insertReview(rvVO);
		
		request.getSession().setAttribute("sessionReview", rvVO); 
		
		return "forward:/review/selectList.do";
	}
	
	//Review글 상세조회
	@RequestMapping(value = "/review/select.do")
	public String select(@ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("USER_INFO", user); 
		
		ReviewVO result = reviewService.selectReview(rvVO);
		model.addAttribute("result", result);
		System.out.println("최초작정자ID" + result.getFrstRegisterId());
		System.out.println("최종수정자ID" + result.getLastUpdusrId());
		
		return "review/ReviewSelect";
	}
	
	//Review 글 수정하기
	@RequestMapping(value = "/review/update.do")
	public String update(final MultipartHttpServletRequest multiRequest, @ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		if(request.getSession().getAttribute("sessionReview") != null) {
			return "forward:/review/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		
		
		//------첨부파일
		String atchFileId = rvVO.getAtchFileId();
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if(!files.isEmpty()) {
			if(EgovStringUtil.isEmpty(atchFileId)) {
				List<FileVO> result = fileUtil.parseFileInf(files, "REVIEW_", 0, "", "review.fileStorePath");
				atchFileId = fileMngService.insertFileInfs(result);
				rvVO.setAtchFileId(atchFileId); 
			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int cnt = fileMngService.getMaxFileSN(fvo);
				List<FileVO> _result = fileUtil.parseFileInf(files, "REVIEW_", cnt, atchFileId, "review.fileStorePath");
				fileMngService.updateFileInfs(_result); 
			}
		}
		//
		
		rvVO.setUserId(user.getId());
		rvVO.setLastUpdusrId(user.getId());
		
		reviewService.updateReview(rvVO); 
		
		request.getSession().setAttribute("sessionReview", rvVO);
		
		return "forward:/review/selectList.do";
	}
	
	//Review 글 삭제하기
	@RequestMapping(value = "/review/delete.do")
	public String delete(@ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
		
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		System.out.println(rvVO.getFrstRegisterId());
		System.out.println(user.getId()); 
		
		if(user == null || user.getId() == null ) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/review/selectList.do";
		} else if("admin".equals(user.getId())) {
			rvVO.setMngAt("Y"); 
		} else if(!rvVO.getFrstRegisterId().equals(user.getId())) {
			model.addAttribute("message", "본인 글만 삭제가능합니다. ");
			return "forward:/review/selectList.do";
		}
		
		
		rvVO.setUserId(user.getId()); 
		
		reviewService.deleteReview(rvVO);
		
		return "forward:/review/selectList.do";
	}
	
}
