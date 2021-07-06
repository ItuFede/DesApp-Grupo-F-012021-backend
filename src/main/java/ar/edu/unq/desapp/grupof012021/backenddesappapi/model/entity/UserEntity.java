package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "user_entity")
public class UserEntity {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 50, unique = true)
    @Getter
    @Setter
    private String username;

    @NotNull
    @Column(nullable = false)
    @JsonIgnore
    @Getter
    @Setter
    private String password;

    @Column(nullable = false, length = 10)
    @JsonIgnore
    @Getter
    @Setter
    private String authority;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "platformTypeId", referencedColumnName = "id")
    @Setter
    private Platform platform;

    @JsonIgnore
    @OneToMany
    private List<ReportMotive> reportMotives;

    @JsonIgnore
    @OneToMany
    private List<ReportMotive> reviews;

    @JsonIgnore
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
