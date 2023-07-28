package co.absa.eml.applicationcapture;

import co.absa.eml.clients.Connections;
import co.absa.eml.dto.DataDto;
import co.absa.eml.dto.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
@Component
public class ApplicationCaptureData {
    public ResponseEntity<?> getApplicationData() {
        RestResponse restResponse = new RestResponse();
        Connections connections = new Connections();
        try {DataDto dataDto = new DataDto();

            Connection nucleusConnection = connections.getEomlDbConnection();
            Statement statement = nucleusConnection.createStatement();
            ResultSet rs = statement.executeQuery("select id_number,erf,street_number,street_name,suburb from light_stone_properties_entity,property");
            ArrayList<String> testList = new ArrayList<>();
            int count=0;
            while (rs.next()) {
                if (count>0) {
                    break;
                }
//                    testList.add(rs.getString("id_number"));
//                    testList.add(rs.getString("erf"));
//                    testList.add(rs.getString("street_number"));
//                    testList.add(rs.getString("street_name"));
//                    testList.add(rs.getString("suburb"));
                    count=count+1;

                dataDto.setId_number(rs.getString("id_number"));
                dataDto.setErf(rs.getString("erf"));
                dataDto.setStreet_number(rs.getString("street_number"));
                dataDto.setStreet_name(rs.getString("street_name"));
                dataDto.setSuburb(rs.getString("suburb"));



            }


            rs.close();
            nucleusConnection.close();
            statement.close();


            return ResponseEntity.status(HttpStatus.OK).body(dataDto);
        } catch (Exception e) {

            restResponse.setStatus("400");
            restResponse.setMessage("An error Occurred while returning Amendment details for specified application number" );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);

        }

    }
}
