package es.urjc.dad.instanceservice.dto;

public class IpUnassignRequest {

    private String serverName;

    public IpUnassignRequest() {}

    public IpUnassignRequest(String serverName) {
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
