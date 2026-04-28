package es.urjc.dad.api_service.service;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import es.urjc.dad.api_service.model.Network;

@Service
public class NetworkService {

    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<Network> NETWORK_ROW_MAPPER = (rs, rowNum) -> {
        Network network = new Network();
        network.setId(rs.getLong("id"));
        network.setMask(rs.getString("mask"));
        return network;
    };

    public NetworkService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Network> findAll() {
        return jdbcTemplate.query("SELECT id, mask FROM network", NETWORK_ROW_MAPPER);
    }

    public Optional<Network> findById(Long id) {
        List<Network> networks = jdbcTemplate.query(
            "SELECT id, mask FROM network WHERE id = ?",
            NETWORK_ROW_MAPPER,
            id
        );
        return networks.stream().findFirst();
    }
}