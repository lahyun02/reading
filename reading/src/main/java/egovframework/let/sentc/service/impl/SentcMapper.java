package egovframework.let.sentc.service.impl;
import java.util.Iterator;
import java.util.List;

import egovframework.let.board.service.BoardVO;
import egovframework.let.sentc.service.SentcVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import org.springframework.stereotype.Repository;

@Mapper("sentcMapper")
public interface SentcMapper {
	
	//게시물 목록 가져오기
	List<EgovMap> selectSentcList(SentcVO vo) throws Exception;

	//게시물 목록 수
	int selectSentcListCnt(SentcVO vo) throws Exception;
	
	//게시물 등록
	void insertSentc(SentcVO vo) throws Exception;
	
	//게시물 상세정보
	SentcVO selectSentc(SentcVO vo) throws Exception;
	
	//조회수 업 
	void updateViewCnt(SentcVO vo) throws Exception;
	
	//게시물 수정하기
	void updateSentc(SentcVO vo) throws Exception;
	
	//게시물 삭제하기
	void deleteSentc(SentcVO vo) throws Exception;
	
}
