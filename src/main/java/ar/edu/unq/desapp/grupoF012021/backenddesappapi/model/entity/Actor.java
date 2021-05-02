package ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.enumeration.MediaGenreType;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "actor")
@Data
public class Actor implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    public Actor(String name)
    {
        this.name = name;
    }
}