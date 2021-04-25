package ar.edu.unq.desapp.grupoF012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService {
    @Autowired
    UserEntityRepository userEntityRepository;

    public Optional<UserEntity> findByUsername(String username) {
        return userEntityRepository.findUserEntityByUsername(username);
    }
}
