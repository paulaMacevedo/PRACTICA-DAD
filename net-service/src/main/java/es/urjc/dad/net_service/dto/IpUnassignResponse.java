package es.urjc.dad.net_service.dto;

public class IpUnassignResponse {

	private String serverName;
	private boolean success;
	private String message;

	public IpUnassignResponse() {}

	public IpUnassignResponse(String serverName, boolean success, String message) {
		this.serverName = serverName;
		this.success = success;
		this.message = message;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
