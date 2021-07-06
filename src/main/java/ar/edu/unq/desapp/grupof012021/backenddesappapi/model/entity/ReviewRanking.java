package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "reviewRanking")
public class ReviewRanking implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "isPositiveVote")
    @Getter
    private boolean isPositiveVote;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idReview", referencedColumnName = "id")
    @JsonIgnore
    @Getter
    private Review review_reviewRanking;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_entity_id", referencedColumnName = "id")
    @Getter
    private UserEntity userEntity;

    public ReviewRanking() {
    }

    public ReviewRanking(boolean isPositiveVote,Review review, UserEntity userEntity) {
        this.isPositiveVote = isPositiveVote;
        this.review_reviewRanking = review;
        this.userEntity = userEntity;
    }
}
