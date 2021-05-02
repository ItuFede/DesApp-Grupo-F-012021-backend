package ar.edu.unq.desapp.grupoF012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.Actor;

public interface ActorService
{
    Actor getActorByName(String name);

    void createNewActor(String name);
}
