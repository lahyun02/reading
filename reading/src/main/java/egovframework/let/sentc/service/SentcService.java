package egovframework.let.sentc.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface SentcService {

	//게시물 목록 가져오기
	public List<EgovMap> selectSentcList(SentcVO vo) throws Exception;
	
	//게시물 목록 수
	public int selectSentcListCnt(SentcVO vo) throws Exception;
	
	//게시물 등록하기
	public String insertSentc(SentcVO vo) throws Exception;
	
	//게시물 상세정보
	public SentcVO selectSentc(SentcVO vo) throws Exception;
	
	//게시물 수정하기
	public void updateSentc(SentcVO vo) throws Exception;
	
	//게시물 삭제하기
	public void deleteSentc(SentcVO vo) throws Exception;
	
	
}