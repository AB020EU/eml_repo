package co.absa.eml.config;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Config {
    @Id
    String id;
    String environment;
    String backendServer;
    String screenshotPath;
    String serverName;
    String pdfPath;
    String angularServer;

    public String getAngularServer() {
        return angularServer;
    }

    public void setAngularServer(String angularServer) {
        this.angularServer = angularServer;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
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

    public String getBackendServer() {
        return backendServer;
    }

    public void setBackendServer(String backendServer) {
        this.backendServer = backendServer;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }

    public void setScreenshotPath(String screenshotPath) {
        this.screenshotPath = screenshotPath;
    }
}
