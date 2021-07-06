package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.MediaDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.ReviewDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Genre;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.MediaRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ReviewRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.UserEntityRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.security.JwtTokenUtil;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.MediaServiceImpl;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.ReviewServiceImpl;
import org.assertj.core.api.Assertions;
import org.hibernate.persister.entity.Queryable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.*;
import java.util.*;

import static ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class MediaServiceTestCase {

    private static MediaService mediaService;
    private static MediaRepository mediaRepositoryMock;

    private static ReviewService reviewService;
    private static ReviewRepository reviewRepositoryMock;
    private static UserEntityRepository userEntityRepositoryMock;
    private static JwtTokenUtil jwtTokenUtilMock;
    private static EntityManager entityManagerMock;

    private static Media donnieDarko;

    private static Review aReview;
    private static Review anotherReview;
    private static Query quaryMock;

    @BeforeAll
    public static void setUp() {

        reviewRepositoryMock = mock(ReviewRepository.class);
        userEntityRepositoryMock = mock(UserEntityRepository.class);
        jwtTokenUtilMock = mock(JwtTokenUtil.class);
        entityManagerMock = mock(EntityManager.class);
        reviewService = new ReviewServiceImpl(reviewRepositoryMock, userEntityRepositoryMock, jwtTokenUtilMock);

        mediaRepositoryMock = mock(MediaRepository.class);
        mediaService = new MediaServiceImpl(mediaRepositoryMock, reviewService, jwtTokenUtilMock, entityManagerMock);

        List<Genre> donnieDarkoGenres = new ArrayList<Genre>();
        donnieDarkoGenres.add(new Genre(SCIFI));
        donnieDarkoGenres.add(new Genre(DRAMA));
        donnieDarkoGenres.add(new Genre(MISTERY));

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
                null
        );

        donnieDarko.setId(1);

        aReview = new Review(
                "Buenardo",
                "La verdad es que la peli está muy buena yo le doy 5 estrellas",
                "Netflix",
                "Spanish",
                false,
                false,
                "ES_AR",
                5.0,
                donnieDarko,
                null
        );

        anotherReview = new Review(
                "Malardo",
                "La verdad es que la peli no me gusto para nada le doy un 1",
                "Netflix",
                "Spanish",
                false,
                false,
                "ES_AR",
                1.0,
                donnieDarko,
                null
        );

        quaryMock = mock(Query.class);
    }

    @Test
    public void givenMedia_whenCreated_mediaHasNotAnyReview() {
        Assertions.assertThat(donnieDarko.getReviews().isEmpty()).isTrue();
    }

    @Test
    public void givenMedia_whenAddReviewById_mediaHasReview() {
        ReviewDTO unaReviewDTO = ReviewDataHelper.getReviewDTO("Good movie");
        Mockito.when(mediaRepositoryMock.findByIdStringMedia(donnieDarko.getIdStringMedia())).thenReturn(donnieDarko);

        mediaService.addReviewTo(donnieDarko.getIdStringMedia(), unaReviewDTO, "");

        Mockito.verify(mediaRepositoryMock, times(1)).save(donnieDarko);
        Assertions.assertThat(donnieDarko.getReviews().size()).isEqualTo(1);
    }

    @Test
    public void givenMedia_whenSearchingForReviews_findAllReviewsByMediaId() {
        ReviewDTO unaReviewDTO = ReviewDataHelper.getReviewDTO("test1");
        ReviewDTO otraReviewDTO = ReviewDataHelper.getReviewDTO("test2");

        Mockito.when(mediaRepositoryMock.findById(donnieDarko.getId())).thenReturn(donnieDarko);
        Mockito.when(mediaRepositoryMock.findByIdStringMedia(donnieDarko.getIdStringMedia())).thenReturn(donnieDarko);

        mediaService.addReviewTo(donnieDarko.getIdStringMedia(), unaReviewDTO, "");
        mediaService.addReviewTo(donnieDarko.getIdStringMedia(), otraReviewDTO, "");

        List<Review> allReviews = mediaService.findAllReviewsFrom(donnieDarko.getId());
        Assertions.assertThat(allReviews.size()).isEqualTo(2);
    }

    @Test
    public void givenFilterMediaDTO_findAllMediaFilter_querryHasAllFilters() throws Exception {
        MediaDTO mediaDTO = MediaDataHelper.getMediaDTO();
        List<Media> medias = new ArrayList<>();
        medias.add(MediaDataHelper.getMedia());

        Mockito.when(entityManagerMock.createQuery(
                "SELECT DISTINCT m FROM Media m " +
                "LEFT JOIN m.genres mg " +
                "LEFT JOIN m.reviews r " +
                "LEFT JOIN r.reviewRankings rr " +
                "LEFT JOIN m.actors a " +
                "WHERE m.year = 2001 AND m.endYear = 2001 " +
                "AND mg.genreName =  2 AND r.score >= 5.00 " +
                "AND rr.isPositiveVote = true " +
                "AND a.name = 'Jake Gyllenhaal' ")).thenReturn(quaryMock);
        Mockito.when(quaryMock.getResultList()).thenReturn(medias);

        List<Media> mediaList = mediaService.findAllMediaFilter(mediaDTO, 1, 1);

        Assertions.assertThat(mediaList.size()).isEqualTo(1);
    }

    @AfterEach
    public void teardown() {
        donnieDarko.setReviews(new ArrayList<Review>());
        Mockito.reset(mediaRepositoryMock);
    }
}
