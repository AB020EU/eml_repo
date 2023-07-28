package co.absa.eml.environmentalvariables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import co.absa.eml.dto.RestResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class EnvironmentalVariablesBusinessLogic {

    @Autowired
    EnvironmentalVariablesRepositoryRepository environmentalVariablesRepositoryRepository;

    public ResponseEntity<?> captureEnvironmentalVariables(EnvironmentalVariables environmentalVariables) {
        RestResponse restResponse = new RestResponse();

        try {
            environmentalVariables.setManuallyCreated("Yes");
            environmentalVariables.setId(UUID.randomUUID().toString());
            environmentalVariablesRepositoryRepository.save(environmentalVariables);
            restResponse.setMessage("New environmental Variables Captured");
            restResponse.setStatus("200");
        } catch (Exception e) {
            
            restResponse.setMessage("Failed to capture environmental Variables");
            restResponse.setStatus("400");
        } finally {
            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }

    }

    public ResponseEntity<?> setupApplicationVariables(EnvironmentalVariables environmentalVariables) {
        RestResponse restResponse = new RestResponse();
        Optional<EnvironmentalVariables> env =environmentalVariablesRepositoryRepository.findById(environmentalVariables.getId());

        try {
            if(env.isPresent()){
                env.get().getId();
                env.get().getEnvironment();
                env.get().getNucleusUrl();
                env.get().getChrome();
                env.get().getGridUrl();
                env.get().setStatus(environmentalVariables.getStatus());
                environmentalVariablesRepositoryRepository.save(env.get());
                restResponse.setMessage("Environmental Variables set-up Successfully");
                restResponse.setStatus("200");

            }

        } catch (Exception e) {
            
            restResponse.setMessage("Failed to set environmental Variables");
            restResponse.setStatus("400");
        } finally {
            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }

    }

    public ResponseEntity<?> getApplicationVariablesById(String id){
        RestResponse restResponse = new RestResponse();
        Optional<EnvironmentalVariables> ById =environmentalVariablesRepositoryRepository.findById(id);

        try{
           if(ById.isPresent()){

                restResponse.setStatus("200");
                restResponse.setMessage("Environment variable  found");
               return ResponseEntity.status(HttpStatus.OK).body(ById);
           }else{
               restResponse.setStatus("201");
               restResponse.setMessage(" Environment id not found");
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
           }



        }catch (Exception e){
            
            restResponse.setStatus("400");
            restResponse.setMessage("No Pending Application");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }


    }
    public ResponseEntity<?> getApplicationVariablesAll(){
        RestResponse restResponse = new RestResponse();
        List<EnvironmentalVariables> All =environmentalVariablesRepositoryRepository.findAll();

        try{

                return ResponseEntity.status(HttpStatus.OK).body(All);



        }catch (Exception e){
            
            restResponse.setStatus("400");
            restResponse.setMessage("No something went wrong on environment variables");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }


    }

    public ResponseEntity<?> getEnvName(String id) {
        RestResponse restResponse = new RestResponse();
        try {
            Optional<EnvironmentalVariables> byId=environmentalVariablesRepositoryRepository.findById(id);


            restResponse.setStatus("200");
            restResponse.setMessage("Final offer reset successful");
            return ResponseEntity.status(HttpStatus.OK).body(byId.get().getEnvironment());
        } catch (Exception e) {
            
            restResponse.setStatus("400");
            restResponse.setMessage("An error occurred while resetting final offers");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restResponse);
        }
    }

}
