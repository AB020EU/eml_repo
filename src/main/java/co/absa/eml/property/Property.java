package co.absa.eml.property;







import co.absa.eml.utilities.Auditable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Property extends Auditable<String> {

    @Id
    String id;
    String idNumber;
    String street;
    String township;
    String type;
    String sgCode;

    public String getSgCode() {
        return sgCode;
    }

    public void setSgCode(String sgCode) {
        this.sgCode = sgCode;
    }

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
