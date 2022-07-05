package egovframework.let.review.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface ReviewService {
	
	//Review 목록 가져오기 
	public List<EgovMap> selectReviewList(ReviewVO vo) throws Exception;

	//Review 등록하기
	public String insertReview(ReviewVO vo) throws Exception;
	
	//Review 수정하기
//	public void updateReview(ReviewVO vo) throws Exception;
	
	

}
