package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReviewDTO {

    @NotNull(message = "shortText can't be null")
    public String shortText;

    @NotNull(message = "longText can't be null")
    public String longText;

    @NotNull(message = "originalPlatform can't be null")
    public String originalPlatform;

    @NotNull(message = "language can't be null")
    public String language;

    @NotNull(message = "isPremium can't be null")
    public boolean isPremium;

    @Min(value = 1, message = "score can't be null")
    @Max(value = 5, message = "score can't be higher than 5.0")
    public double score;

    public boolean hasSpoilers;
    public String region;
    public String isOrdererType;
    public boolean isOrderAsc;
}
