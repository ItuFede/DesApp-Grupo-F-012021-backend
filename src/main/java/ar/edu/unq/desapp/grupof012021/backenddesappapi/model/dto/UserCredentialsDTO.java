package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto;

import lombok.Data;

@Data
public class UserCredentialsDTO {
    public String username;
    public String password;
    public String platform;
}
