package egovframework.let.reply.service.impl;

import java.util.List;

import egovframework.let.reply.service.ReplyVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("replyMapper")
public interface ReplyMapper {
	
	//댓글 추가
	int insertReply(ReplyVO rpVO) throws Exception;
	
	//댓글 목록 
//	List<EgovMap> selectReplyList(ReplyVO rpVO) throws Exception;
	
	//댓글 삭제
	int deleteReply(ReplyVO rpVO) throws Exception;
	
	//댓글 목록 
	List<EgovMap> selectReplyList(ReplyVO rpVO) throws Exception;
	
	//댓글 변경 
	int updateReply(ReplyVO rpVO) throws Exception; 
	
	
	
}
