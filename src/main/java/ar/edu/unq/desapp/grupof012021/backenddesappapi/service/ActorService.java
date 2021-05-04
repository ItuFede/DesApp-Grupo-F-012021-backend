package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Actor;

public interface ActorService
{
    Actor getActorByName(String name);
}
