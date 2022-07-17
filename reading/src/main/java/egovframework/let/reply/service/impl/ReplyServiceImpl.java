package egovframework.let.reply.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.reply.service.ReplyService;
import egovframework.let.reply.service.ReplyVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
	
	@Resource(name = "replyMapper")
	private ReplyMapper replyMapper;
	
	@Resource(name = "egovReplyIdGnrService")
	private EgovIdGnrService idgenService;
	
	//댓글 추가하기 
	@Override
	public int insertReply(ReplyVO rpVO) throws Exception {
		String id = idgenService.getNextStringId();
		rpVO.setRepNo(id); 
		return replyMapper.insertReply(rpVO);
		
	}
	
	//댓글 목록 
//	@Override
//	public List<EgovMap> selectReplyList(ReplyVO rpVO) throws Exception {
//		System.out.println("serviceImpl");  
//		return replyMapper.selectReplyList(rpVO); 
//	}
	
	//댓글 삭제
	@Override
	public int deleteReply(ReplyVO rpVO) throws Exception {
		return replyMapper.deleteReply(rpVO);
	}
	
	//댓글 목록 
	@Override
	public List<EgovMap> selectReplyList(ReplyVO rpVO) throws Exception {
		return replyMapper.selectReplyList(rpVO); 
	}
	
	
	
}
