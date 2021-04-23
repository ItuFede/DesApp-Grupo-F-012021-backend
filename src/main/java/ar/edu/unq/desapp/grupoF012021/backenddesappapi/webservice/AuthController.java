package ar.edu.unq.desapp.grupoF012021.backenddesappapi.webservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    // Returns currently authenticated user
    @RequestMapping("user")
    public Principal user(Principal user) {
        return user;
    }
}