package ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;

public class ReviewDataHelper {

    public static Review getReview()
    {
        Review review = new Review();
        review.setShortText("Good movie");
        review.setLongText("Like i said, gooooooood movie");
        review.setOriginalPlatform("Netflix");
        review.setLanguage("ENGLISH");
        review.setPremium(true);
        review.setMediaReview(new Media());
        review.setHasSpoilers(false);
        review.setRegion("EN_US");

        return review;
    }

    public static ReviewDTO getReviewDTO(String shortText)
    {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.shortText = shortText;
        reviewDTO.shortText = "Like i said, gooooooood movie";
        reviewDTO.originalPlatform = "Netflix";
        reviewDTO.language = "ENGLISH";
        reviewDTO.isPremium = true;
        reviewDTO.hasSpoilers = false;
        reviewDTO.region = "EN_US";

        return reviewDTO;
    }
}
