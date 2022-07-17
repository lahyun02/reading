package egovframework.let.sentc.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.com.cmm.ComDefaultVO;
import java.util.Date;

public class SentcVO extends ComDefaultVO implements Serializable {
	
	//게시판ID
	private String sentcId;
	
	//게시물명
	private String sentcSj;

	//게시물 내용
	private String sentcCn;
	
	//조회수
	private int inqireCo;
	
	//등록자IP
	private String creatIp;
	
	//공지여부
	private String noticeAt;
	
	//비공개여부
	private String othbcAt;
	
	//사용여부
	private String useAt;
	
	//첨부파일ID
	private String atchFileId;
	
	//최초등록시점
	private java.util.Date frstRegistPnttm;
	
	//최초등록자ID
	private String frstRegisterId;
	
	//최종수정시점
	private java.util.Date lastUpdtPnttm;
	
	//최종수정자ID
	private String lastUpdusrId;
	
	//사용자ID
	private String userId;
	
	//관리자여부
	private String mngAt;
	
	//메인여부 
	private String mainAt;
	
	

	public String getMainAt() {
		return mainAt;
	}

	public void setMainAt(String mainAt) {
		this.mainAt = mainAt;
	}

	public String getSentcId() {
		return sentcId;
	}

	public void setSentcId(String sentcId) {
		this.sentcId = sentcId;
	}

	public String getSentcSj() {
		return sentcSj;
	}

	public void setSentcSj(String sentcSj) {
		this.sentcSj = sentcSj;
	}

	public String getSentcCn() {
		return sentcCn;
	}

	public void setSentcCn(String sentcCn) {
		this.sentcCn = sentcCn;
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

	public String getNoticeAt() {
		return noticeAt;
	}

	public void setNoticeAt(String noticeAt) {
		this.noticeAt = noticeAt;
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

	public java.util.Date getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(java.util.Date frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public java.util.Date getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(java.util.Date lastUpdtPnttm) {
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
