package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
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
    @Getter
    private long id;

    @NotNull
    @Column(name = "primaryTitle", unique = true)
    @Getter
    private MediaGenreType genreName;

    @ManyToMany
    private List<Media> mediaList;

    public Genre() {
    }

    public Genre(MediaGenreType genreName) {
        this.genreName = genreName;
    }

    public static MediaGenreType getMediaGenreTypeFromString(String nameGenre) throws Exception {
        switch (nameGenre.toLowerCase()) {
            case "accion": {
                return MediaGenreType.ACTION;
            }
            case "drama": {
                return MediaGenreType.DRAMA;
            }
            case "adventure": {
                return MediaGenreType.ADVENTURE;
            }
            case "animation": {
                return MediaGenreType.ANIMATION;
            }
            case "crime": {
                return MediaGenreType.CRIME;
            }
            case "mistery": {
                return MediaGenreType.MISTERY;
            }
            case "family": {
                return MediaGenreType.FAMILY;
            }
            case "scifi": {
                return MediaGenreType.SCIFI;
            }
            default: {
                throw new Exception("Invalid MediaGenreType name.");
            }
        }
    }
}
