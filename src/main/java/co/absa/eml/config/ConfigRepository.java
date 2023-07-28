package co.absa.eml.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigRepository extends JpaRepository<Config, String> {
    Optional<Config> findByEnvironment(String environment);
    Optional<Config> findByServerName(String serverName);
}
