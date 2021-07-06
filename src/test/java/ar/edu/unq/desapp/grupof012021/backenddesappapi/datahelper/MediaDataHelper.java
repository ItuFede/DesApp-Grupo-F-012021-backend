package ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;

public class MediaDataHelper {

    public static MediaDTO getMediaDTO() {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.year = 2001;
        mediaDTO.endYear = 2001;
        mediaDTO.scoreReview = 5.0;
        mediaDTO.genre = "Drama";
        mediaDTO.positiveVote = "true";
        mediaDTO.actorName = "Jake Gyllenhaal";

        return mediaDTO;
    }

    public static Media getMedia() {
        Media media = new Media(
                "tt0246578",
                "donnieDarko",
                "donnieDarko",
                2001,
                null,
                113,
                MediaType.MOVIE,
                null,
                null,
                null
        );

        return media;
    }
}
