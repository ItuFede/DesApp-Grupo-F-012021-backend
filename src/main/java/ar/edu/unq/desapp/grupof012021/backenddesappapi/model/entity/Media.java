package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.io.Serializable;
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
    @Getter
    private String idStringMedia;

    @NotNull
    @Column(name = "primaryTitle")
    @Getter
    private String primaryTitle;

    @NotNull
    @Column(name = "originalTitle")
    @Getter
    private String originalTitle;

    @NotNull
    @Column(name = "year")
    @Getter
    private Integer year;

    @Nullable
    @Column(name = "endYear")
    @Getter
    private Integer endYear;

    @NotNull
    @Column(name = "runtimeMinutes")
    @Getter
    private Integer runtimeMinutes;

    @NotNull
    @Column(name = "mediaType")
    @Getter
    private MediaType mediaType;

    @OneToMany(mappedBy = "tvSerieMedia")
    @Getter
    private List<Episode> episodes;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Getter
    @Setter
    private List<Genre> genres;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Getter
    @Setter
    private List<Actor> actors;

    @OneToMany(mappedBy = "mediaReview", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<Review> reviews;

    public Media() {
    }

    public Media(String idStringMedia, String primaryTitle, String originalTitle, Integer year,
                 Integer endYear, Integer runtimeMinutes, MediaType mediaType,
                 List<Episode> episodes, List<Genre> genres, List<Actor> actors) {

        ArrayList<Episode> episodesList = new ArrayList<Episode>();
        if (episodes != null)
            episodesList.addAll(episodes);

        this.idStringMedia = idStringMedia;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.year = year;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.mediaType = mediaType;
        this.genres = genres;
        this.actors = actors;
        this.reviews = new ArrayList<>();
        this.episodes = episodesList;
    }
}
