package ar.edu.unq.desapp.grupof012021.backenddesappapi.datahelper;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewFilterDTO;

public class ReviewFilterDataHelper {

    public static ReviewFilterDTO getReviewFilterDTO() {
        ReviewFilterDTO reviewFilterDTO = new ReviewFilterDTO();
        reviewFilterDTO.isPremium = true;
        reviewFilterDTO.originalPlatform = "Nerflix";
        reviewFilterDTO.region = "EN_US";
        reviewFilterDTO.language = "English";
        reviewFilterDTO.hasSpoilers = true;
        reviewFilterDTO.isOrdererType = "date";
        reviewFilterDTO.isOrderAsc = true;

        return reviewFilterDTO;
    }

}
