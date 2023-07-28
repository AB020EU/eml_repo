package co.absa.eml.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientIdsRepository extends JpaRepository<ClientIds, String> {
    List<ClientIds> findAllByType(String type);
    List<ClientIds> findAllByTypeOrType(String type, String type2);
    @Query("select distinct c.type from ClientIds c")
    List<String> findDistinctType();
    void deleteAllByType(String type);
}
