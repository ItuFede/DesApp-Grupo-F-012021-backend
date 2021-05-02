package ar.edu.unq.desapp.grupoF012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.Actor;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence.ActorRepository;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.service.implementations.ActorServiceImpl;
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

        actorService.saveActor(anActor);

        Mockito.when(repositoryMock.findByName(name)).thenReturn(anActor);

        Actor found = actorService.getActorByName(name);

        Assertions.assertThat(found.getName()).isEqualTo(name);
    }

    @Test
    public void anActorNoExist_saveActor_anActorExist() throws Exception {
        Actor anActor = new Actor("Robert de niro");

        actorService.saveActor(anActor);

        Mockito.verify(repositoryMock, times(1)).save(anActor);
    }

}
