package ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.enumeration.MediaType;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.List;

@Entity
@Table(name = "media")
@Data
public class Media implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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

    @OneToMany(mappedBy="mediaReview")
    private List<Review> reviews;
}
