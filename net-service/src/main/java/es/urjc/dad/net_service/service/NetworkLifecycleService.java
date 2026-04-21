package es.urjc.dad.net_service.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.urjc.dad.net_service.dto.IpAssignRequest;
import es.urjc.dad.net_service.dto.IpAssignResponse;
import es.urjc.dad.net_service.dto.IpUnassignRequest;
import es.urjc.dad.net_service.dto.IpUnassignResponse;
import es.urjc.dad.net_service.model.Ip;
import es.urjc.dad.net_service.model.IpAssignment;
import es.urjc.dad.net_service.model.Network;
import es.urjc.dad.net_service.repository.IpAssignmentRepository;
import es.urjc.dad.net_service.repository.NetworkRepository;

@Service
public class NetworkLifecycleService {

	private final NetworkRepository networkRepository;
	private final IpAssignmentRepository ipAssignmentRepository;

	public NetworkLifecycleService(NetworkRepository networkRepository,
			IpAssignmentRepository ipAssignmentRepository) {
		this.networkRepository = networkRepository;
		this.ipAssignmentRepository = ipAssignmentRepository;
	}

	@Transactional
	public void createNetwork(String mask) {
		if (mask == null || mask.isBlank()) {
			return;
		}

		if (networkRepository.findByMask(mask).isPresent()) {
			return;
		}

		networkRepository.save(new Network(mask));
	}

	@Transactional
	public void deleteNetwork(Long networkId) {
		if (networkId == null) {
			return;
		}

		Optional<Network> networkOptional = networkRepository.findById(networkId);
		if (networkOptional.isEmpty()) {
			return;
		}

		Network network = networkOptional.get();
		if (!network.getAssignedIps().isEmpty()) {
			return;
		}

		networkRepository.delete(network);
	}

	@Transactional
	public IpAssignResponse assignIp(IpAssignRequest request) {
		if (request == null || request.getServerName() == null || request.getMask() == null) {
			return new IpAssignResponse(null, null, null, false, "Invalid IP assignment request");
		}

		Optional<Network> networkOptional = networkRepository.findByMask(request.getMask());
		if (networkOptional.isEmpty()) {
			return new IpAssignResponse(request.getServerName(), request.getMask(), null, false,
					"Network not found");
		}

		Network network = networkOptional.get();

		Optional<IpAssignment> existingAssignment =
				ipAssignmentRepository.findByServerName(request.getServerName());
		if (existingAssignment.isPresent()) {
			Ip ip = existingAssignment.get().getAssignedIp();
			return new IpAssignResponse(request.getServerName(), request.getMask(),
					ip.toIpv4String(), true, null);
		}

		int[] base = parseMaskBase(request.getMask());
		if (base == null) {
			return new IpAssignResponse(request.getServerName(), request.getMask(), null, false,
					"Invalid network mask format");
		}

		Set<Integer> usedLastOctets = new HashSet<>();
		for (IpAssignment assignment : network.getAssignedIps()) {
			if (assignment.getAssignedIp() != null && assignment.getAssignedIp().getD() != null) {
				usedLastOctets.add(assignment.getAssignedIp().getD());
			}
		}

		Integer freeLastOctet = null;
		for (int d = 1; d <= 255; d++) {
			if (!usedLastOctets.contains(d)) {
				freeLastOctet = d;
				break;
			}
		}

		if (freeLastOctet == null) {
			return new IpAssignResponse(request.getServerName(), request.getMask(), null, false,
					"No IPs available in the network");
		}

		Ip assignedIp = new Ip(base[0], base[1], base[2], freeLastOctet);
		IpAssignment ipAssignment = new IpAssignment(request.getServerName(), assignedIp, network);
		network.addIpAssignment(ipAssignment);
		networkRepository.save(network);

		return new IpAssignResponse(request.getServerName(), request.getMask(),
				assignedIp.toIpv4String(), true, null);
	}

	@Transactional
	public IpUnassignResponse unassignIp(IpUnassignRequest request) {
		if (request == null || request.getServerName() == null
				|| request.getServerName().isBlank()) {
			return new IpUnassignResponse(null, false, "Invalid unassignment request");
		}

		Optional<IpAssignment> assignmentOptional =
				ipAssignmentRepository.findByServerName(request.getServerName());
		if (assignmentOptional.isEmpty()) {
			return new IpUnassignResponse(request.getServerName(), false,
					"IP assignment not found");
		}

		ipAssignmentRepository.delete(assignmentOptional.get());
		return new IpUnassignResponse(request.getServerName(), true, "IP released");
	}

	private int[] parseMaskBase(String mask) {
		String[] parts = mask.split("\\.");
		if (parts.length != 4) {
			return null;
		}

		try {
			int a = Integer.parseInt(parts[0]);
			int b = Integer.parseInt(parts[1]);
			int c = Integer.parseInt(parts[2]);
			int d = Integer.parseInt(parts[3]);
			if (!isOctet(a) || !isOctet(b) || !isOctet(c) || !isOctet(d)) {
				return null;
			}
			return new int[] {a, b, c};
		} catch (NumberFormatException ex) {
			return null;
		}
	}

	private boolean isOctet(int value) {
		return value >= 0 && value <= 255;
	}
}
