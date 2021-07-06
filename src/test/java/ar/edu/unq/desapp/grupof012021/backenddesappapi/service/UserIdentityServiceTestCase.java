package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.UserDataHelper;
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
        userIdentityService = new UserIdentityServiceImpl(userEntityServiceMock);
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
}
