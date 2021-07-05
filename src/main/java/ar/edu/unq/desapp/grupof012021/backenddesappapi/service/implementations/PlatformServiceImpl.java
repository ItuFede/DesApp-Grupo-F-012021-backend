package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Platform;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.PlatformRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("PlatformService")
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private PlatformRepository repository;

    public Collection<Platform> findAll() {
        return this.repository.findAll();
    }
}
