package ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "review")
@Data
public class Review implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "shortText")
    private String shortText;

    @NotNull
    @Column(name = "longText")
    private String longText;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotNull
    @Column(name = "originalPlatform")
    private String originalPlatform;

    @NotNull
    @Column(name = "language")
    private String language;

    @NotNull
    @Column(name = "isCritic")
    private boolean isCritic;

    @ManyToOne
    @JoinColumn(name = "idMedia", referencedColumnName = "id")
    private Media media;

    @OneToMany(mappedBy="reviewRanking")
    private List<Review> rankings;

    @OneToMany(mappedBy="reportMotive")
    private List<ReportMotive> reports;

    @Nullable
    @Column(name = "hasSpoilers")
    private boolean hasSpoilers;

    @Nullable
    @Column(name = "region")
    private String region; // EN_US, ES_AR
}
