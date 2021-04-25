package ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> { }
