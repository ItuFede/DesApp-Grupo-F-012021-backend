package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MediaGenreKey implements Serializable {

    @Column(name = "mediaId")
    private Long mediaId;

    @Column(name = "genreId")
    private Long genreId;
}
