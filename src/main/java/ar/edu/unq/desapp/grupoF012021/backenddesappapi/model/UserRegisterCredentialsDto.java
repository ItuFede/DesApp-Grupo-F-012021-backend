package ar.edu.unq.desapp.grupoF012021.backenddesappapi.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRegisterCredentialsDto extends UserLoginCredentialsDto {
    @NotEmpty
    String name;
    @NotEmpty
    String lastName;
}
