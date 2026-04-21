package es.urjc.dad.net_service.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Network {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// E.g., "172.1.0.0"
	@Column(unique = true, nullable = false)
	private String mask;

	// One Network has many IpAssignments.
	// 'mappedBy' indicates that 'network' field in IpAssignment owns the relationship.
	// cascade = CascadeType.ALL ensures that saving a network saves its assignments.
	@OneToMany(mappedBy = "network", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<IpAssignment> assignedIps = new ArrayList<>();

	public Network() {}

	public Network(String mask) {
		this.mask = mask;
	}

	// Helper method to add an assignment to the list easily
	public void addIpAssignment(IpAssignment assignment) {
		assignedIps.add(assignment);
		assignment.setNetwork(this);
	}

	// Helper method to calculate available IPs.
	// Max host IPs in this project is 255 (.1 to .255).
	public int getAvailableIpsCount() {
		return 255 - assignedIps.size();
	}

	// --- Getters and Setters ---
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

	public List<IpAssignment> getAssignedIps() {
		return assignedIps;
	}

	public void setAssignedIps(List<IpAssignment> assignedIps) {
		this.assignedIps = assignedIps;
	}
}
