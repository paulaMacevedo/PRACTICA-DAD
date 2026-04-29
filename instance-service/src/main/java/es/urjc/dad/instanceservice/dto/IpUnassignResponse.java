package es.urjc.dad.instanceservice.dto;

public class IpUnassignResponse {

    private String serverName;
    private boolean success;
    private String error;

    public IpUnassignResponse() {}

    public IpUnassignResponse(String serverName, boolean success, String error) {
        this.serverName = serverName;
        this.success = success;
        this.error = error;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }



}
