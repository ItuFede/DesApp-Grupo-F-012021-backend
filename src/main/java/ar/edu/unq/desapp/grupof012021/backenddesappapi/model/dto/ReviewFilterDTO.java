package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReviewFilterDTO {

    public boolean isPremium;
    public String originalPlatform;
    public String region;
    public String language;
    public boolean hasSpoilers;
    public String isOrdererType;
    public boolean isOrderAsc;

}
