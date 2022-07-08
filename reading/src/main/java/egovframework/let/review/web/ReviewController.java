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
		
		List<EgovMap> list = reviewService.selectReviewList(rvVO);
		System.out.println(list); 
		model.addAttribute("rvList", list);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("UserInfo", user); 
		return "review/ReviewSelectList";
	}
	
	//Review 글 등록/수정하는 폼
	@RequestMapping(value = "/review/reviewRegist.do")
	public String reviewRegist(@ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		ReviewVO result = new ReviewVO();
		if(rvVO.getReviewId()!=null || !("").equals(rvVO.getReviewId())) { 
			result = reviewService.selectReview(rvVO);
		}
		model.addAttribute("result", result); 
		
		return "review/ReviewRegist";
	}    
	
	//Review 글 등록하기
	@RequestMapping(value = "/review/insert.do")
	public String insert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
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
		
		
		return "forward:/review/selectList.do";
	}
	
	//Review글 상세조회
	@RequestMapping(value = "/review/select.do")
	public String select(@ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		
		ReviewVO result = reviewService.selectReview(rvVO);
		model.addAttribute("result", result);
		
		return "review/ReviewSelect";
	}
	
	//Review 글 수정하기
	@RequestMapping(value = "/review/update.do")
	public String update(final MultipartHttpServletRequest multiRequest, @ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
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
		
		return "forward:/review/selectList.do";
	}
	
	//Review 글 삭제하기
	@RequestMapping(value = "/review/delete.do")
	public String delete(@ModelAttribute("rv") ReviewVO rvVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		reviewService.deleteReview(rvVO);
		
		return "forward:/review/selectList.do";
	}
	
}
