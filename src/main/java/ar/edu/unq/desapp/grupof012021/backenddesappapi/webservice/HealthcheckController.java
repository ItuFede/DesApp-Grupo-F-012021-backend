package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class HealthcheckController {
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
