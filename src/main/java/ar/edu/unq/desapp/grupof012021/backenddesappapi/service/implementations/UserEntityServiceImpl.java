package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Platform;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.PlatformRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.UserEntityRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userEntityService")
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserEntityRepository repository;

    @Autowired
    private PlatformRepository platformRepository;

    public Optional<UserEntity> findByUsername(String username) {
        return repository.findUserEntityByUsername(username);
    }

    public Optional<UserEntity> saveUser(UserCredentialsDTO userRegisterCredentialsDto) throws Exception {
        Platform platform = platformRepository.findByPlatformType(new Platform(userRegisterCredentialsDto.getPlatform()).getPlatformType());
        UserEntity user = new UserEntity(userRegisterCredentialsDto, platform);
        user.setPassword(userRegisterCredentialsDto.getPassword());
        repository.save(user);
        return this.findByUsername(user.getUsername());
    }
}
