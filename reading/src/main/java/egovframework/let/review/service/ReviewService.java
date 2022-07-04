package egovframework.let.review.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface ReviewService {
	
	//게시물 목록 가져오기 
	public List<EgovMap> selectReviewList(ReviewVO vo) throws Exception;
	

}
