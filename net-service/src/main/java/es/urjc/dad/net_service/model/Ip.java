package es.urjc.dad.net_service.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Ip {

	private Integer a;
	private Integer b;
	private Integer c;
	private Integer d;

	// Default constructor required by JPA
	public Ip() {}

	public Ip(Integer a, Integer b, Integer c, Integer d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	// --- Getters and Setters ---
	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}

	public Integer getB() {
		return b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

	public Integer getC() {
		return c;
	}

	public void setC(Integer c) {
		this.c = c;
	}

	public Integer getD() {
		return d;
	}

	public void setD(Integer d) {
		this.d = d;
	}

	public String toIpv4String() {
		return a + "." + b + "." + c + "." + d;
	}
}
