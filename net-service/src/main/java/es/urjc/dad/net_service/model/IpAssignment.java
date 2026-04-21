package es.urjc.dad.net_service.model;

import jakarta.persistence.*;

@Entity
public class IpAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serverName;

    @Embedded
    private Ip assignedIp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "network_id")
    private Network network;

    public IpAssignment() {
    }

    public IpAssignment(String serverName, Ip assignedIp, Network network) {
        this.serverName = serverName;
        this.assignedIp = assignedIp;
        this.network = network;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getServerName() { return serverName; }
    public void setServerName(String serverName) { this.serverName = serverName; }

    public Ip getAssignedIp() { return assignedIp; }
    public void setAssignedIp(Ip assignedIp) { this.assignedIp = assignedIp; }

    public Network getNetwork() { return network; }
    public void setNetwork(Network network) { this.network = network; }
}