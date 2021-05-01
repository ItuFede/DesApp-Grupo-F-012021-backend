package ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reportMotive")
@Data
public class ReportMotive implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "motiveText")
    private String motiveText;

    @ManyToOne
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review reviewReportMotive;
}
