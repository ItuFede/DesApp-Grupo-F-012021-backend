package ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Actor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActorRepository extends CrudRepository<Actor, Long> {

    Actor findByName(String name);

    List<Actor> findAll();
}
