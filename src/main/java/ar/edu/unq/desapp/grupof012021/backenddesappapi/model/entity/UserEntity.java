package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_entity")
@Data
public class UserEntity {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 10)
    private String authority;

    @Column(nullable = false)
    private Boolean isCriticUser;

    @Column(nullable = false)
    private String location;

    @OneToMany
    private List<ReportMotive> reportMotives;

    @OneToMany
    private List<ReportMotive> reviews;

    @OneToMany
    private List<ReviewRanking> reviewRankings;

    public UserEntity(UserCredentialsDTO dto) {
        username = dto.getUsername();
        password = dto.getPassword();
        isCriticUser = dto.getIsCriticUser();
        location = dto.getLocation();
        authority = "ROLE_USER";
    }

    public UserEntity() {}
}
