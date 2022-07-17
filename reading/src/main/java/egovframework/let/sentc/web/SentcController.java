package egovframework.let.sentc.web;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;
import egovframework.let.cop.bbs.service.BoardMaster;
import egovframework.let.cop.bbs.service.BoardMasterVO;
import egovframework.let.cop.bbs.service.EgovBBSAttributeManageService;
import egovframework.let.sentc.service.SentcService;
import egovframework.let.sentc.service.SentcVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.let.utl.fcc.service.FileMngUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

@Controller
public class SentcController {
	
	@Resource(name = "sentcService")
	private SentcService sentcService;
	// 객체명과 변수명에 앞자리에 각각 대소문자를 다르게 해서 차이점을 둔다. 같으면 호출시 헷갈리기 때문.
	
	@Resource(name = "EgovFileMngService")
	protected EgovFileMngService fileMngService;
	
	@Resource(name = "fileMngUtil")
	private FileMngUtil fileUtil;
	
		
	//sentc 목록 가져오기
	@RequestMapping(value = "/sentc/selectList.do")
	public String selectList(@ModelAttribute("searchVO") SentcVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{

		searchVO.setMainAt("N");
		
		//공지게시글
		searchVO.setNoticeAt("Y");
		List<EgovMap> noticeResultList = sentcService.selectSentcList(searchVO);
		model.addAttribute("noticeResultList", noticeResultList);
		
		//페이징
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		// 공지글이 아닌 일반글. 만약 이 설정을 하지 않는다면 위에서 설정한대로 공지글만 나옴.
		searchVO.setNoticeAt("N");
		List<EgovMap> resultList = sentcService.selectSentcList(searchVO);
		model.addAttribute("resultList", resultList);
		
		int totCnt = sentcService.selectSentcListCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		//사용자 회원 정보 
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("USER_INFO", user);
		
		
		return "sentc/SentcSelectList";
	}
	
	//게시물 등록/수정하는 폼을 불러오는 메소드. 
	@RequestMapping(value = "/sentc/sentcRegist.do")
	public String sentcRegist(@ModelAttribute("searchVO") SentcVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/sentc/selectList.do";
		} else {
			model.addAttribute("USER_INFO", user); 
		}
		
		SentcVO result = new SentcVO();
		if(!EgovStringUtil.isEmpty(searchVO.getSentcId())) {
			result = sentcService.selectSentc(searchVO);
			//본인 및 관리자만 허용 (비밀글 같은 경우) 
			if(!user.getId().equals(result.getFrstRegisterId()) && !"admin".equals(user.getId())){
				model.addAttribute("message", "작성자 본인만 확인 가능합니다.");
				return "forward:/sentc/selectList.do";
			}
			
		}
		model.addAttribute("result", result);
		
		request.getSession().removeAttribute("sessionBoard");
		//해당 섹션을 삭제해야 등록 가능. 
		
		return "sentc/SentcRegist";
	}
	
	//sentc게시글 등록하기
	@RequestMapping(value = "/sentc/insert.do")
	public String insert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") SentcVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		//이중 서브밋 방지 체크
		// F5를 하면 입력했던 값이 그대로 남아있음.
		// 밑에 SEARCHVO를 SESSIONBOARD에 저장했기 때문에 값이 NULL일 수 없음.
		// 밑에서 저장한 걸 가져옴! 
		if(request.getSession().getAttribute("sessionBoard") != null) {
			return "forward:/sentc/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		// 로그인 정보가 없으면  나중엔 경로가 로그인페이지로 됨.
		// 예)글쓰기도중 로그인-현재에 대한 URL주소를 갖고 있다가 다시 URL 주소 반환. 해당페이지로 이동. | 로그인 후 메인페이지.
		if(user == null || user.getId() == null) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/sentc/selectList.do";
		}
		
		//---- 첨부파일 추가
		List<FileVO> result = null;
		String atchFileId = "";
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if(!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "SENTC_", 0, "", "review.fileStorePath");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		searchVO.setAtchFileId(atchFileId); 
		//
		
		
		searchVO.setCreatIp(request.getRemoteAddr()); //작성자 기준 IP 작성 . 공인 IP에 대한 정보 저장 (사설IP X) 
		searchVO.setUserId(user.getId());
		
		sentcService.insertSentc(searchVO);
		
		//이중 서브밋 방지 
		// searchVO를 sessionBoard란 이름으로 세션에  "저장!"  
		request.getSession().setAttribute("sessionBoard", searchVO);
		return "forward:/sentc/selectList.do";
	}
	
	//sentc게시물 가져오기
	@RequestMapping(value = "/sentc/select.do")
	public String select(@ModelAttribute("searchVO") SentcVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("USER_INFO", user);
		
		SentcVO result = sentcService.selectSentc(searchVO);
		//비밀글 여부 체크 (비밀글이면)
		if("Y".equals(result.getOthbcAt())) {
			//본인 및 관리자만 허용
			if(user == null || user.getId() == null || (!user.getId().equals(result.getFrstRegisterId()) && !"admin".equals(user.getId()))) {
				model.addAttribute("message", "작성자 본인만 확인 가능합니다.");
				return "forward:/sentc/selectList.do";
			}
		}
		model.addAttribute("result", result);
		return "sentc/SentcSelect";
	}
	
	//sentc게시물 수정하기
	@RequestMapping(value = "/sentc/update.do")
	public String update(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") SentcVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		//이중 서브밋 방지
		if(request.getSession().getAttribute("sessionBoard") != null) {
			return "forward:/sentc/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null) {
			model.addAttribute("message", "로그인 후 사용가능합니다."); 
			return "forward:/sentc/selectList.do";
		}else if("admin".equals(user.getId())) {
			searchVO.setMngAt("Y");
		}
		
		//------첨부파일
		String atchFileId = searchVO.getAtchFileId();
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if(!files.isEmpty()) {
			if(EgovStringUtil.isEmpty(atchFileId)) {
				List<FileVO> result = fileUtil.parseFileInf(files, "SENTC_", 0, "", "sentc.fileStorePath");
				atchFileId = fileMngService.insertFileInfs(result);
				searchVO.setAtchFileId(atchFileId); 
			} else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int cnt = fileMngService.getMaxFileSN(fvo);
				List<FileVO> _result = fileUtil.parseFileInf(files, "SENTC_", cnt, atchFileId, "sentc.fileStorePath");
				fileMngService.updateFileInfs(_result); 
			}
		}
		//
		
		
		searchVO.setUserId(user.getId()); 
		
		sentcService.updateSentc(searchVO);
		
		//이중 서브밋 방지
		request.getSession().setAttribute("sessionBoard", searchVO);
		return "forward:/sentc/selectList.do";
		
	}
	
	//sentc게시물 삭제하기
	@RequestMapping(value = "/sentc/delete.do")
	public String delete(@ModelAttribute("searchVO") SentcVO searchVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/board/selectList.do";
		}else if("admin".equals(user.getId())) {
			searchVO.setMngAt("Y");
		}
		
		searchVO.setUserId(user.getId());
		
		sentcService.deleteSentc(searchVO);
		
		return "forward:/sentc/selectList.do";
		
	}
		
	
	
}
