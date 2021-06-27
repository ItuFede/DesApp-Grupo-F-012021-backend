package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.PlatformType;
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

    @OneToOne
    @JoinColumn(name = "platformTypeId", referencedColumnName = "id")
    private Platform platform;

    @OneToMany
    private List<ReportMotive> reportMotives;

    @OneToMany
    private List<ReportMotive> reviews;

    @OneToMany
    private List<ReviewRanking> reviewRankings;

    public UserEntity(UserCredentialsDTO dto, Platform platformData) throws Exception {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.platform = platformData;
        this.authority = "ROLE_USER";
    }

    public UserEntity() {}

    public String getPlatform() {
        return this.platform.getPlatformType().getPlatformString();
    }
}
