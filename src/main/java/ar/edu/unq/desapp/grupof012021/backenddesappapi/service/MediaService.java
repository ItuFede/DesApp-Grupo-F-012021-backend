package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaRedisDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewFilterDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MediaService {

    void addReviewTo(String idMediaString, ReviewDTO reviewDTO, String token);

    List<Review> findAllReviewsFrom(long idMedia);

    List<Review> findAllReviewsFilter(ReviewFilterDTO reviewFilterDTO, String idStringMedia, int offset, int limit);

    Media findById(long mediaId);

    List<Media> findAllMediaFilter(MediaDTO mediaDTO, int offset, int limit) throws Exception;

    MediaRedisDTO findMediaRedis(String idStringMedia);

    void subscribeForNotifications(String idMedia, String accessToken) throws ExecutionException, InterruptedException;

}
