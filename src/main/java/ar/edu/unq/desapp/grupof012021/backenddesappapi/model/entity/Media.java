package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
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

    @OneToMany(mappedBy="tvSerieMedia")
    @Getter
    private List<Episode> episodes;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @Getter
    @Setter
    private List<Genre> genres;

    @OneToMany(mappedBy="mediaReview", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<Review> reviews;

    public Media(){}

    public Media(String idStringMedia, String primaryTitle, String originalTitle, Integer year,
                 Integer endYear, Integer runtimeMinutes, MediaType mediaType,
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
