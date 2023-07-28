package co.absa.eml.restinteractionpoints;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.absa.eml.config.Config;
import co.absa.eml.config.ConfigRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestInteractionPoints {
    @Autowired

    ConfigRepository configRepository;
    String baseURI;


    public Response post(String endPoint, String body) {
        baseURI = getBaseURI();
        return given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .request().body(body).post("http://localhost:8083"+endPoint);
    }
    public Response post(String endPoint) {
        baseURI = getBaseURI();
        return given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .request().post(baseURI+endPoint);
    }

    public Response put(String endPoint, String body) {
        baseURI = getBaseURI();
        return given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .request().body(body)
                .put(baseURI+endPoint);
    }

    public Response get(String endPoint) {
        baseURI = getBaseURI();
        return given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .request()
                .get("http://localhost:8083"+endPoint);

    }

    public Response get(String endPoint, String body) {
        baseURI = getBaseURI();
        return given().relaxedHTTPSValidation().contentType(ContentType.JSON).request().body(body).get(baseURI+endPoint);
    }

    public String getBaseURI() {

        try{
            String environment = getProperties("environment");
            Optional<Config> byEnvironment = configRepository.findByEnvironment(environment);
            return byEnvironment.get().getBackendServer();
        }catch (Exception e){

            return null;
        }
    }

    public  String getProperties(String key)throws Exception{
        File file = new File("src/main/resources/application.properties");

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {

        }
        Properties prop = new Properties();
        try {
            prop.load(fileInput);
        } catch (IOException e) {

        }
        return    prop.getProperty(key);
    }


}
