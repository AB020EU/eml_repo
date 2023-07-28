package co.absa.eml.applicationcapture;

import co.absa.eml.clients.ClientIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicationCaptureRepository extends JpaRepository<ApplicationCapture, String> {

}
