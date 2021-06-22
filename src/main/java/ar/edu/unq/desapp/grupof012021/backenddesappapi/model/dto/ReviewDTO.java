package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto;

import javax.validation.constraints.NotNull;

public class ReviewDTO {

    @NotNull
    public long idMedia;

    public String shortText;
    public String longText;
    public String originalPlatform;
    public String language;
    public boolean isPremium;
    public boolean hasSpoilers;
    public String region;

}
