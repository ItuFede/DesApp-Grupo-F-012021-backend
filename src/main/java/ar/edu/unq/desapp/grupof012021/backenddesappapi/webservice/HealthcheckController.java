package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.FirebaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthcheckController {
    @Autowired
    FirebaseServiceImpl firebaseService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> health() {
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/error")
    public ResponseEntity<?> error() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/authenticated")
    public ResponseEntity<?> authenticated() {
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/authenticated/error")
    public ResponseEntity<?> authenticatedError() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
