package ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Platform;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;

public class UserDataHelper {

    public static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPlatform(new Platform());
        userEntity.setUsername("test_dataHelper@gmail.com");
        userEntity.setPassword("Test");
        userEntity.setAuthority("ROLE_USER");

        return userEntity;
    }

}
