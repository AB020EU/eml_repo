package co.absa.eml.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import co.absa.eml.dto.DocumentChecklist;
import co.absa.eml.dto.RestResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Component
public class NucluesActioner {

    @Autowired
    ClientIdsRepository clientIdsRepository;

    @Autowired
    Connections connections;
    public ResponseEntity<?> getDocumentCheckListIds(String app) {

        try {

            
            Connection nucleusConnection = connections.getNucleusConnectionS2(app);
            Statement statement = nucleusConnection.createStatement();
            ResultSet rs = statement.executeQuery( "select * from nucleus.linked_document where application_number = '" + app + "'" );

            ArrayList<DocumentChecklist> documentIds = new ArrayList<>();
            HashSet<String> documentTypes = new HashSet<>();

            while (rs.next()) {
                documentTypes.add(String.valueOf(rs.getString("type_name")));
            }

            for(String type : documentTypes){

                ResultSet rsSet = statement.executeQuery( "select * from nucleus.linked_document where application_number = '" + app + "' and type_name = '" + type + "'" );
                while (rsSet.next()) {

                    DocumentChecklist temp = new DocumentChecklist();
                    temp.setId(String.valueOf(rsSet.getInt("id")));
                    temp.setType(type);
                    temp.setDocumentName(rsSet.getString("document_name"));
                    documentIds.add(temp);
                    break;
                }
            }

            rs.close();
            statement.close();
            nucleusConnection.close();

            return ResponseEntity.status(HttpStatus.OK).body(documentIds);

        } catch (Exception e) {
            
            RestResponse restResponse = new RestResponse();
            restResponse.setMessage("Error fetching document check list ids");
            restResponse.setStatus("400");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }

    }
    public ResponseEntity<?> getDocumentCheckListIdsS2(String app) {

        try {
            
            Connection nucleusConnection = connections.getNucleusConnectionS2(app);
            Statement statement = nucleusConnection.createStatement();
            ResultSet rs = statement.executeQuery( "select * from nucleus.linked_document where application_number = '" + app + "'" );

            ArrayList<DocumentChecklist> documentIds = new ArrayList<>();
            HashSet<String> documentTypes = new HashSet<>();

            while (rs.next()) {
                documentTypes.add(String.valueOf(rs.getString("type_name")));
            }

            for(String type : documentTypes){

                ResultSet rsSet = statement.executeQuery( "select * from nucleus.linked_document where application_number = '" + app + "' and type_name = '" + type + "'" );
                while (rsSet.next()) {

                    DocumentChecklist temp = new DocumentChecklist();
                    temp.setId(String.valueOf(rsSet.getInt("id")));
                    temp.setType(type);
                    temp.setDocumentName(rsSet.getString("document_name"));
                    documentIds.add(temp);
                    break;
                }
            }

            rs.close();
            statement.close();
            nucleusConnection.close();

            return ResponseEntity.status(HttpStatus.OK).body(documentIds);

        } catch (Exception e) {
            
            RestResponse restResponse = new RestResponse();
            restResponse.setMessage("Error fetching document check list ids");
            restResponse.setStatus("400");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }

    }

    public ResponseEntity<?> importExistingClientIds() {

        try{

            Connection nucleusConnection = getNucleusConnection();
            Statement statement = nucleusConnection.createStatement();
            ResultSet rs = statement.executeQuery( "select * from nucleus.applicant where application_id in\n" +
                    "(select id from nucleus.application where Not status = 'INCOMPLETE')");

            ArrayList<ClientIds> clientIds = new ArrayList<>();

            while (rs.next()) {
                ClientIds clientIds1 = new ClientIds();

                clientIds1.setId(rs.getString("id_number"));
                clientIds1.setIdNumber(rs.getString("id_number"));
                clientIds1.setType("Existing Client");
                int numberOfProperties = rs.getInt("number_of_properties");
                String idNumber = rs.getString("id_number");

                if(numberOfProperties == 0){
                    clientIds1.setType("FTHB");
                    if(idNumber.startsWith("9")){

                        Statement statement2 = nucleusConnection.createStatement();
                        ResultSet rs2 = statement2.executeQuery( "select * from nucleus.applicant where id_number = '" + idNumber + "'");
                        boolean yp = true;
                        while (rs2.next()){
                            if(rs2.getInt("number_of_properties")> 0){
                                yp = false;
                            }
                        }
                        if(yp){
                            clientIds1.setType("YP");
                        }
                        statement2.close();
                        rs2.close();

                    }
                }

                if(numberOfProperties == 1){
                    clientIds1.setType("BuyToLet");
                }

                if(numberOfProperties > 3){
                    clientIds1.setType("FRI");
                }

                clientIds1.setProfile("");

                //ignore loyalty
                Optional<ClientIds> id_number = clientIdsRepository.findById(idNumber);
                if(id_number.isPresent()){
                    if(id_number.get().getType().equalsIgnoreCase("Loyalty")){
                        //do nothi  ng
                    }else{
                        clientIdsRepository.save(clientIds1);
                        clientIds.add(clientIds1);
                    }
                }else{
                    clientIdsRepository.save(clientIds1);
                    clientIds.add(clientIds1);
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(clientIds);

        }catch(Exception e){
            
            RestResponse restResponse = new RestResponse();
            restResponse.setMessage("Error importing client IDs from Nucleus");
            restResponse.setStatus("400");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);

        }
    }



    public static Connection getNucleusConnectionS21() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0932.corp.dsarena.com:5432/app_account",
                    "readonly", "READ0nly");
            System.out.println("Opened database successfully");



        } catch (Exception e) {
            
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return connection;
    }
    public static Connection getNucleusConnection() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0933.corp.dsarena.com:5433/app_account",
                    "readonly", "READ0nly");
            System.out.println("Opened database successfully");

        } catch (Exception e) {
            
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return connection;
    }
    public static Connection getNucleusConnectionProcessEngine() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0933.corp.dsarena.com:5433/app_account",
                    "process_readonly", "F2wgpJD8M1");
            System.out.println("Opened database successfully");

        } catch (Exception e) {
            
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return connection;
    }
    public static Connection getNucleusConnectionProcessEngineS2() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0932.corp.dsarena.com:5433/app_account",
                    "process_readonly", "Pr0C355_R34D0N_Y_69");
            System.out.println("Opened database successfully");





        } catch (Exception e) {
            
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return connection;
    }


    public static Connection getNucleusConnectionSitDb() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0932.corp.dsarena.com:5433/app_account",
                    "readonly", "READ0nly");
            System.out.println("Opened database successfully");





        } catch (Exception e) {
            
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return connection;
    }

}