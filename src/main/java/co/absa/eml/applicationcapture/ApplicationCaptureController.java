package co.absa.eml.applicationcapture;


import co.absa.eml.clients.ClientIds;
import co.absa.eml.clients.ClientIdsBusinessLogic;
import co.absa.eml.dto.CaptureApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
public class ApplicationCaptureController {

    @Autowired
    ApplicationCaptureBusinessLogic applicationCaptureBusinessLogic;

//    @GetMapping("/get/all/client/tests")
//    public ResponseEntity<?> getAllClients() {
//
//        return clientIdsBusinessLogic.getAllClientDetails();
//    }

//    @PostMapping("/create/client/test")
//    public ResponseEntity<?> createEmploymentTestScenario(@RequestBody ClientIds clientIds) {
//
//        return clientIdsBusinessLogic.createClientTestDetails(clientIds);
//    }
    @PostMapping("/capture/application")
    public ResponseEntity<?> captureApplication(@RequestBody CaptureApplication captureApplication) {

        return applicationCaptureBusinessLogic.captureApplication(captureApplication);
    }
    @PostMapping("/captured/application")
    public ResponseEntity<?> capturedApplication(@RequestBody ApplicationCapture applicationCapture) {

        return applicationCaptureBusinessLogic.capturedApplication(applicationCapture);
    }

}
