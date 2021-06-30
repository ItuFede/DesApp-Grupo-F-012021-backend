package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserIdentity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserIdentityService {

    UserIdentity register(UserCredentialsDTO userRegisterCredentialsDto) throws Exception;

    UserDetails loadUserByUsername(String username);
}
