package co.absa.eml.applicationcapture;




import co.absa.eml.utilities.Auditable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApplicationCapture extends Auditable<String> {

    @Id
    String id;
    String applicationNumber;
    String abNumber;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getAbNumber() {
        return abNumber;
    }

    public void setAbNumber(String abNumber) {
        this.abNumber = abNumber;
    }


}
