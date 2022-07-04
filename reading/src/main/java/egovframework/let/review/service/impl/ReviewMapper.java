package egovframework.let.review.service.impl;

import java.util.List;

import egovframework.let.review.service.ReviewVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("reviewMapper")
public interface ReviewMapper {
	
	//게시물 목록 가져오기
	List<EgovMap> selectReviewList(ReviewVO vo) throws Exception;

	
	
}