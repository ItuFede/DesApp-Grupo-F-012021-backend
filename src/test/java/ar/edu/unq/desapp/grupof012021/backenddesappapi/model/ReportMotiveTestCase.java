package ar.edu.unq.desapp.grupof012021.backenddesappapi.model;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.ReportMotive;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ReportMotiveTestCase {
    @Test
    public void emptyConstructor_created_emptyValues() {
        ReportMotive report = new ReportMotive();
        Assert.assertEquals(report.getUserEntity(), null);
        Assert.assertEquals(report.getMotiveText(), null);
        Assert.assertNotNull(report.getId());
    }
}
