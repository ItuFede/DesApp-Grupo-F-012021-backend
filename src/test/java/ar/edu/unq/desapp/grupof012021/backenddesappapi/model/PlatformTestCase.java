package ar.edu.unq.desapp.grupof012021.backenddesappapi.model;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Platform;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.PlatformType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlatformTestCase {
    @Test
    public void defaultConstructor_created_noExceptionThrown() {
        Assertions.assertNotNull(new Platform());
        Assertions.assertDoesNotThrow(() -> new Platform());
    }

    @Test
    public void givenConstructor_whenInvalidPlatformnameParam_throwsException() {
        Assertions.assertThrows(Exception.class, () -> new Platform("asdasdjkdha"));
    }

    @Test
    public void givenConstructor_whenPlatformnameParam_PlatformHasPlatformTypeAndName() throws Exception {
        Platform netflix = new Platform("Netflix");
        Platform amazon = new Platform("Amazon Prime");
        Platform disneyPlus = new Platform("Disney Plus");

        Assertions.assertEquals(netflix.getPlatformType(), PlatformType.NETFLIX);
        Assertions.assertEquals(amazon.getPlatformType(), PlatformType.AMAZON_PRIME);
        Assertions.assertEquals(disneyPlus.getPlatformType(), PlatformType.DISNEY_PLUS);

        Assertions.assertEquals(disneyPlus.getName(), "Disney Plus");
        Assertions.assertEquals(netflix.getName(), "Netflix");
        Assertions.assertEquals(amazon.getName(), "Amazon Prime");
    }

}
