package com.clevered.rest.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Nagendra Kumar
 * @Since 14-JUN-2021
 *  
 */
@JsonInclude(value=Include.NON_NULL)
public class CodeResponse {
	private String status;
	private String compilationError;
	private boolean isGraph;
	private String image;
	private String input;
	private String output;
	private String description;
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isGraph() {
		return isGraph;
	}

	public void setGraph(boolean isGraph) {
		this.isGraph = isGraph;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompilationError() {
		return compilationError;
	}

	public void setCompilationError(String compilationError) {
		this.compilationError = compilationError;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CodeResponse [status=" + status + ", compilationError=" + compilationError + ", input=" + input
				+ ", output=" + output + ", description=" + description + "]";
	}

}
