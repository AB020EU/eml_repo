package co.absa.eml.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
public class NucluesActionController {

    @Autowired
    NucluesActioner nucluesActioner;

    @GetMapping("/get/documents/by/app/{app}")
    public ResponseEntity<?> getPropertyTestDetailsById(@PathVariable String app) {

        return nucluesActioner.getDocumentCheckListIds(app);
    }
    @GetMapping("/get/documents/by/app/s2/{app}")
    public ResponseEntity<?> getPropertyTestDetailsByIdS2(@PathVariable String app) {

        return nucluesActioner.getDocumentCheckListIdsS2(app);
    }

    @PostMapping("/import/ids/from/nucleus")
    public ResponseEntity<?> importClientIdsFromNucleus() {

        return nucluesActioner.importExistingClientIds();
    }

}