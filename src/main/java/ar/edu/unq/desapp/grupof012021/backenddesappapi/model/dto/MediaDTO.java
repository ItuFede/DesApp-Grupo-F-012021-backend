package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Genre;
import java.time.Year;

public class MediaDTO {

    private String idStringMedia;
    private String primaryTitle;
    public Integer year;
    public Integer endYear;
    private Integer runtimeMinutes;
    public String genre;

}
