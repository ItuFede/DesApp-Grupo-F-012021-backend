package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserEntityService {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> saveUser(UserCredentialsDTO userRegisterCredentialsDto) throws Exception;
}
