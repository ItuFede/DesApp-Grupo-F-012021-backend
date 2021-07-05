package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Platform;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/platform")
public class PlatformController {

    @Autowired
    private PlatformService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getPlatforms() {
        try {
            Collection<Platform> platforms = this.service.findAll();
            return ResponseEntity.ok(platforms);
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }
}
