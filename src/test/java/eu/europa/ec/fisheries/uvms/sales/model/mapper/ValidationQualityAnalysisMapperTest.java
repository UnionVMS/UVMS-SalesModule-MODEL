package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.sales.ValidationQualityAnalysisType;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ValidationQualityAnalysisMapperTest {

    @Test
    public void map() throws Exception {
        ValidationQualityAnalysisType validationQualityAnalysis =
                ValidationQualityAnalysisMapper.map("brId1", "L01", "ERR",
                        "message 1", Arrays.asList("xpath1", "xpath2"));

        assertEquals("brId1", validationQualityAnalysis.getID().getValue());
        assertEquals("SALE_BR", validationQualityAnalysis.getID().getSchemeID());
        assertEquals("ERR", validationQualityAnalysis.getTypeCode().getValue());
        assertEquals("FLUX_GP_VALIDATION_TYPE", validationQualityAnalysis.getTypeCode().getListID());
        assertEquals("L01", validationQualityAnalysis.getLevelCode().getValue());
        assertEquals("FLUX_GP_VALIDATION_LEVEL", validationQualityAnalysis.getLevelCode().getListID());
        assertEquals("message 1", validationQualityAnalysis.getResults().get(0).getValue());
        assertEquals("xpath1", validationQualityAnalysis.getReferencedItems().get(0).getValue());
        assertEquals("xpath2", validationQualityAnalysis.getReferencedItems().get(1).getValue());
    }
}