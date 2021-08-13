package com.clevered.rest.api.vo;

/**
 * 
 * @author Nagendra Kumar
 * @Since 14-JUN-2021
 * 
 */
//@JsonInclude(value = Include.NON_NULL)
public class CodeResponse {
	private String status;
	private boolean isGraph;
	private boolean isError;
	private String stdout;
	private String stderr;

	public boolean getIsError() {
		return isError;
	}

	public void setIsError(boolean isError) {
		this.isError = isError;
	}

	public String getStdout() {
		return stdout;
	}

	public void setStdout(String stdout) {
		this.stdout = stdout;
	}

	public String getStderr() {
		return stderr;
	}

	public void setStderr(String stderr) {
		this.stderr = stderr;
	}

	public boolean isGraph() {
		return isGraph;
	}

	public void setGraph(boolean isGraph) {
		this.isGraph = isGraph;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CodeResponse [status=" + status + ", isGraph=" + isGraph + ", isError=" + isError + ", stdout=" + stdout
				+ ", stderr=" + stderr + "]";
	}

}
