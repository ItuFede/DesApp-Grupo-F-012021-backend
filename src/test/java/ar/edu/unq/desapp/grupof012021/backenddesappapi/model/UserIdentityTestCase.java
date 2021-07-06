package ar.edu.unq.desapp.grupof012021.backenddesappapi.model;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.UserDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserIdentity;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserIdentityTestCase {
    @Test
    public void defaultConstructor_created_defaultValues() {
        UserEntity userEntity = UserDataHelper.getUserEntity();
        UserIdentity user = new UserIdentity(userEntity);
        Assertions.assertTrue(user.isAccountNonExpired());
        Assertions.assertTrue(user.isAccountNonLocked());
        Assertions.assertTrue(user.isCredentialsNonExpired());
        Assertions.assertTrue(user.isEnabled());
        Assertions.assertTrue(user.getAuthorities().size() > 0);
        Assert.assertEquals(user.getUsername(), userEntity.getUsername());
        Assert.assertEquals(user.getPassword(), userEntity.getPassword());
    }
}
