package egovframework.let.review.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;
import java.util.Date;

public class ReviewVO extends ComDefaultVO implements Serializable {
	
	//책리뷰ID
	private String reviewId;
	
	//책리뷰제목
	private String reviewSj;

	//책리뷰내용
	private String reviewCn;
	
	//조회수
	private int inqireCo;
	
	//등록자IP
	private String creatIp;
	
	//비공개여부
	private String othbcAt;
	
	//사용여부
	private String useAt;
	
	//첨부파일ID
	private String atchFileId;
	
	//최초등록시점
	private Date frstRegistPnttm;
	
	//최초등록자ID
	private String frstRegisterId;
	
	//최종수정시점
	private Date lastUpdtPnttm;
	
	//최종수정자ID
	private String lastUpdusrId;
	
	//사용자ID
	private String userId;
	
	//관리자여부
	private String mngAt;
	
	
	/** recordCountPerPage */
    private int recordCountPerPage = 9;
	
    /** 페이지갯수 */
    private int pageUnit = 9;
    
    //썸네일 이름
    private String atchFileNm;
    
    //리뷰책 제목
    private String rvBookSj;
    
    //리뷰책 작가
    private String rvBookWr;
    
    //리뷰책 출판사
    private String rvBookPb;
    
    
	public String getRvBookSj() {
		return rvBookSj;
	}

	public void setRvBookSj(String rvBookSj) {
		this.rvBookSj = rvBookSj;
	}

	public String getRvBookWr() {
		return rvBookWr;
	}

	public void setRvBookWr(String rvBookWr) {
		this.rvBookWr = rvBookWr;
	}

	public String getRvBookPb() {
		return rvBookPb;
	}

	public void setRvBookPb(String rvBookPb) {
		this.rvBookPb = rvBookPb;
	}

	public String getAtchFileNm() {
		return atchFileNm;
	}

	public void setAtchFileNm(String atchFileNm) {
		this.atchFileNm = atchFileNm;
	}

	
	
	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	
	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewSj() {
		return reviewSj;
	}

	public void setReviewSj(String reviewSj) {
		this.reviewSj = reviewSj;
	}

	public String getReviewCn() {
		return reviewCn;
	}

	public void setReviewCn(String reviewCn) {
		this.reviewCn = reviewCn;
	}

	public int getInqireCo() {
		return inqireCo;
	}

	public void setInqireCo(int inqireCo) {
		this.inqireCo = inqireCo;
	}

	public String getCreatIp() {
		return creatIp;
	}

	public void setCreatIp(String creatIp) {
		this.creatIp = creatIp;
	}

	public String getOthbcAt() {
		return othbcAt;
	}

	public void setOthbcAt(String othbcAt) {
		this.othbcAt = othbcAt;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public Date getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(Date frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public Date getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(Date lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMngAt() {
		return mngAt;
	}

	public void setMngAt(String mngAt) {
		this.mngAt = mngAt;
	}

	
	
	
	
	
}
