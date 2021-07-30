package com.clevered.rest.api.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class CodeResponseWrapper {
	private String status;
	private List<CodeResponse> javaCodeResponseList;
	private int totalTestCasePassed;
	private int totalTestCaseFailed;
	private String comment;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CodeResponse> getJavaCodeResponseList() {
		return javaCodeResponseList;
	}

	public void setJavaCodeResponseList(List<CodeResponse> javaCodeResponseList) {
		this.javaCodeResponseList = javaCodeResponseList;
	}

	public int getTotalTestCasePassed() {
		return totalTestCasePassed;
	}

	public void setTotalTestCasePassed(int totalTestCasePassed) {
		this.totalTestCasePassed = totalTestCasePassed;
	}

	public int getTotalTestCaseFailed() {
		return totalTestCaseFailed;
	}

	public void setTotalTestCaseFailed(int totalTestCaseFailed) {
		this.totalTestCaseFailed = totalTestCaseFailed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "JavaCodeResponseWrapper [javaCodeResponseList=" + javaCodeResponseList + ", totalTestCasePassed="
				+ totalTestCasePassed + ", totalTestCaseFailed=" + totalTestCaseFailed + ", comment=" + comment + "]";
	}

}
