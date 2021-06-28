package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.MediaService;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
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

    @RequestMapping(value = "{idMedia}/review", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getReviewsFromMedia(@Valid @RequestBody ReviewDTO reviewDTO, @PathVariable long idMedia, @RequestParam(value = "offset") int offset, @RequestParam(value = "limit") int limit) {
        List<Review> reviews = mediaService.findAllReviewsFilter(reviewDTO, idMedia, offset, limit);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @RequestMapping(value = "{idMedia}/review", method = RequestMethod.POST)
    public ResponseEntity<?> addReviewsToMedia(@PathVariable long idMedia, @RequestBody ReviewDTO reviewDTO) {
        Review addReview = reviewService.createTemporalReview(reviewDTO, mediaService.findById(idMedia));
        mediaService.addReviewTo(idMedia, addReview);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}