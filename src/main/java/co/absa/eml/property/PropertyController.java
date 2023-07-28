package co.absa.eml.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {
    @Autowired
    PropertyBusinessLogic propertyBusinessLogic;

    @GetMapping("get/random/property/{propertyType}")
    public ResponseEntity<?> getProperty(@PathVariable String propertyType){
        return propertyBusinessLogic.getProperty(propertyType);
    }

    @GetMapping("get/development/code/{propertyType}")
    public ResponseEntity<?> getDevelopmentCode(@PathVariable String propertyType){
        return propertyBusinessLogic.getDevelopmentCode(propertyType);
    }
}
