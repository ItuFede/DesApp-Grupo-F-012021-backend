package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
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
    private String shortText;

    @NotNull
    @Column(name = "longText")
    @Setter
    private String longText;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotNull
    @Column(name = "originalPlatform")
    @Setter
    private String originalPlatform;

    @NotNull
    @Column(name = "language")
    @Setter
    private String language;

    @NotNull
    @Column(name = "isCritic")
    @Setter
    private boolean isCritic;

    @ManyToOne
    @JoinColumn(name = "idMedia", referencedColumnName = "id")
    @Setter
    @Getter
    private Media mediaReview;

    @OneToMany(mappedBy="review_reviewRanking")
    @Getter
    private final List<ReviewRanking> reviewRankings;

    @OneToMany(mappedBy="reviewReportMotive")
    private final List<ReportMotive> reportMotives;

    @Nullable
    @Column(name = "hasSpoilers")
    @Setter
    private boolean hasSpoilers;

    @Nullable
    @Column(name = "region")
    @Setter
    private String region; // EN_US, ES_AR

    public Review()
    {
        this.reviewRankings = new ArrayList<ReviewRanking>();
        this.reportMotives = new ArrayList<ReportMotive>();
    }
    
    @NotNull
    @Column(name = "score")
    private Double score;

    public Review(String shortText, String longText,
                  String originalPlatform, String language, boolean isCritic,
                  boolean hasSpoilers, String region, Double score) {
        this.shortText = shortText;
        this.longText = longText;
        this.date = new Date();
        this.originalPlatform = originalPlatform;
        this.language = language;
        this.isCritic = isCritic;
        this.hasSpoilers = hasSpoilers;
        this.region = region;
        this.reportMotives = new ArrayList<ReportMotive>();
        this.reviewRankings = new ArrayList<ReviewRanking>();
        this.score = score;
    }
}
