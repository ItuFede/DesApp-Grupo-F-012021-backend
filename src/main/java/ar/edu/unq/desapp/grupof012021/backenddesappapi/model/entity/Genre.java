package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "primaryTitle")
    private MediaGenreType genreName;

    @ManyToMany
    private List<Media> mediaList;

    public Genre(MediaGenreType genreName) {
        this.genreName = genreName;
    }
}
