package es.urjc.dad.api_service.model;

public class Network {
    private Long id;
    private String mask;

    public Network() {}

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getMask() { 
        return mask; 
    }
    public void setMask(String mask) { 
        this.mask = mask; 
    }
}
