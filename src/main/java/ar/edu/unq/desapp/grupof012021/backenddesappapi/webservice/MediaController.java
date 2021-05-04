package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.MediaService;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/media/")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "{idMedia}/review", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getReviewsFromMedia(@PathVariable long idMedia) {
        List<Review> reviews = mediaService.findAllReviewsFrom(idMedia);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @RequestMapping(value = "{idMedia}/review", method = RequestMethod.POST)
    public ResponseEntity<?> addReviewsToMedia(@PathVariable long idMedia, @RequestBody ReviewDTO reviewDTO) {
        Review addReview = reviewService.createTemporalReview(reviewDTO, mediaService.findById(idMedia));
        mediaService.addReviewTo(idMedia, addReview);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}