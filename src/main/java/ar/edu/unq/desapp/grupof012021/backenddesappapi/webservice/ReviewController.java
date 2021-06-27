package ar.edu.unq.desapp.grupof012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReportMotiveDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.ReportMotive;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewReportService;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.validation.Valid;

@RestController
@RequestMapping("/review/")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewReportService reviewReportService;

    @RequestMapping(value = "{id}/upvote", method = RequestMethod.POST)
    public ResponseEntity<?> upvote(@PathVariable long id) {
        reviewService.upvoteReview(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "{id}/downvote", method = RequestMethod.POST)
    public ResponseEntity<?> downvote(@PathVariable long id) {
        reviewService.downvoteReview(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "{id}/report", method = RequestMethod.POST)
    public ResponseEntity<?> report(@Valid @RequestBody ReportMotiveDTO reportMotiveDTO, @PathVariable long id) {
        reviewReportService.reportReview(id, reportMotiveDTO.getReviewReportText(), Long.valueOf(reportMotiveDTO.getUserId()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}