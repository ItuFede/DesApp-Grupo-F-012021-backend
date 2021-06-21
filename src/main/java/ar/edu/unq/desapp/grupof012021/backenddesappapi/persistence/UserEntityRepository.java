package ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByUsername(String username);
    UserEntity findById(long idUserEntity);
}
