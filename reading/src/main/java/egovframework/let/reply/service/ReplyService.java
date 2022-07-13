package egovframework.let.reply.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface ReplyService {
	
	//댓글 추가 
	void insertReply(ReplyVO rpVO) throws Exception;
	
	//댓글 목록 
	List<EgovMap> selectReplyList(ReplyVO rpVO) throws Exception;
	
	//댓글 삭제 
	void deleteReply(ReplyVO rpVO) throws Exception; 

}
