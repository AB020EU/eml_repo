package co.absa.eml.clients;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
public class ClientController {

    @Autowired
    ClientIdsBusinessLogic clientIdsBusinessLogic;

    @GetMapping("/get/all/client/tests")
    public ResponseEntity<?> getAllClients() {

        return clientIdsBusinessLogic.getAllClientDetails();
    }

    @GetMapping("/get/client/test/by/id/{id}")
    public ResponseEntity<?> getClientTestDetailsById(@PathVariable String id) {

        return clientIdsBusinessLogic.getClientTestDetailsById(id);
    }

    @PostMapping("/create/client/test")
    public ResponseEntity<?> createEmploymentTestScenario(@RequestBody ClientIds clientIds) {

        return clientIdsBusinessLogic.createClientTestDetails(clientIds);
    }

    @GetMapping("get/client/types")
    public ResponseEntity<?> getClientTypes() {
        return clientIdsBusinessLogic.getClientTypes();
    }

    @PostMapping("import/client/ids")
    public ResponseEntity<?> importClientIds(){
        return clientIdsBusinessLogic.importClientIds();
    }

}
