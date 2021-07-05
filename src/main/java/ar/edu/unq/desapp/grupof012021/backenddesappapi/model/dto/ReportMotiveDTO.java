package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReportMotiveDTO {

    @NotNull(message = "reviewReportText can't be null")
    String reviewReportText;

    /*
    @NotNull(message = "userId can't be null")
    String userId;
    */
}
