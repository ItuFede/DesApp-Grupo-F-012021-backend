package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReportMotiveDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewReportService;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/review/")
@Validated
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewReportService reviewReportService;

    @RequestMapping(value = "{idReview}/upvote", method = RequestMethod.POST)
    public ResponseEntity<?> upvote(@PathVariable @Min(1) long idReview,
                                    @RequestHeader(value = "Authorization") @NotNull String token) {
        try {
            reviewService.upvoteReview(idReview, token);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception err) {
            if (err.getMessage().equals("User already vote this review")) {
                err.printStackTrace();
                return ResponseEntity.status(402).body(err.getMessage());
            }
            else {
                err.printStackTrace();
                return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
            }
        }
    }

    @RequestMapping(value = "{idReview}/downvote", method = RequestMethod.POST)
    public ResponseEntity<?> downvote(@PathVariable @Min(1) long idReview,
                                      @RequestHeader(value = "Authorization") @NotNull String token) {
        try {
            reviewService.downvoteReview(idReview, token);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception err) {
            if (err.getMessage().equals("User already vote this review")) {
                err.printStackTrace();
                return ResponseEntity.status(402).body(err.getMessage());
            }
            else {
                err.printStackTrace();
                return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
            }
        }
    }

    @RequestMapping(value = "{idReview}/report", method = RequestMethod.POST)
    public ResponseEntity<?> report(@Valid @RequestBody ReportMotiveDTO reportMotiveDTO,
                                    @PathVariable @Min(1) long idReview,
                                    @RequestHeader(value = "Authorization") @NotNull String token) {
        try {
            reviewReportService.reportReview(idReview, reportMotiveDTO.getReviewReportText(), token);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception err) {
            err.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }

}