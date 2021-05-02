package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDto;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserIdentityService implements UserDetailsService {

    @Autowired
    private UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userEntityService.findByUsername(username);
        if(user.isPresent()) {
            return new UserIdentity(user.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public UserIdentity register(UserCredentialsDto userRegisterCredentialsDto) {
        try {
            userEntityService.saveUser(userRegisterCredentialsDto);
            return (UserIdentity) this.loadUserByUsername(userRegisterCredentialsDto.getUsername());
        } catch (Exception ex) {
            throw new RuntimeException("Something went wrong, couldn't create user " + userRegisterCredentialsDto.getUsername());
        }
    }
}
