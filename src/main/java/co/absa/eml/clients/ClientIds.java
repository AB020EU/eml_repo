package co.absa.eml.clients;




import co.absa.eml.utilities.Auditable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientIds extends Auditable<String> {

    @Id
    String id;
    String idNumber;
    String type;
    String profile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    //sort
}
