package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.dataHelper.ReviewDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Genre;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.ReviewServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType.*;
import static org.mockito.Mockito.mock;

public class ReviewServiceTestCase {

    //Services
    private static ReviewService reviewService;
    private static ReviewRepository reviewRepositoryMock;

    //Constants
    private static final Long REVIEW_ID = Long.valueOf(1);

    //Entity
    private static Media donnieDarko;

    @BeforeAll
    public static void setUp()
    {
        reviewRepositoryMock = mock(ReviewRepository.class);
        reviewService = new ReviewServiceImpl(reviewRepositoryMock);

        List<Genre> donnieDarkoGenres = new ArrayList<Genre>();
        donnieDarkoGenres.add(new Genre(SCIFI));
        donnieDarkoGenres.add(new Genre(DRAMA));
        donnieDarkoGenres.add(new Genre(MISTERY));

        donnieDarko = new Media(
                "donnieDarko",
                "donnieDarko",
                Year.of(2001),
                null,
                113,
                MediaType.MOVIE,
                null,
                donnieDarkoGenres
        );
    }

    @Test
    public void reviewNotExits_findById_null()
    {
        Review review = reviewService.getReviewById(REVIEW_ID);

        Assertions.assertNull(review);
    }

    @Test
    public void review_findById_getTheReview()
    {
        Review review = ReviewDataHelper.getReview();

        Mockito.when(reviewRepositoryMock.findById(review.getId())).thenReturn(review);

        Assertions.assertNotNull(review);
    }

    @Test
    public void reviewWithoutRanking_upvoteReview_reviewPositive()
    {
        Review review = ReviewDataHelper.getReview();
        Mockito.when(reviewRepositoryMock.findById(review.getId())).thenReturn(review);

        reviewService.upvoteReview(review.getId());

        Assertions.assertEquals(1, review.getReviewRankings().stream().count());
        Assertions.assertTrue(review.getReviewRankings().get(0).isPositiveVote());
    }

    @Test
    public void reviewWithoutRanking_downvoteReview_reviewNegative()
    {
        Review review = ReviewDataHelper.getReview();
        Mockito.when(reviewRepositoryMock.findById(review.getId())).thenReturn(review);

        reviewService.downvoteReview(review.getId());

        Assertions.assertEquals(1, review.getReviewRankings().stream().count());
        Assertions.assertFalse(review.getReviewRankings().get(0).isPositiveVote());
    }

    @Test
    public void reviewTemporalNotCreated_createTemporalReview_reviewTemporalAssociatedToMedia() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.shortText = "ShortText";
        reviewDTO.longText = "LongText";
        reviewDTO.originalPlatform = "Netflix";
        reviewDTO.language = "EN";
        reviewDTO.isCritic = false;
        reviewDTO.hasSpoilers = false;
        reviewDTO.region = "EN_US";

        Review review = reviewService.createTemporalReview(reviewDTO,donnieDarko);

        Assertions.assertEquals(review.getMediaReview().getId(), donnieDarko.getId());
    }

}
