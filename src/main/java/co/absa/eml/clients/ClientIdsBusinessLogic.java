package co.absa.eml.clients;

import co.absa.eml.dto.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class ClientIdsBusinessLogic {

    @Autowired
    ClientIdsRepository clientIdsRepository;

    public ResponseEntity<?> getAllClientDetails() {

        try {

            return ResponseEntity.status(HttpStatus.OK).body(clientIdsRepository.findAll());

        }catch (Exception e){

            RestResponse restResponse= new RestResponse();

            restResponse.setMessage("Failed to get Client Test Details Details - " + e.getMessage());
            restResponse.setStatus("400");

            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }

    }

    public ResponseEntity<?> getClientTestDetailsById(String id) {

        try {

            Optional<ClientIds> byId = clientIdsRepository.findById(id);

            if(byId.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(byId);
            }else{
                RestResponse restResponse = new RestResponse();

                restResponse.setMessage("Client details with id = " + id +" is not found");
                restResponse.setStatus("200");
                return ResponseEntity.status(HttpStatus.OK).body(restResponse);
            }

        }catch (Exception e){
            
            RestResponse restResponse = new RestResponse();

            restResponse.setMessage("Failed to get Client Details Details by Id - " + e.getMessage());
            restResponse.setStatus("400");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }
    }

    public ResponseEntity<?> createClientTestDetails(ClientIds clientIds) {

        try {
            clientIds.setManuallyCreated("Yes");
            clientIdsRepository.save(clientIds);

            RestResponse restResponse = new RestResponse();

            restResponse.setMessage("Client Test Details Created");
            restResponse.setStatus("200");

            return ResponseEntity.status(HttpStatus.OK).body(restResponse);

        }catch (Exception e){
            
            RestResponse restResponse = new RestResponse();

            restResponse.setMessage("Failed to Create Client Test Details - " + e.getMessage());
            restResponse.setStatus("400");

            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }
    }

    public ResponseEntity<?> getClientTypes(){
        try{
            List<String> distinctByType = clientIdsRepository.findDistinctType();
            if(distinctByType.isEmpty()){
                RestResponse restResponse = new RestResponse();
                restResponse.setMessage("No client ids found");
                restResponse.setStatus("200");
                return  ResponseEntity.status(HttpStatus.OK).body(restResponse);
            }
            return ResponseEntity.status(HttpStatus.OK).body(distinctByType);
        }catch(Exception e){
            RestResponse restResponse = new RestResponse();
            restResponse.setMessage("An error occurred while retrieving client types");
            restResponse.setStatus("400");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }
    }

    public ResponseEntity<?> importClientIds(){

        RestResponse restResponse = new RestResponse();
        try{
            Connections connections = new Connections();
            Connection nucleusConnection = connections.getNucleusConnection();
            Statement statement = nucleusConnection.createStatement();
            Statement applicantStatement = nucleusConnection.createStatement();
            Statement applicationStatement = nucleusConnection.createStatement();

            //ACCEPT
            ResultSet rs = statement.executeQuery("select * from nucleus.credit_assessment where decision='ACCEPT' order by id desc limit 1000");
            Set<String> acceptSet = new HashSet<>();
            while (rs.next()){
                acceptSet.add(rs.getString("application_number"));
                if(acceptSet.size()==999){
                    break;
                }
            }

            List<ClientIds> aipAcceptList = clientIdsRepository.findAllByType("AIP Accept");
            if(aipAcceptList.size()>0){
                for(int i=0; i<aipAcceptList.size();i++){
                    clientIdsRepository.deleteById(aipAcceptList.get(i).getId());
                }
            }


            for(String applicationNumber:acceptSet){
                ResultSet rsApplication = applicationStatement.executeQuery("SELECT * FROM nucleus.application WHERE application_number='"+applicationNumber+"'");
                while(rsApplication.next()){
                    ResultSet rsApplicant = applicantStatement.executeQuery("SELECT * FROM nucleus.applicant where application_id='"+rsApplication.getString("id")+"'");
                    while (rsApplicant.next()){
                        ClientIds clientIds = new ClientIds();
                        clientIds.setId(rsApplicant.getString("id_number"));
                        clientIds.setIdNumber(rsApplicant.getString("id_number"));
                        clientIds.setManuallyCreated("No");
                        clientIds.setType("AIP Accept");
                        clientIds.setProfile("AIP Accept");
                        clientIdsRepository.save(clientIds);
                    }
                }
            }

            //Fraud

            Statement fraudStatement = nucleusConnection.createStatement();
            Statement fraudApplicantStatement = nucleusConnection.createStatement();
            Statement fraudApplicationStatement = nucleusConnection.createStatement();

            ResultSet rsFraud = fraudStatement.executeQuery("select * from nucleus.credit_assessment where decision='FRAUD' order by id desc limit 1000");
            Set<String> fraudSet = new HashSet<>();
            while (rsFraud.next()){
                fraudSet.add(rsFraud.getString("application_number"));
                if(fraudSet.size()==999){
                    break;
                }
            }

            List<ClientIds> fraudList = clientIdsRepository.findAllByType("Fraud");
            if(fraudList.size()>0 && fraudSet.size()>0){
                for(int i=0; i<fraudList.size();i++){
                    clientIdsRepository.deleteById(fraudList.get(i).getId());
                }
            }


            for(String applicationNumber:fraudSet){
                ResultSet rsApplication = fraudApplicationStatement.executeQuery("SELECT * FROM nucleus.application WHERE application_number='"+applicationNumber+"'");
                while(rsApplication.next()){
                    ResultSet rsApplicant = fraudApplicantStatement.executeQuery("SELECT * FROM nucleus.applicant where application_id='"+rsApplication.getString("id")+"'");
                    while (rsApplicant.next()){
                        ClientIds clientIds = new ClientIds();
                        clientIds.setId(rsApplicant.getString("id_number"));
                        clientIds.setIdNumber(rsApplicant.getString("id_number"));
                        clientIds.setManuallyCreated("No");
                        clientIds.setType("Fraud");
                        clientIds.setProfile("Fraud");
                        clientIdsRepository.save(clientIds);
                    }
                }
            }

            //Credit Refer

            Statement referStatement = nucleusConnection.createStatement();
            Statement referApplicantStatement = nucleusConnection.createStatement();
            Statement referApplicationStatement = nucleusConnection.createStatement();

            ResultSet rsRefer = referStatement.executeQuery("select * from nucleus.credit_assessment where decision='REFER' order by id desc limit 1000");
            Set<String> referSet = new HashSet<>();
            while (rsRefer.next()){
                referSet.add(rsRefer.getString("application_number"));
                if(referSet.size()==999){
                    break;
                }
            }

            List<ClientIds> referList = clientIdsRepository.findAllByType("Credit Referral");
            if(referList.size()>0 && referSet.size()>0){
                for(int i=0; i<referList.size();i++){
                    clientIdsRepository.deleteById(referList.get(i).getId());
                }
            }


            for(String applicationNumber:referSet){
                ResultSet rsApplication = referApplicationStatement.executeQuery("SELECT * FROM nucleus.application WHERE application_number='"+applicationNumber+"'");
                while(rsApplication.next()){
                    ResultSet rsApplicant = referApplicantStatement.executeQuery("SELECT * FROM nucleus.applicant where application_id='"+rsApplication.getString("id")+"'");
                    while (rsApplicant.next()){
                        ClientIds clientIds = new ClientIds();
                        clientIds.setId(rsApplicant.getString("id_number"));
                        clientIds.setIdNumber(rsApplicant.getString("id_number"));
                        clientIds.setManuallyCreated("No");
                        clientIds.setType("Credit Referral");
                        clientIds.setProfile("Credit Referral");
                        clientIdsRepository.save(clientIds);
                    }
                }
            }

            //FTHB

            List<ClientIds> fthbList = clientIdsRepository.findAllByTypeOrType("First Time Home Buyer","FTHB");
            if(fthbList.size()>0){
                for(int i=0; i<fthbList.size();i++){
                    clientIdsRepository.deleteById(fthbList.get(i).getId());
                }
            }

            Statement fthbApplicantStatement = nucleusConnection.createStatement();
            Statement fthbApplicationStatement = nucleusConnection.createStatement();

            ResultSet rsApplication = fthbApplicationStatement.executeQuery("SELECT * FROM nucleus.application ORDER BY id DESC LIMIT 1000");
            int iteration = 0;
            while(rsApplication.next()) {
                ResultSet rsApplicant = fthbApplicantStatement.executeQuery("SELECT * FROM nucleus.applicant where application_id='" + rsApplication.getString("id") + "' and first_time_homebuyer=true limit 35");
                while (rsApplicant.next()) {
                    ClientIds clientIds = new ClientIds();
                    clientIds.setId(rsApplicant.getString("id_number"));
                    clientIds.setIdNumber(rsApplicant.getString("id_number"));
                    clientIds.setManuallyCreated("No");
                    if(iteration % 2 == 0){
                        clientIds.setType("First Time Home Buyer");
                        clientIds.setProfile("First Time Home Buyer");
                    }else{
                        clientIds.setType("FTHB");
                        clientIds.setProfile("FTHB");
                    }
                    clientIdsRepository.save(clientIds);
                    iteration++;
                }
            }


            restResponse.setMessage("Client IDs Imported Successfully");
            restResponse.setStatus("200");
            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }catch (Exception e){
            e.printStackTrace();
            restResponse.setStatus("400");
            restResponse.setMessage("An error occurred while importing client IDs");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }
    }
}
