package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;

import java.util.List;

public interface MediaService {

    void addReviewTo(long id, Review aReview);

    List<Review> findAllReviewsFrom(long idMedia);

    Media findById(long mediaId);
}
