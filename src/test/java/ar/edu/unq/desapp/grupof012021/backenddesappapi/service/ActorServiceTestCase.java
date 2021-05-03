package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Actor;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ActorRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.ActorServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class ActorServiceTestCase {

    private static ActorService actorService;
    private static ActorRepository repositoryMock;

    @BeforeAll
    public static void setUp()
    {
        repositoryMock = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(repositoryMock);
    }

    @Test
    public void whenValidName_thenActorShouldBeFound() {
        String name = "Robert de niro";
        Actor anActor = new Actor(name);
        Mockito.when(repositoryMock.findByName(name)).thenReturn(anActor);
        Actor actorByName = actorService.getActorByName(name);
        Assertions.assertThat(actorByName.getName()).isEqualTo(name);
    }

    @Test
    public void whenInvalidName_thenActorShouldBeNull() {
        String name = "Leonardo DiCaprio";
        Mockito.when(repositoryMock.findByName(name)).thenReturn(null);
        Actor anActor = actorService.getActorByName(name);
        Assertions.assertThat(anActor).isEqualTo(null);
    }
}
