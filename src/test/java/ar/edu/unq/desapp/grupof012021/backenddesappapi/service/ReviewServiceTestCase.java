package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.ReviewDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.UserDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.*;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ActorRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.UserEntityRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.security.JwtTokenUtil;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.ActorServiceImpl;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.ReviewServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType.*;
import static org.mockito.Mockito.mock;

public class ReviewServiceTestCase {

    //Constants
    private static final Long REVIEW_ID = Long.valueOf(1);

    //Services
    private static ReviewService reviewService;
    private static ReviewRepository reviewRepositoryMock;
    private static ActorService actorService;
    private static ActorRepository actorRepositoryMock;
    private static UserEntityRepository userEntityRepositoryMock;
    private static JwtTokenUtil jwtTokenUtilMock;
    //Entity
    private static Media donnieDarko;

    @BeforeAll
    public static void setUp() {
        reviewRepositoryMock = mock(ReviewRepository.class);
        userEntityRepositoryMock = mock(UserEntityRepository.class);
        jwtTokenUtilMock = mock(JwtTokenUtil.class);
        reviewService = new ReviewServiceImpl(reviewRepositoryMock, userEntityRepositoryMock, jwtTokenUtilMock);

        actorRepositoryMock = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepositoryMock);

        List<Genre> donnieDarkoGenres = new ArrayList<Genre>();
        donnieDarkoGenres.add(new Genre(SCIFI));
        donnieDarkoGenres.add(new Genre(DRAMA));
        donnieDarkoGenres.add(new Genre(MISTERY));

        List<Actor> actorsDonnieDarko = new ArrayList<Actor>();
        actorsDonnieDarko.add(new Actor("nm0000196", "Mike Myers"));
        actorsDonnieDarko.add(new Actor("nm0000552", "Eddie Murphy"));
        actorsDonnieDarko.add(new Actor("nm0001475", "John Lithgow"));
        actorsDonnieDarko.add(new Actor("nm0000139", "Cameron Diaz"));

        donnieDarko = new Media(
                "tt0246578",
                "donnieDarko",
                "donnieDarko",
                2001,
                null,
                113,
                MediaType.MOVIE,
                null,
                donnieDarkoGenres,
                actorsDonnieDarko
        );
    }

    @Test
    public void reviewNotExits_findById_null() {
        Review review = reviewService.getReviewById(REVIEW_ID);

        Assertions.assertNull(review);
    }

    @Test
    public void review_findById_getTheReview() {
        Review review = ReviewDataHelper.getReview();

        Mockito.when(reviewRepositoryMock.findById(review.getId())).thenReturn(review);

        Assertions.assertNotNull(review);
    }

    @Test
    public void reviewWithoutRanking_upvoteReview_reviewPositive() throws Exception {
        Review review = ReviewDataHelper.getReview();
        Mockito.when(reviewRepositoryMock.findById(review.getId())).thenReturn(review);

        reviewService.upvoteReview(review.getId(), "");

        Assertions.assertEquals(1, review.getReviewRankings().stream().count());
        Assertions.assertTrue(review.getReviewRankings().get(0).isPositiveVote());
    }

    @Test
    public void reviewWithoutRanking_downvoteReview_reviewNegative() throws Exception {
        Review review = ReviewDataHelper.getReview();
        Mockito.when(reviewRepositoryMock.findById(review.getId())).thenReturn(review);

        reviewService.downvoteReview(review.getId(), "");

        Assertions.assertEquals(1, review.getReviewRankings().stream().count());
        Assertions.assertFalse(review.getReviewRankings().get(0).isPositiveVote());
    }

    @Test
    public void reviewWithoutRanking_upvoteReviewTwiceSameUser_throwsException() throws Exception {
        Review review = ReviewDataHelper.getReview();
        UserEntity userEntity = UserDataHelper.getUserEntity();
        ReviewRanking reviewRanking = new ReviewRanking(true, review, userEntity);
        Mockito.when(reviewRepositoryMock.findById(review.getId())).thenReturn(review);
        Mockito.when(userEntityRepositoryMock.findByUsername(null)).thenReturn(userEntity);

        reviewService.upvoteReview(review.getId(), "");

        Assertions.assertThrows(Exception.class, () -> {
            reviewService.upvoteReview(review.getId(), "");
        });
    }

    @Test
    public void reviewWithoutRanking_downvoteReviewTwiceSameUser_throwsException() throws Exception {
        Review review = ReviewDataHelper.getReview();
        UserEntity userEntity = UserDataHelper.getUserEntity();
        ReviewRanking reviewRanking = new ReviewRanking(true, review, userEntity);
        Mockito.when(reviewRepositoryMock.findById(review.getId())).thenReturn(review);
        Mockito.when(userEntityRepositoryMock.findByUsername(null)).thenReturn(userEntity);

        reviewService.downvoteReview(review.getId(), "");

        Assertions.assertThrows(Exception.class, () -> {
            reviewService.downvoteReview(review.getId(), "");
        });
    }

    @Test
    public void reviewTemporalNotCreated_createTemporalReview_reviewTemporalAssociatedToMedia() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.shortText = "ShortText";
        reviewDTO.longText = "LongText";
        reviewDTO.originalPlatform = "Netflix";
        reviewDTO.language = "EN";
        reviewDTO.isPremium = false;
        reviewDTO.hasSpoilers = false;
        reviewDTO.region = "EN_US";

        Review review = reviewService.createTemporalReview(reviewDTO, donnieDarko);

        Assertions.assertEquals(review.getMediaReview().getId(), donnieDarko.getId());
    }

}