package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "reportMotive")
public class ReportMotive implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @NotNull
    @Column(name = "motiveText")
    @Getter
    private String motiveText;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review reviewReportMotive;

    @ManyToOne
    @JoinColumn(name = "user_entity_id", referencedColumnName = "id")
    @Getter
    private UserEntity userEntity;

    public ReportMotive(){}

    public ReportMotive(String motiveText, Review reviewReportMotive, UserEntity userEntity) {
        this.motiveText = motiveText;
        this.reviewReportMotive = reviewReportMotive;
        this.userEntity = userEntity;
    }
}
