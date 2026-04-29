package es.urjc.dad.instanceservice.dto;

public class IpAssignRequest {

    private String serverName;
    private String mask;

    public IpAssignRequest() {}

    public IpAssignRequest(String serverName, String mask) {
        this.serverName = serverName;
        this.mask = mask;
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
}
