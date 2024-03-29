package egovframework.let.sentc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.let.cop.bbs.service.EgovBBSManageService;
import egovframework.let.sentc.service.SentcService;
import egovframework.let.sentc.service.SentcVO;
import egovframework.let.utl.fcc.service.EgovDateUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("sentcService")
public class SentcServiceImpl extends EgovAbstractServiceImpl implements SentcService {
	// 어노테이션에 명칭을 줄때 항상 소문자로 시작함.
	
	@Resource(name = "sentcMapper")
	private SentcMapper sentcMapper;
	
	@Resource(name = "egovSentcIdGnrService")
	private EgovIdGnrService idgenService;
	//context-idgen에 있는 이름을 @resource의 이름에 들어감. (리소스를 통해 context-idgen을 찾아갈 수 있도록)
	
	//게시물 목록 가져오기
	public List<EgovMap> selectSentcList(SentcVO vo) throws Exception {
		return sentcMapper.selectSentcList(vo);
	}

	//게시물 목록 수
	public int selectSentcListCnt(SentcVO vo) throws Exception {
		return sentcMapper.selectSentcListCnt(vo);
	}
	
	//게시물 등록
	@Override
	public String insertSentc(SentcVO vo) throws Exception {
		String id = idgenService.getNextStringId();
		vo.setSentcId(id); 
		sentcMapper.insertSentc(vo);
		
		return id;
	}
	
	//게시물 상세정보
	@Override
	public SentcVO selectSentc(SentcVO vo) throws Exception {
		//조회수 업 
		sentcMapper.updateViewCnt(vo);
		
		return sentcMapper.selectSentc(vo);
	}
	
	//게시물 수정하기
	@Override
	public void updateSentc(SentcVO vo) throws Exception {
		sentcMapper.updateSentc(vo);
	}
	
	//게시물 삭제하기
	@Override
	public void deleteSentc(SentcVO vo) throws Exception {
		sentcMapper.deleteSentc(vo);
	}
	
	
}

