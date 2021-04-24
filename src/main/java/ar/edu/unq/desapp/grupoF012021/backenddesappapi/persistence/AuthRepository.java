package ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.DomainUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CrudRepository<DomainUser, Long> {

    DomainUser findUserByEmail(String email);
}
