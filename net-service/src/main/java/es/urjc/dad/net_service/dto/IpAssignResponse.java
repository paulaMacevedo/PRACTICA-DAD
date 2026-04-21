package es.urjc.dad.net_service.dto;

public class IpAssignResponse {

	private String serverName;
	private String mask;
	private String ip;
	private boolean success;
	private String error;

	public IpAssignResponse() {}

	public IpAssignResponse(String serverName, String mask, String ip, boolean success,
			String error) {
		this.serverName = serverName;
		this.mask = mask;
		this.ip = ip;
		this.success = success;
		this.error = error;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
