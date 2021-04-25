package ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MediaGenre {

    @EmbeddedId
    private MediaGenreKey id;

    @ManyToOne
    @JoinColumn(name = "mediaId")
    private Media media;

    @ManyToOne
    @JoinColumn(name = "genreId")
    private Genre genre;
}