package es.urjc.dad.api_service.model;

public class Instance {
    private Long id;
    private String name; // Server unique name;
    private String mask;// Network mask
    private String ip;// IP address of the server inside the subnet
    private String status;// Status of the server
    private Integer cpu;// Number of CPU cores
    private Integer memory; // Memory RAM in GB
    private Integer disk;// Disk space in GB
    private Instance dependsOn;// Instance that this instance depends on, if any

    // Default constructor for JPA
    public Instance(){

    }
     public Instance(String name, Integer memory, Integer cpu, Integer disk, String mask,
            String status, Instance dependsOn) {
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

    public Instance getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Instance dependsOn) {
        this.dependsOn = dependsOn;
    }



    }
