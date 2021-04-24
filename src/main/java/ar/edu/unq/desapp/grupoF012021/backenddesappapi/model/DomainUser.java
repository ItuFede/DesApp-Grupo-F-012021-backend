package ar.edu.unq.desapp.grupoF012021.backenddesappapi.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "domain_user")
@Data
public class DomainUser {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "password")
    private String password;

    public DomainUser() {
        super();
    }

    public DomainUser(String email, String encodedPassword, String name, String lastName, Long id) {
        this.id = id;
    }

    public DomainUser(String email, String encodedPassword, String name, String lastName) {
    }
}

