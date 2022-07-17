package egovframework.let.reply.service;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import egovframework.com.cmm.ComDefaultVO;

public class ReplyVO extends ComDefaultVO {
	
	//댓글번호
	private String repNo;
	
	//댓글내용
	private String repContent;
	
	//댓글작성자
	private String repWriter;
	
	//댓글날짜
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date repRegDate;
	
	//댓글리뷰글번호
	private String repReviewId;

	//사용여부
	private String useAt;
	
	//댓글문구글번호
	private String repSentcId;
	
	
	
	public String getRepSentcId() {
		return repSentcId;
	}

	public void setRepSentcId(String repSentcId) {
		this.repSentcId = repSentcId;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getRepNo() {
		return repNo;
	}

	public void setRepNo(String repNo) {
		this.repNo = repNo;
	}

	public String getRepContent() {
		return repContent;
	}

	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}

	public String getRepWriter() {
		return repWriter;
	}

	public void setRepWriter(String repWriter) {
		this.repWriter = repWriter;
	}

	public Date getRepRegDate() {
		return repRegDate;
	}

	public void setRepRegDate(Date repRegDate) {
		this.repRegDate = repRegDate;
	}

	public String getRepReviewId() {
		return repReviewId;
	}

	public void setRepReviewId(String repReviewId) {
		this.repReviewId = repReviewId;
	}
	
	
}
