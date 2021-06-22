package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Genre;
import java.time.Year;

public class MediaDTO {

    private String idStringMedia;
    private String primaryTitle;
    private Year year;
    private Year endYear;
    private Integer runtimeMinutes;
    private Genre genre;

}
