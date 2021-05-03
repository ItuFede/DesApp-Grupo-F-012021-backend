package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;

public interface MediaService {
    void addReviewTo(long id, Review aReview);
}
