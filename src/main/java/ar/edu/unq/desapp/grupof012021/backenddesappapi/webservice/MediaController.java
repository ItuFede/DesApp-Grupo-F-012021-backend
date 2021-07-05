package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaRedisDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewFilterDTO;
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
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/media")
@Validated
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getMediaFilter(@Valid @RequestBody MediaDTO mediaDTO,
                                                      @RequestParam(value = "offset") int offset,
                                                      @RequestParam(value = "limit") int limit) {
        try {
            List<Media> medias = mediaService.findAllMediaFilter(mediaDTO, offset, limit);
            return new ResponseEntity<>(medias, HttpStatus.OK);
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }

    @RequestMapping(value = "{idStringMedia}/review", method = RequestMethod.GET)
    public ResponseEntity<?> getReviewsFromMedia(@RequestBody ReviewFilterDTO reviewFilterDTO,
                                                            @PathVariable String idStringMedia,
                                                            @RequestParam(value = "offset", defaultValue = "1") @Min(1) int offset,
                                                            @RequestParam(value = "limit", defaultValue = "1") @Min(0) int limit) {
        try {
            List<Review> reviews = mediaService.findAllReviewsFilter(reviewFilterDTO, idStringMedia, offset, limit);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }

    @RequestMapping(value = "{idStringMedia}/review", method = RequestMethod.POST)
    public ResponseEntity<?> addReviewsToMedia(@PathVariable @NotNull String idStringMedia,
                                               @Valid @RequestBody ReviewDTO reviewDTO,
                                               @RequestHeader(value="Authorization") @NotNull String token) {
        try {
            mediaService.addReviewTo(idStringMedia, reviewDTO, token);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
        }
    }

    @RequestMapping(value = "{idStringMedia}", method = RequestMethod.GET)
    public ResponseEntity<?> getMediaRedis(@PathVariable String idStringMedia) {
        try {
            MediaRedisDTO media = mediaService.findMediaRedis(idStringMedia);
            return new ResponseEntity<>(media, HttpStatus.OK);
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }

    @RequestMapping(value = "{idMedia}/subscribe", method = RequestMethod.POST)
    public ResponseEntity<?> subscribeForNotifications(@PathVariable String idMedia, @RequestHeader(value="Authorization") String token) {
        try {
            mediaService.subscribeForNotifications(idMedia, token);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }
}
