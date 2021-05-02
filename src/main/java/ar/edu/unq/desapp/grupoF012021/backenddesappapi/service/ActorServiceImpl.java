package ar.edu.unq.desapp.grupoF012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.Actor;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("actorService")
public class ActorServiceImpl implements ActorService{

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Actor getActorByName(String name) {
        return actorRepository.findByName(name);
    }

    @Override
    public void createNewActor(String name) {
        actorRepository.save(new Actor(name));
    }
}
