package ar.edu.unq.desapp.grupof012021.backenddesappapi.model;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Platform;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UserEntityTestCase {
    @Test
    public void defaultConstructor_created_defaultValues() throws Exception {
        UserCredentialsDTO userCreds = new UserCredentialsDTO();
        userCreds.username = "username";
        userCreds.password = "123";
        Platform platform = new Platform("Netflix");
        UserEntity user = new UserEntity(userCreds, platform);
        Assert.assertEquals(user.getUsername(), userCreds.username);
        Assert.assertEquals(user.getPassword(), userCreds.password);
        Assert.assertEquals(user.getAuthority(), "ROLE_USER");
        Assert.assertEquals(user.getPlatform(), "Netflix");
    }
}