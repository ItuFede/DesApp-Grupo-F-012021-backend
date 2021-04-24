package ar.edu.unq.desapp.grupoF012021.backenddesappapi.model;

import lombok.Data;

@Data
public class AccessToken {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String accessToken;
    private final Long userId;

    public AccessToken(String token, Long id) {
        this.accessToken = token;
        this.userId = id;
    }
}
