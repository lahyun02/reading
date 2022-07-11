package egovframework.let.review.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface ReviewService {
	
	//Review 목록 가져오기 
	public List<EgovMap> selectReviewList(ReviewVO vo) throws Exception;

	//Review 등록하기
	public String insertReview(ReviewVO vo) throws Exception;
	
	//Review 상세정보 조회하기 
	public ReviewVO selectReview(ReviewVO rvVO) throws Exception;
	
	//Review 수정하기
	public void updateReview(ReviewVO rvVO) throws Exception;

	//Review 삭제하기
	public void deleteReview(ReviewVO rvVO) throws Exception;
	
	//Review 목록 수 
	public int selectReviewListCnt(ReviewVO rvVO) throws Exception;
	
	//Main Review 조회순 
	public List<EgovMap> selectMainReview() throws Exception; 
	
	
	
	

}
