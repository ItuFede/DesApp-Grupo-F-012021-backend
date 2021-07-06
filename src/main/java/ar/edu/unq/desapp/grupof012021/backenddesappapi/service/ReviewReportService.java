package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.ReportMotive;

public interface ReviewReportService {
    ReportMotive reportReview(long reviewId, String reportMotiveText, String accessToken);
}
