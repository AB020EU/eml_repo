package co.absa.eml.environmentalvariables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class EnvironmentalVariablesController {

    @Autowired
    EnvironmentalVariablesBusinessLogic environmentalVariablesBusinessLogic;

    @PostMapping("capture/environmental/variables")
    public ResponseEntity<?> environmentalVariables(@RequestBody EnvironmentalVariables environmentalVariables) {
        return environmentalVariablesBusinessLogic.captureEnvironmentalVariables(environmentalVariables);
    }
    @PostMapping("setup/application/variables")
    public ResponseEntity<?> setupApplicationVariables(@RequestBody EnvironmentalVariables environmentalVariables) {
        return environmentalVariablesBusinessLogic.setupApplicationVariables(environmentalVariables);
    }
    @GetMapping("get/application/variables/byid/{id}")
    public ResponseEntity<?> getApplicationVariablesById(@PathVariable String id) {
        return environmentalVariablesBusinessLogic.getApplicationVariablesById(id);
    }
    @GetMapping("get/application/variables/all")
    public ResponseEntity<?> getApplicationVariablesAll() {
        return environmentalVariablesBusinessLogic.getApplicationVariablesAll();
    }

    @GetMapping("get/env/name/byName/{id}")
    public ResponseEntity<?> getEnvName(@PathVariable String id) {
        return environmentalVariablesBusinessLogic.getEnvName(id);
    }


}
