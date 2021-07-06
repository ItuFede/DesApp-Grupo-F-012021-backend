package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class MediaGenre {

    @EmbeddedId
    private MediaGenreKey id;

    @ManyToOne
    @JoinColumn(name = "mediaId", insertable = false, updatable = false)
    private Media media;

    @ManyToOne
    @JoinColumn(name = "genreId", insertable = false, updatable = false)
    private Genre genre;
}