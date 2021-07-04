package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaRedisDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.MediaService;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Media>> getMediaFilter(@Valid @RequestBody MediaDTO mediaDTO, @RequestParam(value = "offset") int offset, @RequestParam(value = "limit") int limit) throws Exception {
        List<Media> medias = mediaService.findAllMediaFilter(mediaDTO, offset, limit);
        return new ResponseEntity<>(medias, HttpStatus.OK);
    }

    @RequestMapping(value = "{idStringMedia}/review", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getReviewsFromMedia(@Valid @RequestBody ReviewDTO reviewDTO, @PathVariable String idStringMedia, @RequestParam(value = "offset", defaultValue = "1") @Min(1) int offset, @RequestParam(value = "limit", defaultValue = "1") @Min(0) int limit) {
            List<Review> reviews = mediaService.findAllReviewsFilter(reviewDTO, idStringMedia, offset, limit);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @RequestMapping(value = "{idMedia}/review", method = RequestMethod.POST)
    public ResponseEntity<?> addReviewsToMedia(@PathVariable long idMedia, @RequestBody ReviewDTO reviewDTO) {
        try {
            Review addReview = reviewService.createTemporalReview(reviewDTO, mediaService.findById(idMedia));
            mediaService.addReviewTo(idMedia, addReview);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
        }
    }

    @RequestMapping(value = "{idStringMedia}", method = RequestMethod.GET)
    public ResponseEntity<MediaRedisDTO> getMediaRedis(@PathVariable String idStringMedia) {
        MediaRedisDTO media = mediaService.findMediaRedis(idStringMedia);
        return new ResponseEntity<>(media, HttpStatus.OK);
    }

    @RequestMapping(value = "{idMedia}/subscribe", method = RequestMethod.POST)
    public ResponseEntity<?> subscribeForNotifications(@PathVariable long idMedia, @RequestHeader(value="Authorization") String token) {
        try {
            mediaService.subscribeForNotifications(idMedia, token);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
        }
    }
}