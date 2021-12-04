package com.clevered.rest.api.vo;

/**
 * 
 * @author Nagendra Kumar
 * @Since 14-JUN-2021
 * 
 */
//@JsonInclude(value = Include.NON_NULL)
public class CodeResponse {
	private boolean isError;
	private String stdout="";
	private String stderr="";
	private String combined;
	private String url;
	private boolean killedByContainer=false;
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCombined() {
		return combined;
	}

	public void setCombined(String combined) {
		this.combined = combined;
	}

	public boolean isKilledByContainer() {
		return killedByContainer;
	}

	public void setKilledByContainer(boolean killedByContainer) {
		this.killedByContainer = killedByContainer;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

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

	@Override
	public String toString() {
		return "CodeResponse [isError=" + isError + ", stdout=" + stdout + ", stderr=" + stderr + ", combined="
				+ combined + ", killedByContainer=" + killedByContainer + "]";
	}

	

}
