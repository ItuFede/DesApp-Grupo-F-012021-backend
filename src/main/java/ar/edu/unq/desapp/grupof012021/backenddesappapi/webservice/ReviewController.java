package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/review/")
public class ReviewController {

    @Autowired
    ReviewService service;

    @PostMapping("{id}/upvote")
    public ResponseEntity<?> upvote(@PathVariable long id) {
            service.upvoteReview(id);
            return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("{id}/downvote")
    public ResponseEntity<?> downvote(@PathVariable long id) {
            service.downvoteReview(id);
            return ResponseEntity.status(HttpStatus.OK).build();
    }
}