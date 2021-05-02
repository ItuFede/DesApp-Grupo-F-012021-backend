package ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "episode")
@Data
public class Episode implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "mediaId", referencedColumnName = "id")
    private Media tvSerieMedia;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "seasonNumber")
    private Integer seasonNumber;

    @Nullable
    @Column(name = "episodeNumber")
    private Integer episodeNumber;
}
