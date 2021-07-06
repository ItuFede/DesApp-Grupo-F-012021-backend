package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

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
@Table(name = "actor")
public class Actor implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true, name = "idStringActor")
    @Getter
    private String idStringActor;

    @NotNull
    @Column(name = "name")
    @Getter
    private String name;

    @ManyToMany
    private List<Media> mediaList;

    public Actor(String idStringActor, String name) {
        this.idStringActor = idStringActor;
        this.name = name;
    }

    public Actor() {
    }
}