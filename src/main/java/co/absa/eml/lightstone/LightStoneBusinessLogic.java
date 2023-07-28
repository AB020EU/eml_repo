package co.absa.eml.lightstone;

import co.absa.eml.clients.Connections;
import co.absa.eml.dto.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import co.absa.eml.clients.ClientIds;
import co.absa.eml.clients.ClientIdsRepository;

import co.absa.eml.dto.RestResponse;
import co.absa.eml.property.Property;
import co.absa.eml.property.PropertyRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class
LightStoneBusinessLogic {

    @Autowired
    LightStonePropertyRepository lightStonePropertyRepository;
    @Autowired
    Connections connections;
    @Autowired
    ClientIdsRepository clientIdsRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public ResponseEntity<?> importPropertiesFromLightStone(ArrayList<LightStonePropertiesEntity> properties) {

        RestResponse restResponse= new RestResponse();

        try {

            for(int i = 0; i < properties.size(); i++){
                LightStonePropertiesEntity tempProperty = properties.get(i);
                tempProperty.setId(properties.get(i).getProp_id());
                lightStonePropertyRepository.save(tempProperty);

                /*if(!properties.get(i).getTownship().equalsIgnoreCase("WINDSORTON")){
                    lightStonePropertyRepository.save(tempProperty);
                }*/
            }

            restResponse.setMessage("Properties Imported");
            restResponse.setStatus("200");

        }catch (Exception e){
            
            //lightStonePropertyRepository.saveAll(properties);

            restResponse.setMessage("Properties Import Filed - " + e.getMessage());
            restResponse.setStatus("400");
        }

        return ResponseEntity.status(HttpStatus.OK).body(restResponse);
    }

    public ResponseEntity<?> extractTestIds() {

        List<LightStonePropertiesEntity> allProperties = lightStonePropertyRepository.findAll();

        for(int i = 0; i < allProperties.size(); i++){

            try{



                if(allProperties.get(i).getBuyer_idck().length() == 13){
                    Long.valueOf(allProperties.get(i).getBuyer_idck());
                    ClientIds clientIds = new ClientIds();


                    clientIds.setId(allProperties.get(i).getBuyer_idck());
                    clientIds.setIdNumber(allProperties.get(i).getBuyer_idck());
                    clientIds.setProfile("New To Bank");
                    clientIds.setType("New To Bank");

                    clientIdsRepository.save(clientIds);
                }
            }
            catch(Exception e){
                
            }
        }

        RestResponse restResponse = new RestResponse();

        restResponse.setStatus("200");
        restResponse.setMessage("New to bank ids imported");

        return ResponseEntity.status(HttpStatus.OK).body(restResponse);
    }

    public ResponseEntity<?> extractProperties() {

        List<LightStonePropertiesEntity> allProperties = lightStonePropertyRepository.findAll();

        for(int i = 0; i < allProperties.size(); i++){

            try{
                if(allProperties.get(i).getBuyer_idck().length() == 13){
                    Property property = new Property();
                    Long.valueOf(allProperties.get(i).getBuyer_idck());

                    property.setId(allProperties.get(i).getBuyer_idck());
                    property.setIdNumber(allProperties.get(i).getBuyer_idck());
                    //property.setStreet(allProperties.get(i).getStreet_number() + " " + allProperties.get(i).getStreet_name()
                    //+ " " + allProperties.get(i).getStreet_type().toLowerCase());
                    property.setType(allProperties.get(i).getProperty_type_full());
                    property.setTownship(allProperties.get(i).getDeedtown());
                    property.setSgCode(allProperties.get(i).sg_code);

                    Connection geospatialConnection = connections.getGeospatialConnection();
                    Statement statement = geospatialConnection.createStatement();

                    ResultSet rs = statement.executeQuery("SELECT * FROM geospatial.geo_property where sg_code = '"+property.getSgCode()+"'");

                    while(rs.next()){
                        if(rs.getString("property_type") != null && rs.getString("property_type").equalsIgnoreCase("E")){
                            property.setType("FREEHOLD");
                        }else if (rs.getString("property_type") != null && rs.getString("property_type").equalsIgnoreCase("U")) {
                            property.setType("SECTIONAL TITLE");
                        }else if (rs.getString("property_type") != null && rs.getString("property_type").equalsIgnoreCase("H")) {
                            property.setType("Residential Small Holding");
                        }else if (rs.getString("property_type") != null && rs.getString("property_type").equalsIgnoreCase("-")) {
                            property.setType("FREEHOLD");
                        }else if (rs.getString("property_type") != null && rs.getString("property_type").equalsIgnoreCase("D")) {
                            property.setType("SECTIONAL TITLE");
                        }
                    }

                    propertyRepository.save(property);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        RestResponse restResponse = new RestResponse();

        restResponse.setStatus("200");
        restResponse.setMessage("Properties imported");

        return ResponseEntity.status(HttpStatus.OK).body(restResponse);
    }

    public ResponseEntity<?> findPropertyById(String id) {

        RestResponse restResponse = new RestResponse();
        try{
            List<LightStonePropertiesEntity> all = lightStonePropertyRepository.findAll();
            for(int i=0;i<all.size();i++){
                try {
                    if (all.get(i).getBuyer_idck().equalsIgnoreCase(id)) {
                        return ResponseEntity.status(HttpStatus.OK).body(all.get(i));
                    }
                }catch (Exception e){
                    
                }
            }
            restResponse.setStatus("200");
            restResponse.setMessage("No property matching "+id+" found");
            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
            //Optional<LightStonePropertiesEntity> byId = lightStonePropertyRepository.findByBuyeIdck(id);
//            if(byId.isPresent()){
//                return  ResponseEntity.status(HttpStatus.OK).body(byId.get());
//            }else{
//                restResponse.setStatus("200");
//                restResponse.setMessage("No property matching "+id+" found");
//                return ResponseEntity.status(HttpStatus.OK).body(restResponse);
//            }

        }catch(Exception e){
            
            restResponse.setStatus("400");
            restResponse.setMessage("An error occurred while retrieving property by id :"+ id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }
    }

    public ResponseEntity<?> updatePropertyTypes(){
        try{
            
            Connection geospatialConnection = connections.getGeospatialConnection();
            Statement statement = geospatialConnection.createStatement();

            List<Property> propertyList = propertyRepository.findAll();


            for (int i=0; i<propertyList.size();i++){
                Property tempProperty = propertyList.get(i);

                String sgCode = tempProperty.getSgCode();
                ResultSet rs = statement.executeQuery("SELECT * FROM geospatial.geo_property where sg_code = '"+sgCode+"'");

                while(rs.next()){
                    if(rs.getString("property_type") != null && rs.getString("property_type").equalsIgnoreCase("E")){
                        tempProperty.setType("FREEHOLD");
                    }else if (rs.getString("property_type") != null && rs.getString("property_type").equalsIgnoreCase("U")) {
                        tempProperty.setType("SECTIONAL TITLE");
                    }else if (rs.getString("property_type") != null && rs.getString("property_type").equalsIgnoreCase("H")) {
                        tempProperty.setType("Residential Small Holding");
                    }else if (rs.getString("property_type") != null && rs.getString("property_type").equalsIgnoreCase("-")) {
                        tempProperty.setType("FREEHOLD");
                    }else if (rs.getString("property_type") != null && rs.getString("property_type").equalsIgnoreCase("D")) {
                        tempProperty.setType("SECTIONAL TITLE");
                    }
                }
                propertyRepository.save(tempProperty);
            }

            RestResponse restResponse = new RestResponse();
            restResponse.setStatus("200");
            restResponse.setMessage("Property types updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }catch (Exception e){
            
            RestResponse restResponse = new RestResponse();
            restResponse.setStatus("400");
            restResponse.setMessage("An error occurred while updating property types");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }
    }
    public ResponseEntity<?> test() {

        List<LightStonePropertiesEntity> allProperties = lightStonePropertyRepository.findAll();






        RestResponse restResponse = new RestResponse();

        restResponse.setStatus("200");
        restResponse.setMessage("New to bank ids imported");

        return ResponseEntity.status(HttpStatus.OK).body(restResponse);
    }
}
