package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDto;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserIdentity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.security.JwtTokenUtil;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.UserIdentityService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/auth/")
@ComponentScan
public class UserIdentityController {
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserIdentityService userIdentityService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserIdentityController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("user")
    public Principal authenticatedUserCredentials(Principal user) {
        return user;
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @PostMapping("register")
    public @ResponseBody
    ResponseEntity<Object> register(@Valid @RequestBody UserCredentialsDto userRegisterCredentialsDto) throws NotFoundException {
        UserIdentity user = userIdentityService.register(userRegisterCredentialsDto);
        return ResponseEntity.ok(jwtTokenUtil.generateToken(user));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid UserCredentialsDto userLoginCredentialsDto) throws Exception {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginCredentialsDto.getUsername(), userLoginCredentialsDto.getPassword()));
            final UserIdentity authenticatedUser = (UserIdentity) userIdentityService.loadUserByUsername(userLoginCredentialsDto.getUsername());
            return ResponseEntity.ok().header(
                    HttpHeaders.AUTHORIZATION,
                    jwtTokenUtil.generateToken(authenticatedUser)
            ).body(authenticatedUser);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
    }
}