package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

public interface ReviewReportService
{
    void reportReview(long reviewId, String reportMotiveText, long userId);
}
