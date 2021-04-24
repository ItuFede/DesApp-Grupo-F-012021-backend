package ar.edu.unq.desapp.grupoF012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.authentication.JwtTokenUtil;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.AccessToken;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.DomainUser;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.UserRegisterCredentialsDto;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence.AuthRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public DomainUser register(UserRegisterCredentialsDto userRegisterCredentialsDto) throws NotFoundException {
        String encodedPassword = passwordEncoder.encode(userRegisterCredentialsDto.getPassword());
        DomainUser userToPersist = new DomainUser(
                userRegisterCredentialsDto.getEmail(),
                encodedPassword,
                userRegisterCredentialsDto.getName(),
                userRegisterCredentialsDto.getLastName()
        );
        authRepository.save(userToPersist);
        return userToPersist;
    }

    public void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public AccessToken generateToken(DomainUser aDomainUser) {
        String token = jwtTokenUtil.generateToken(aDomainUser);
        return new AccessToken(token, aDomainUser.getId());
    }

    public DomainUser findUserByEmail(String email) {
        return authRepository.findUserByEmail(email);
    }
}
