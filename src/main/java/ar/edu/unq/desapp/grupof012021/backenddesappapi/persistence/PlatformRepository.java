package ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Platform;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.PlatformType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PlatformRepository extends CrudRepository<Platform, Long> {

    Collection<Platform> findAll();

    Platform findByPlatformType(PlatformType platformType);
}
