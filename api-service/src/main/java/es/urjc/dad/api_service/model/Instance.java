package es.urjc.dad.api_service.model;

import com.fasterxml.jackson.annotation.JsonAlias;


public class Instance {
    private Long id;
    private String name; // Server unique name;
    private String mask;// Network mask
    private String ip;// IP address of the server inside the subnet
    private String status;// Status of the server
    @JsonAlias({"cpus", "cpu"})
    private Integer cpu;// Number of CPU cores
    @JsonAlias("memory")
    private Integer memory; // Memory RAM in GB
    @JsonAlias("disk")
    private Integer disk;// Disk space in GB
    @JsonAlias("dependsOn")
    private String dependsOn;// Instance that this instance depends on, if any

    // Default constructor for JPA
    public Instance() {

    }



    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getDisk() {
        return disk;
    }

    public void setDisk(Integer disk) {
        this.disk = disk;
    }

    public String getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String dependsOn) {
        this.dependsOn = dependsOn;
    }



}
