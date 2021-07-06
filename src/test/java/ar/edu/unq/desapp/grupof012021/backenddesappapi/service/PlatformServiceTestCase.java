package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Platform;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.PlatformRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.PlatformServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;

public class PlatformServiceTestCase {
    private static PlatformService platformService;
    private static PlatformRepository platformRepositoryMock;
    private static Collection<Platform> allPlatforms;

    @BeforeAll
    public static void setUp() {
        platformRepositoryMock = mock(PlatformRepository.class);
        platformService = new PlatformServiceImpl(platformRepositoryMock);
        Platform netflix = new Platform();
        Platform amazon = new Platform();
        allPlatforms = List.of(netflix, amazon);
    }

    @Test
    public void defaultConstructor_created_noExceptionThrown() {
        Assertions.assertNotNull(new PlatformServiceImpl(platformRepositoryMock));
        Assertions.assertDoesNotThrow(() -> new PlatformServiceImpl(platformRepositoryMock));
    }

    @Test
    public void givenPlatforms_whenFindAll_allPlatformsFound() {
        Mockito.when(platformRepositoryMock.findAll()).thenReturn(allPlatforms);
        Collection<Platform> platforms = platformService.findAll();
        Assertions.assertEquals(2, platforms.size());
    }

}
