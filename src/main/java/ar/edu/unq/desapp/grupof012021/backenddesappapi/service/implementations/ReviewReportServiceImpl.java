package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.DataLoader;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.ReportMotive;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewReportRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.UserEntityRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ReviewReportService")
public class ReviewReportServiceImpl implements ReviewReportService {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private final ReviewReportRepository reviewReportRepository;

    @Autowired
    private final UserEntityRepository userEntityRepository;

    public ReviewReportServiceImpl(ReviewRepository reviewRepository, ReviewReportRepository reviewReportRepository, UserEntityRepository userEntityRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewReportRepository = reviewReportRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public void reportReview(long reviewId, String reportMotiveText, long userId) {
        Review review = reviewRepository.findById(reviewId);
        UserEntity userEntity = userEntityRepository.findById(userId);
        ReportMotive reportMotive = new ReportMotive(reportMotiveText, review, userEntity);
        reviewReportRepository.save(reportMotive);
    }

}