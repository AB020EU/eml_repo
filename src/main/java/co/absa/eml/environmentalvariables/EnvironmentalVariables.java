package co.absa.eml.environmentalvariables;

import co.absa.eml.utilities.Auditable;
import co.absa.eml.utilities.Auditable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EnvironmentalVariables extends Auditable<String> {
    @Id
    String id;
    String environment;
    String nucleusUrl;
    String chrome;
    String gridUrl;
    String Status;
    String absaezvalUrl;

    public String getAbsaezvalUrl() {
        return absaezvalUrl;
    }

    public void setAbsaezvalUrl(String absaezvalUrl) {
        this.absaezvalUrl = absaezvalUrl;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getNucleusUrl() {
        return nucleusUrl;
    }

    public void setNucleusUrl(String nucleusUrl) {
        this.nucleusUrl = nucleusUrl;
    }

    public String getChrome() {
        return chrome;
    }

    public void setChrome(String chrome) {
        this.chrome = chrome;
    }

    public String getGridUrl() {
        return gridUrl;
    }

    public void setGridUrl(String gridUrl) {
        this.gridUrl = gridUrl;
    }


}
