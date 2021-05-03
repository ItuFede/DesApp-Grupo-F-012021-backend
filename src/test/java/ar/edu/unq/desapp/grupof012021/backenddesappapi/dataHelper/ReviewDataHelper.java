package ar.edu.unq.desapp.grupof012021.backenddesappapi.dataHelper;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;

import java.util.Date;

public class ReviewDataHelper {

    public static Review getReview()
    {
        Review review = new Review();
        review.setShortText("Good movie");
        review.setLongText("Like i said, gooooooood movie");
        review.setDate(new Date());
        review.setOriginalPlatform("Netflix");
        review.setLanguage("ENGLISH");
        review.setCritic(true);
        review.setMediaReview(new Media());
        review.setHasSpoilers(false);
        review.setRegion("EN_US");

        return review;
    }
}
