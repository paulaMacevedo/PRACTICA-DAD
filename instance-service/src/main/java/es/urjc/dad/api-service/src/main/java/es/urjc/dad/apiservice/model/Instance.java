package es.urjc.dad.apiservice.model;

public class Instance {
    private Long id;
    private String nombre;
    private Integer cpu;
    private Integer memory;
    private Integer disco;
    private String mascara;
    private String ip;
    private String status;
    private Object dependsOn;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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


    public Integer getDisco() {
        return disco;
    }
    
    public void setDisco(Integer disco) {
        this.disco = disco;
    }

    public String getMascara() {
        return mascara;
    }
     public void setMascara(String mascara) {
        this.mascara = mascara;
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

    public Object getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Object dependsOn) {
        this.dependsOn = dependsOn;
    }

  


  

   

    


}
