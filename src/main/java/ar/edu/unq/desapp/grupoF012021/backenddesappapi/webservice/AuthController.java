package ar.edu.unq.desapp.grupoF012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.AccessToken;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.DomainUser;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.UserLoginCredentialsDto;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.UserRegisterCredentialsDto;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.service.AuthService;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.authentication.JwtTokenUtil;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("user")
    public Principal authenticatedUserCredentials(Principal user) {
        return user;
    }

    @PostMapping("register")
    public @ResponseBody
    ResponseEntity<Object> register(@Valid @RequestBody UserRegisterCredentialsDto userRegisterCredentialsDto) throws NotFoundException {
        DomainUser aUser = authService.register(userRegisterCredentialsDto);
        return ResponseEntity.ok(authService.generateToken(aUser));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginCredentialsDto userLoginCredentialsDto) throws Exception {
        authService.authenticate(userLoginCredentialsDto.getEmail(), userLoginCredentialsDto.getPassword());
        final DomainUser aUser = authService.findUserByEmail(userLoginCredentialsDto.getEmail());
        return ResponseEntity.ok(authService.generateToken(aUser));
    }
}