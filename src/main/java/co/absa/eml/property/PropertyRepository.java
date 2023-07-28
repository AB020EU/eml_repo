package co.absa.eml.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, String> {

    List<Property> findAllByTypeIgnoreCase(String type);
}
