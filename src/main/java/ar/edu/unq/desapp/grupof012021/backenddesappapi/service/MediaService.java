package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaRedisDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MediaService {

    void addReviewTo(String id, Review aReview, String accessToken) throws ExecutionException, InterruptedException;

    List<Review> findAllReviewsFrom(long idMedia);

    List<Review> findAllReviewsFilter(ReviewDTO reviewDTO, String idStringMedia, int offset, int limit);

    Media findById(long mediaId);

    Media findByStringId(String mediaId);

    List<Media> findAllMediaFilter(MediaDTO mediaDTO, int offset, int limit) throws Exception;

    MediaRedisDTO findMediaRedis(String idStringMedia);

    void subscribeForNotifications(String idMedia, String accessToken) throws ExecutionException, InterruptedException;

}
