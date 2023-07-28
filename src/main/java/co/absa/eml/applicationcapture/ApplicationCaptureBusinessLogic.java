package co.absa.eml.applicationcapture;

import co.absa.eml.clients.ClientIds;
import co.absa.eml.clients.ClientIdsRepository;
import co.absa.eml.clients.Connections;
import co.absa.eml.dto.CaptureApplication;
import co.absa.eml.dto.RestResponse;
import co.absa.eml.soupinteractionpoint.SOAPInteractionPoint;
import co.absa.eml.soupinteractionpoint.SoapEnvelope;
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
public class ApplicationCaptureBusinessLogic {

    @Autowired
    SOAPInteractionPoint soapInteractionPoint;
    @Autowired
    ApplicationCaptureRepository applicationCaptureRepository;



    public ResponseEntity<?> captureApplication(CaptureApplication captureApplication) {
        SoapEnvelope soapEnvelope= new SoapEnvelope();
        try {

            for(int i=0;i<captureApplication.getIterations();i++){

                soapInteractionPoint.send(captureApplication.getAbNumber(),"https://eomlsit.absa.africa/homeloans/services/HomeLoanFulfilmentSOAP",soapEnvelope.XML(captureApplication),"\'\'");
            }

            RestResponse restResponse = new RestResponse();

            restResponse.setMessage("Application captured successfully");
            restResponse.setStatus("200");

            return ResponseEntity.status(HttpStatus.OK).body(restResponse);

        }catch (Exception e){

            RestResponse restResponse = new RestResponse();

            restResponse.setMessage("Failed to Capture Application - " + e.getMessage());
            restResponse.setStatus("400");

            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }
    }
    public ResponseEntity<?> capturedApplication(ApplicationCapture applicationCapture) {

        try {
            applicationCapture.setId(applicationCapture.getApplicationNumber());
            applicationCapture.setApplicationNumber(applicationCapture.getApplicationNumber());
            applicationCapture.setAbNumber(applicationCapture.getAbNumber());
            applicationCaptureRepository.save(applicationCapture);

            RestResponse restResponse = new RestResponse();

            restResponse.setMessage("Application captured successfully");
            restResponse.setStatus("200");

            return ResponseEntity.status(HttpStatus.OK).body(restResponse);

        }catch (Exception e){

            RestResponse restResponse = new RestResponse();

            restResponse.setMessage("Failed to Capture Application - " + e.getMessage());
            restResponse.setStatus("400");

            return ResponseEntity.status(HttpStatus.OK).body(restResponse);
        }
    }

}
