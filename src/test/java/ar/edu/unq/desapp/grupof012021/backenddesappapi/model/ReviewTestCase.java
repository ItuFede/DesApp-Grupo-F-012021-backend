package ar.edu.unq.desapp.grupof012021.backenddesappapi.model;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper.ReviewDataHelper;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Platform;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ReviewTestCase {
    @Test
    public void givenReview_default_gettersAndSetters() throws Exception {
        UserCredentialsDTO userCreds = new UserCredentialsDTO();
        userCreds.username = "username";
        userCreds.password = "123";
        Platform platform = new Platform("Netflix");
        UserEntity user = new UserEntity(userCreds, platform);

        Review review = ReviewDataHelper.getReview();
        review.setUserEntity(user);

        Assert.assertEquals(review.getUserEntity(), user);
        Assert.assertEquals(review.getShortText(), "Good movie");
        Assert.assertEquals(review.getLongText(), "Like i said, gooooooood movie");
        Assert.assertTrue(new Date().getTime() >= review.getDate().getTime());
        Assert.assertEquals(review.getOriginalPlatform(), "Netflix");
        Assert.assertEquals(review.getLanguage(), "ENGLISH");
        Assert.assertTrue(review.isPremium());
        Assert.assertEquals(review.getReportMotives().size(), 0);
        Assert.assertFalse(review.isHasSpoilers());
        Assert.assertEquals(review.getRegion(), "EN_US");
    }
}
