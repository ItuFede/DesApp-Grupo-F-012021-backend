package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Platform;

import java.util.Collection;

public interface PlatformService {

    Collection<Platform> findAll();
}
