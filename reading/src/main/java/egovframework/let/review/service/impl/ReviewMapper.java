package egovframework.let.review.service.impl;

import java.util.List;

import egovframework.let.review.service.ReviewVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("reviewMapper")
public interface ReviewMapper {
	
	//게시물 목록 가져오기
	List<EgovMap> selectReviewList(ReviewVO vo) throws Exception;
	
	//Review글 등록하기 
	void insertReview(ReviewVO vo) throws Exception;
	
	//Review글 상세조회하기 
	ReviewVO selectReview(ReviewVO rvVO) throws Exception;
	
	//Review 수정하기
	void updateReview(ReviewVO rvVO) throws Exception;
	
	//Review 삭제하기
	void deleteReview(ReviewVO rvVO) throws Exception;
	
	//Review 목록 수 
	int selectReviewListCnt(ReviewVO rvVO) throws Exception; 
	
	

	
	
}
