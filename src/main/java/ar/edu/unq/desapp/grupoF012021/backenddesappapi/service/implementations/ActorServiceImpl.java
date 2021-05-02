package ar.edu.unq.desapp.grupoF012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.Actor;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence.ActorRepository;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("actorService")
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public ActorServiceImpl() { }

    public ActorServiceImpl(ActorRepository repositoryMock) {
        this.actorRepository = repositoryMock;
    }

    @Override
    public Actor getActorByName(String name) {
        return actorRepository.findByName(name);
    }

    @Override
    public void saveActor(Actor anActor) {
        actorRepository.save(anActor);
    }

}
