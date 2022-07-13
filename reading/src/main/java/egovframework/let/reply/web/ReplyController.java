package egovframework.let.reply.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.reply.service.ReplyService;
import egovframework.let.reply.service.ReplyVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class ReplyController {
	
	@Resource(name = "replyService")
	private ReplyService replyService;
	
//	@ResponseBody
//	@RequestMapping(value = "/reply/insert.do")
//	public String insert(@ModelAttribute("rp") ReplyVO rpVO, HttpServletRequest request) throws Exception {
//		//유저 id 가져와서 ReplyVO에 저장하기
//		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//		rpVO.setRepWriter(user.getId());
//		
//		replyService.insertReply(rpVO);
//		
//		return "댓글 입력 성공";
//	}
	
	//댓글 목록 
	@RequestMapping(value = "/reply/list.do")
	public String list(@ModelAttribute("rp") ReplyVO rpVO, HttpServletRequest request, Model model) throws Exception {
		//내용저장  //유저 id 가져와서 ReplyVO에 저장하기
		
		System.out.println(rpVO.getRepContent()); 
		if(!EgovStringUtil.isEmpty(rpVO.getRepContent())) {
			replyService.insertReply(rpVO);
		}
		
		rpVO.setRecordCountPerPage(Integer.MAX_VALUE);
		rpVO.setFirstIndex(0); 
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		rpVO.setRepWriter(user.getId());
		
		List<EgovMap> resultList = replyService.selectReplyList(rpVO);
		model.addAttribute("resultList", resultList); 
		System.out.println(resultList);
		
		return "/reply/ReplyList";
	}
	
	//댓글 삭제
	@RequestMapping(value = "/reply/delete.do")
	public String delete(@ModelAttribute("rp") ReplyVO rpVO, HttpServletRequest request, Model model) throws Exception{
		System.out.println(rpVO); 
		replyService.deleteReply(rpVO);
		
		return "/reply/ReplyList";
	}
	
	
}
