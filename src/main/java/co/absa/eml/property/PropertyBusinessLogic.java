package co.absa.eml.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import co.absa.eml.clients.Connections;
import co.absa.eml.dto.RestResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

@Component
public class PropertyBusinessLogic {
    @Autowired
    PropertyRepository propertyRepository;

    public ResponseEntity<?> getProperty(String propertyType) {
        try {
            List<Property> properties = propertyRepository.findAllByTypeIgnoreCase(propertyType);

            if (!properties.isEmpty()) {
                Random random = new Random();
                int propertyIndex = random.nextInt(properties.size() - 1);
                return ResponseEntity.status(HttpStatus.OK).body(properties.get(propertyIndex));
            } else {
                RestResponse restResponse = new RestResponse();
                restResponse.setMessage("No properties of type : " + propertyType + " found");
                restResponse.setStatus("200");
                return ResponseEntity.status(HttpStatus.OK).body(restResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            RestResponse restResponse = new RestResponse();
            restResponse.setMessage("An error occurred while retrieving property");
            restResponse.setStatus("400");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }
    }

    public ResponseEntity<?> getDevelopmentCode(String propertyType) {
        RestResponse restResponse = new RestResponse();
        try {

            Connections connections = new Connections();
            Connection nucleusConnection = connections.getNucleusConnection();
            Statement statement = nucleusConnection.createStatement();
            ResultSet rs = statement.executeQuery("select * from nucleus.new_development_property where development_type ='" + propertyType + "'");

            while (rs.next()) {
                NewDevelopmentPropertyDto newDevelopmentPropertyDto = new NewDevelopmentPropertyDto();
                newDevelopmentPropertyDto.setCode(rs.getString("code"));
                return ResponseEntity.status(HttpStatus.OK).body(newDevelopmentPropertyDto);
            }

            restResponse.setMessage("No property matching property type " + propertyType + " found");
            restResponse.setStatus("500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);

        } catch (Exception e) {
            restResponse.setMessage("An error occurred while retrieving development code");
            restResponse.setStatus("500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }
    }
}