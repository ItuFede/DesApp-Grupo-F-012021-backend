package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import com.sun.istack.NotNull;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Entity
@Table(name = "reportMotive")
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
