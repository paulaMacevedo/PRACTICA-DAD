package es.urjc.dad.instanceservice.model;

import jakarta.persistence.*;

@Entity
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false)
    private String name; // Server unique name;

    private Integer memory; // Memory RAM in GB
    private Integer cpu; // Number of CPU cores
    private Integer disk; // Disk space in GB
    private String mask; // Network mask
    private String ip; // IP address of the server inside the subnet
    private String status; // Status of the server

    @Column(name = "depends_on")
    private String dependsOn; // Instance that this instance depends on, if any

    public Instance() {} // Default constructor for JPA

    // Constructor for the creation of an instance

    public Instance(String name, Integer memory, Integer cpu, Integer disk, String mask,
            String status, String dependsOn) {
        this.name = name;
        this.memory = memory;
        this.cpu = cpu;
        this.disk = disk;
        this.mask = mask;
        this.status = "PENDING";
        this.dependsOn = dependsOn;
        this.ip = null;
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

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getDisk() {
        return disk;
    }

    public void setDisk(Integer disk) {
        this.disk = disk;
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

    public String getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String dependsOn) {
        this.dependsOn = dependsOn;
    }
}
