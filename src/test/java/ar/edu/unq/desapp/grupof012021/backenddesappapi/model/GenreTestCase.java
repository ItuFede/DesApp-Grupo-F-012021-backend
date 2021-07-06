package ar.edu.unq.desapp.grupof012021.backenddesappapi.model;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Genre;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenreTestCase {
    @Test
    public void defaultConstructor_created_noExceptionThrown() {
        Assertions.assertNotNull(new Genre());
        Assertions.assertDoesNotThrow(() -> new Genre());
    }

    @Test
    public void givenGenre_whenInvalidGenreType_throwsException() {
        Assertions.assertThrows(Exception.class, () -> {
            Genre.getMediaGenreTypeFromString("asfdjhsdlkfj");
        });
    }

    @Test
    public void givenGenreTypeString_whenGetMediaGenreTypeFromString_returnGenreType() throws Exception {
        Genre actionGenre = new Genre(MediaGenreType.ACTION);
        MediaGenreType genreType = actionGenre.getGenreName();
        MediaGenreType accion = Genre.getMediaGenreTypeFromString("accion");
        MediaGenreType drama = Genre.getMediaGenreTypeFromString("drama");
        MediaGenreType adventure = Genre.getMediaGenreTypeFromString("adventure");
        MediaGenreType animation = Genre.getMediaGenreTypeFromString("animation");
        MediaGenreType crime = Genre.getMediaGenreTypeFromString("crime");
        MediaGenreType mistery = Genre.getMediaGenreTypeFromString("mistery");
        MediaGenreType family = Genre.getMediaGenreTypeFromString("family");
        MediaGenreType scifi = Genre.getMediaGenreTypeFromString("scifi");

        Assertions.assertEquals(accion, MediaGenreType.ACTION);
        Assertions.assertEquals(drama, MediaGenreType.DRAMA);
        Assertions.assertEquals(adventure, MediaGenreType.ADVENTURE);
        Assertions.assertEquals(animation, MediaGenreType.ANIMATION);
        Assertions.assertEquals(crime, MediaGenreType.CRIME);
        Assertions.assertEquals(mistery, MediaGenreType.MISTERY);
        Assertions.assertEquals(family, MediaGenreType.FAMILY);
        Assertions.assertEquals(scifi, MediaGenreType.SCIFI);
        Assertions.assertEquals(genreType, MediaGenreType.ACTION);
    }
}
