package ar.edu.unq.desapp.grupof012021.backenddesappapi.model;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.MediaDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Actor;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Genre;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MediaTestCase {
    @Test
    public void givenMedia_default_gettersAndSetters() {
        Media media = MediaDataHelper.getMedia();
        media.setGenres(List.of(new Genre()));
        media.setActors(List.of(new Actor()));

        Assert.assertEquals(media.getPrimaryTitle(), "donnieDarko");
        Assert.assertTrue(media.getYear() == 2001);
        Assert.assertEquals(media.getEndYear(), null);
        Assert.assertTrue(media.getRuntimeMinutes() == 113);
        Assert.assertEquals(media.getMediaType(), MediaType.MOVIE);
        Assert.assertEquals(media.getEpisodes().size(), 0);
        Assert.assertEquals(media.getGenres().size(), 1);
        Assert.assertEquals(media.getActors().size(), 1);
    }
}
