package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class MediaGenreKey implements Serializable {

    @Column(name = "mediaId")
    private Long mediaId;

    @Column(name = "genreId")
    private Long genreId;
}
