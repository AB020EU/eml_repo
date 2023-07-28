package co.absa.eml.dto;

public class CaptureApplication {
String abNumber;
int iterations;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbNumber() {
        return abNumber;
    }

    public void setAbNumber(String abNumber) {
        this.abNumber = abNumber;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
