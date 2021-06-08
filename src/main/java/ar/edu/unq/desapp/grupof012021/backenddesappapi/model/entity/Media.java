package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "media")
public class Media implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @NotNull
    @Column(unique = true, name = "idStringMedia")
    private String idStringMedia;

    @NotNull
    @Column(name = "primaryTitle")
    private String primaryTitle;

    @NotNull
    @Column(name = "originalTitle")
    private String originalTitle;

    @NotNull
    @Column(name = "year")
    private Year year;

    @Nullable
    @Column(name = "endYear")
    private Year endYear;

    @NotNull
    @Column(name = "runtimeMinutes")
    private Integer runtimeMinutes;

    @NotNull
    @Column(name = "mediaType")
    private MediaType mediaType;

    @OneToMany(mappedBy="tvSerieMedia")
    private List<Episode> episodes;

    @ManyToMany
    private List<Genre> genres;

    @OneToMany(mappedBy="mediaReview", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<Review> reviews;

    public Media(){}

    public Media(String idStringMedia, String primaryTitle, String originalTitle, Year year,
                 Year endYear, Integer runtimeMinutes, MediaType mediaType,
                 List<Episode> episodes, List<Genre> genres) {
        this.idStringMedia = idStringMedia;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.year = year;
        this.endYear = year;
        this.runtimeMinutes = runtimeMinutes;
        this.mediaType = mediaType;
        this.genres = genres;
        this.reviews = new ArrayList<>();
    }
}
