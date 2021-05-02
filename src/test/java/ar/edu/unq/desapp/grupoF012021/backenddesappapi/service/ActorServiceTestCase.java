package ar.edu.unq.desapp.grupoF012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity.Actor;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence.ActorRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class ActorServiceTestCase {

    @TestConfiguration
    static class ActorServiceTestCaseContextConfiguration {

        @Bean
        public ActorService actorService() {
            return new ActorServiceImpl();
        }
    }

    @Autowired
    private ActorService actorService;

    @MockBean
    private ActorRepository actorRepository;

    @Before
    public void setUp() {
        Actor actorRobertDeNiro = new Actor("Robert de niro");

        Mockito.when(actorRepository.findByName(actorRobertDeNiro.getName()))
                .thenReturn(actorRobertDeNiro);
    }

    /*
    @Test
    public void whenValidName_thenActorShouldBeFound() {
        String name = "Robert de niro";
        Actor found = actorService.getActorByName(name);

        Assertions.assertThat(found.getName())
                .isEqualTo(name);
    }
     */

}
