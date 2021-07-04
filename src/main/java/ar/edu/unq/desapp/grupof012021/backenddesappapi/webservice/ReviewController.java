package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReportMotiveDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewReportService;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/review/")
@Validated
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewReportService reviewReportService;

    @RequestMapping(value = "{idReview}/upvote", method = RequestMethod.POST)
    public ResponseEntity<?> upvote(@PathVariable @Min(1) long idReview) {
        reviewService.upvoteReview(idReview);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "{idReview}/downvote", method = RequestMethod.POST)
    public ResponseEntity<?> downvote(@PathVariable @Min(1) long idReview) {
        reviewService.downvoteReview(idReview);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "{idReview}/report", method = RequestMethod.POST)
    public ResponseEntity<?> report(@Valid @RequestBody ReportMotiveDTO reportMotiveDTO, @PathVariable @Min(1) long idReview, @RequestHeader(value="Authorization") @NotNull String token) {
        reviewReportService.reportReview(idReview, reportMotiveDTO.getReviewReportText(), token);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}