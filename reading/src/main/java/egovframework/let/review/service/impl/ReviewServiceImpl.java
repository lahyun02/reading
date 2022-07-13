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
	
	//Review 글 등록하기
	@Override
	public String insertReview(ReviewVO vo) throws Exception {
		String id = idgenService.getNextStringId();
		vo.setReviewId(id);
		reviewMapper.insertReview(vo);
		
		return id;
	}
	//Review글 상세조회하기 
	@Override
	public ReviewVO selectReview(ReviewVO rvVO) throws Exception {
		//조회수 업 
		reviewMapper.updateViewCnt(rvVO);
		
		return reviewMapper.selectReview(rvVO);
	}
	
	//Review 수정하기
	@Override
	public void updateReview(ReviewVO rvVO) throws Exception {
		reviewMapper.updateReview(rvVO);
	}
	
	//Review 삭제하기
	@Override
	public void deleteReview(ReviewVO rvVO) throws Exception {
		reviewMapper.deleteReview(rvVO);
	}
	
	//Review 목록 수 
	@Override
	public int selectReviewListCnt(ReviewVO rvVO) throws Exception {
		return reviewMapper.selectReviewListCnt(rvVO);
	}
	
	//Main Review 조회순 
	@Override
	public List<EgovMap> selectMainReview(ReviewVO rvVO) throws Exception {
		return reviewMapper.selectMainReview(rvVO);
	}
	

}
