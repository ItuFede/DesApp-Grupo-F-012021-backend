package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserIdentity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.security.JwtTokenUtil;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.UserIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/")
@ComponentScan
public class UserIdentityController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserIdentityService userIdentityService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ResponseEntity<Object> authenticatedUser(@RequestHeader(value="Authorization") String token) {
        try {
            return ResponseEntity.ok(
                    jwtTokenUtil.getUsernameFromToken(jwtTokenUtil.getTokenFromHeader(token))
            );
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Object> register(@Valid @RequestBody UserCredentialsDTO userRegisterCredentialsDto) {
        try {
            userIdentityService.register(userRegisterCredentialsDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("");
        }
        catch (Exception err) {
            if (err.getMessage() == "Invalid platform name.")
                return ResponseEntity.status(402).body("Invalid platform name.");
            else
                return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody @Valid UserCredentialsDTO userLoginCredentialsDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginCredentialsDto.getUsername(), userLoginCredentialsDto.getPassword()));
            final UserIdentity authenticatedUser = (UserIdentity) userIdentityService.loadUserByUsername(userLoginCredentialsDto.getUsername());
            return ResponseEntity.ok().body(jwtTokenUtil.generateToken(authenticatedUser));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        } catch (Exception err) {
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }
}