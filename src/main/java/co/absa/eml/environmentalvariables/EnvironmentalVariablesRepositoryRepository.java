package co.absa.eml.environmentalvariables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvironmentalVariablesRepositoryRepository extends JpaRepository<EnvironmentalVariables, String> {
    Optional<EnvironmentalVariables> findById(String id);
    Optional<EnvironmentalVariables> findByEnvironment(String environment);

}
