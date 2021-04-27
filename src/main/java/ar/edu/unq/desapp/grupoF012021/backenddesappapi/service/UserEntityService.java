package ar.edu.unq.desapp.grupoF012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.dto.UserCredentialsDto;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService {
    @Autowired
    UserEntityRepository repository;

    public Optional<UserEntity> findByUsername(String username) {
        return repository.findUserEntityByUsername(username);
    }

    public Optional<UserEntity> saveUser(UserCredentialsDto userRegisterCredentialsDto) {
        UserEntity user = new UserEntity(userRegisterCredentialsDto);
        repository.save(user);
        return this.findByUsername(user.getUsername());
    }
}
