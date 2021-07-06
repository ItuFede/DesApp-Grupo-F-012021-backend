package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.UserDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserIdentity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.UserIdentityServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.mock;

public class UserIdentityServiceTestCase {

    private static UserEntityService userEntityServiceMock;
    private static PasswordEncoder passwordEncoderMock;
    private static UserIdentityService userIdentityService;
    private static UserEntity userEntity1;

    @BeforeAll
    public static void setUp() {
        userEntityServiceMock = mock(UserEntityService.class);
        passwordEncoderMock = mock(PasswordEncoder.class);
        userIdentityService = new UserIdentityServiceImpl(userEntityServiceMock, passwordEncoderMock);
        userEntity1 = UserDataHelper.getUserEntity();
    }

    @Test
    public void givenUsername_whenLoadUserByUsername_getUserIdentity() {
        Mockito.when(userEntityServiceMock.findByUsername("test_dataHelper@gmail.com")).thenReturn(Optional.ofNullable(userEntity1));
        UserIdentity userIdentity = (UserIdentity) userIdentityService.loadUserByUsername("test_dataHelper@gmail.com");
        Assert.assertEquals(userIdentity.getAssociatedUserEntity(), userEntity1);
    }

    @Test
    public void givenUsername_whenLoadUserByUsernameWithUnknownUsername_throwsException() {
        Mockito.when(userEntityServiceMock.findByUsername("test_dataHelper@gmail.com")).thenReturn(Optional.ofNullable(null));
        Assert.assertThrows(UsernameNotFoundException.class, () -> userIdentityService.loadUserByUsername("test_dataHelper@gmail.com"));
    }

    @Test
    public void givenUserCredentials_whenRegister_userPersistedThroughEntityService() throws Exception {
        Mockito.when(passwordEncoderMock.encode("123")).thenReturn("$2a$10$T3d/kyy8JcF605uF5WAU/ueZMAufCM2AXKKDL6BfjJtcvnw8D5J8q");

        UserCredentialsDTO userCredentials = new UserCredentialsDTO();
        userCredentials.password = "123";
        userCredentials.username = "username";
        userCredentials.platform = "netflix";

        UserEntity userEntity2 = UserDataHelper.getUserEntity();
        userEntity2.setPassword(userCredentials.password);
        userEntity2.setUsername(userCredentials.username);
        Mockito.when(userEntityServiceMock.findByUsername("username")).thenReturn(Optional.ofNullable(userEntity2));
        Mockito.when(userEntityServiceMock.saveUser(userCredentials)).thenReturn(Optional.ofNullable(userEntity2));

        UserIdentity registeredUser = userIdentityService.register(userCredentials);
        Assert.assertEquals(registeredUser.getAssociatedUserEntity().getUsername(), "username");
    }
}
