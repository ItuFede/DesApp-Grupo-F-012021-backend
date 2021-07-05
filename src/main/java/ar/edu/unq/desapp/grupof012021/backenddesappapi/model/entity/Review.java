package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @NotNull
    @Column(name = "shortText")
    @Setter
    @Getter
    private String shortText;

    @NotNull
    @Column(name = "longText")
    @Setter
    @Getter
    private String longText;

    @NotNull
    @Column(name = "date")
    @Getter
    private Date date;

    @NotNull
    @Column(name = "originalPlatform")
    @Setter
    @Getter
    private String originalPlatform;

    @NotNull
    @Column(name = "language")
    @Setter
    @Getter
    private String language; // EN_US, ES_AR

    @NotNull // true if generated by a critic user
    @Column(name = "isPremium")
    @Setter
    @Getter
    private boolean isPremium;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idMedia", referencedColumnName = "id")
    @Setter
    @Getter
    Media mediaReview;

    @OneToMany(mappedBy="review_reviewRanking")
    @Getter
    @Setter
    private final List<ReviewRanking> reviewRankings;

    @OneToMany(mappedBy="reviewReportMotive")
    @Getter
    @Setter
    private final List<ReportMotive> reportMotives;

    @Nullable
    @Column(name = "hasSpoilers")
    @Setter
    @Getter
    private boolean hasSpoilers;

    @Nullable
    @Column(name = "region")
    @Setter
    @Getter
    private String region;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_entity_id", referencedColumnName = "id")
    @Getter
    @Setter
    private UserEntity userEntity;

    public Review()
    {
        this.reviewRankings = new ArrayList<ReviewRanking>();
        this.reportMotives = new ArrayList<ReportMotive>();
    }
    
    @NotNull
    @Column(name = "score")
    @Getter
    private Double score;

    public Review(String shortText, String longText,
                  String originalPlatform, String language, boolean isCritic,
                  boolean hasSpoilers, String region, Double score, Media mediaReview, UserEntity userEntity) {
        this.shortText = shortText;
        this.longText = longText;
        this.date = new Date();
        this.originalPlatform = originalPlatform;
        this.language = language;
        this.isPremium = isCritic;
        this.hasSpoilers = hasSpoilers;
        this.region = region;
        this.reportMotives = new ArrayList<ReportMotive>();
        this.reviewRankings = new ArrayList<ReviewRanking>();
        this.score = score;
        this.mediaReview = mediaReview;
        this.userEntity = userEntity;
    }
}
