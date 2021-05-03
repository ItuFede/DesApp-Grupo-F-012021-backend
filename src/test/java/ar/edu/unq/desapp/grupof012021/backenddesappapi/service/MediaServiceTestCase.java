package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.dataHelper.ReviewDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.*;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.MediaRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.MediaServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class MediaServiceTestCase {

    private static MediaService mediaService;
    private static MediaRepository repositoryMock;
    private static Media donnieDarko;

    private static Review aReview;
    private static Review anotherReview;

    @BeforeAll
    public static void setUp() {
        repositoryMock = mock(MediaRepository.class);
        mediaService = new MediaServiceImpl(repositoryMock);
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

        donnieDarko.setId(1);

        aReview = new Review(
                "Buenardo",
                "La verdad es que la peli está muy buena yo le doy 5 estrellas",
                "Netflix",
                "Spanish",
                false,
                false,
                "ES_AR",
                5.0
        );

        anotherReview = new Review(
                "Malardo",
                "La verdad es que la peli no me gusto para nada le doy un 1",
                "Netflix",
                "Spanish",
                false,
                false,
                "ES_AR",
                1.0
        );
    }

    @Test
    public void givenMedia_whenCreated_mediaHasNotAnyReview() {
        Assertions.assertThat(donnieDarko.getReviews().isEmpty()).isTrue();
    }

    @Test
    public void givenMedia_whenAddReviewById_mediaHasReview() {
        Review unaReview = ReviewDataHelper.getReview();
        Mockito.when(repositoryMock.findById(donnieDarko.getId())).thenReturn(donnieDarko);
        mediaService.addReviewTo(donnieDarko.getId(), unaReview);

        Mockito.verify(repositoryMock, times(1)).save(donnieDarko);
        Assertions.assertThat(donnieDarko.getReviews().size()).isEqualTo(1);
    }

    @Test
    public void givenMedia_whenSearchingForReviews_findAllReviewsByMediaId() {
        Mockito.when(repositoryMock.findById(donnieDarko.getId())).thenReturn(donnieDarko);

        mediaService.addReviewTo(donnieDarko.getId(), aReview);
        mediaService.addReviewTo(donnieDarko.getId(), anotherReview);

        List<Review> allReviews = mediaService.findAllReviewsFrom(donnieDarko.getId());
        Assertions.assertThat(allReviews.size()).isEqualTo(2);
    }

    @AfterEach
    public void teardown() {
        donnieDarko.setReviews(new ArrayList<Review>());
        Mockito.reset(repositoryMock);
    }
}
