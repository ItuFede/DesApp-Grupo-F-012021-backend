package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserIdentity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.UserEntityService;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.UserIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userIdentityService")
public class UserIdentityServiceImpl implements UserIdentityService, UserDetailsService {
    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userEntityService.findByUsername(username);
        if(user.isPresent()) {
            return new UserIdentity(user.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public UserIdentity register(UserCredentialsDTO userRegisterCredentialsDto) {
        try {
            userRegisterCredentialsDto.setPassword(passwordEncoder.encode(userRegisterCredentialsDto.getPassword()));
            userEntityService.saveUser(userRegisterCredentialsDto);
            return (UserIdentity) this.loadUserByUsername(userRegisterCredentialsDto.getUsername());
        } catch (Exception ex) {
            throw new RuntimeException("Something went wrong, couldn't create user " + userRegisterCredentialsDto.getUsername());
        }
    }
}
