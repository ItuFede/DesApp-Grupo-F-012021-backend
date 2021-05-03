package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.*;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.ActorRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.MediaRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.ActorServiceImpl;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations.MediaServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class MediaServiceTestCase {

    private static MediaService mediaService;
    private static MediaRepository repositoryMock;
    private static Media donnieDarko;

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
    }

    @Test
    public void givenMedia_whenCreated_mediaHasNotAnyReview() {
        Assertions.assertThat(donnieDarko.getReviews().isEmpty()).isTrue();
    }

    @Test
    public void givenMedia_whenAddReviewById_mediaHasReview() {
        Review aReview = new Review(
                "Buenardo",
                "La verdad es que la peli está muy buena yo le doy 5 estrellas",
                new Date(),
                "Netflix",
                "Spanish",
                false,
                false,
                "ES_AR",
                5.0
                );

        Mockito.when(repositoryMock.findById(donnieDarko.getId())).thenReturn(donnieDarko);
        mediaService.addReviewTo(donnieDarko.getId(), aReview);

        Mockito.verify(repositoryMock, times(1)).save(donnieDarko);
        Assertions.assertThat(donnieDarko.getReviews().size()).isEqualTo(1);
    }

    @Test
    public void givenMedia_whenSearchingForReviews_findAllReviewsByMediaId() {

    }
}
