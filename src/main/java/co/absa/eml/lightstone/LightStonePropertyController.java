package co.absa.eml.lightstone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
public class LightStonePropertyController {

    @Autowired
    LightStoneBusinessLogic lightStoneBusinessLogic;

    @PostMapping("/import/properties")
    public ResponseEntity<?> importLightStoneProperties(@RequestBody ArrayList<LightStonePropertiesEntity> properties) {

        return lightStoneBusinessLogic.importPropertiesFromLightStone(properties);
    }

    @PostMapping("/extract/ids")
    public ResponseEntity<?> extractIds() {

        return lightStoneBusinessLogic.extractTestIds();
    }

    @PostMapping("/extract/properties")
    public ResponseEntity<?> extractProperties() {

        return lightStoneBusinessLogic.extractProperties();
    }

    @GetMapping("/get/properties/by/id/{id}")
    public ResponseEntity<?> findPropertyById(@PathVariable String id) {
        return lightStoneBusinessLogic.findPropertyById(id);
    }

    @PostMapping("update/property/types")
    public ResponseEntity<?> updatePropertyTypes() {
        return lightStoneBusinessLogic.updatePropertyTypes();
    }
    @GetMapping("test")
    public ResponseEntity<?> importLightStoneProperties() {

        return lightStoneBusinessLogic.test();
    }
}
