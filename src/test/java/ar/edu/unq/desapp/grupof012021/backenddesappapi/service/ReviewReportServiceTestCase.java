package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.ReviewDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.UserDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.ReportMotive;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewReportRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.UserEntityRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.security.JwtTokenUtil;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.ReviewReportServiceImpl;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class ReviewReportServiceTestCase {

    //Services
    private static ReviewService reviewService;
    private static ReviewRepository reviewRepositoryMock;
    private static ReviewReportService reviewReportService;
    private static ReviewReportRepository reviewReportRepositoryMock;
    private static UserEntityRepository userEntityRepositoryMock;
    private static JwtTokenUtil jwtTokenUtilMock;

    @BeforeAll
    public static void setUp() {
        userEntityRepositoryMock = mock(UserEntityRepository.class);
        reviewRepositoryMock = mock(ReviewRepository.class);
        reviewReportRepositoryMock = mock(ReviewReportRepository.class);
        jwtTokenUtilMock = mock(JwtTokenUtil.class);

        reviewService = new ReviewServiceImpl(reviewRepositoryMock, userEntityRepositoryMock, jwtTokenUtilMock);
        reviewReportService = new ReviewReportServiceImpl(reviewRepositoryMock, reviewReportRepositoryMock, userEntityRepositoryMock, jwtTokenUtilMock);
    }

    @Test
    public void reviewWithoutReports_reportReview_reviewWithReport() {
        Review review = ReviewDataHelper.getReview();
        UserEntity userEntity = UserDataHelper.getUserEntity();

        Mockito.when(reviewRepositoryMock.findById(review.getId())).thenReturn(review);
        Mockito.when(jwtTokenUtilMock.getUsernameFromToken("")).thenReturn(null);
        Mockito.when(userEntityRepositoryMock.findByUsername(userEntity.getUsername())).thenReturn(userEntity);

        ReportMotive reportMotive = reviewReportService.reportReview(review.getId(), "Didn't like the review", "");

        Mockito.verify(reviewReportRepositoryMock, times(1)).save(reportMotive);
    }
}
