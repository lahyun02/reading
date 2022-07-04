package egovframework.let.review.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.review.service.ReviewService;
import egovframework.let.review.service.ReviewVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("reviewService")
public class ReviewServiceImpl extends EgovAbstractServiceImpl implements ReviewService {
	
	@Resource(name = "reviewMapper")
	private ReviewMapper reviewMapper;
	
	// 아이디 젠 
	@Resource(name = "egovReviewIdGnrService")
	private EgovIdGnrService idgenService;
	
	// 게시물 목록 가져오기 
	@Override
	public List<EgovMap> selectReviewList(ReviewVO vo) throws Exception {
		return reviewMapper.selectReviewList(vo);
	}
	

}