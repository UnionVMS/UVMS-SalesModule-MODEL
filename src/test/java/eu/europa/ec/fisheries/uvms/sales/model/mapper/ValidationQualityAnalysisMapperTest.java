package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import com.google.common.collect.Lists;
import eu.europa.ec.fisheries.schema.rules.rule.v1.ErrorType;
import eu.europa.ec.fisheries.schema.rules.rule.v1.ValidationMessageType;
import eu.europa.ec.fisheries.schema.sales.ValidationQualityAnalysisType;
import eu.europa.ec.fisheries.uvms.rules.model.dto.ValidationResultDto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationQualityAnalysisMapperTest {

    @Test
    public void map() throws Exception {
        ValidationMessageType validationMessage1 = new ValidationMessageType();
        validationMessage1.setBrId("brId1");
        validationMessage1.setErrorType(ErrorType.ERROR);
        validationMessage1.setLevel("L01");
        validationMessage1.setMessage("message 1");
        validationMessage1.getXpaths().add("xpath1");
        validationMessage1.getXpaths().add("xpath2");

        ValidationMessageType validationMessage2 = new ValidationMessageType();
        validationMessage2.setBrId("brId2");
        validationMessage2.setErrorType(ErrorType.WARNING);
        validationMessage2.setLevel("L02");
        validationMessage2.setMessage("message 2");

        ValidationResultDto validationResultDto = new ValidationResultDto();
        validationResultDto.setValidationMessages(Lists.newArrayList(validationMessage1, validationMessage2));

        List<ValidationQualityAnalysisType> validationQualityAnalysis = ValidationQualityAnalysisMapper.map(validationResultDto);

        assertEquals(2, validationQualityAnalysis.size());

        assertEquals("brId1", validationQualityAnalysis.get(0).getID().getValue());
        assertEquals("SALE_BR", validationQualityAnalysis.get(0).getID().getSchemeID());
        assertEquals("ERR", validationQualityAnalysis.get(0).getTypeCode().getValue());
        assertEquals("FLUX_GP_VALIDATION_TYPE", validationQualityAnalysis.get(0).getTypeCode().getListID());
        assertEquals("L01", validationQualityAnalysis.get(0).getLevelCode().getValue());
        assertEquals("FLUX_GP_VALIDATION_LEVEL", validationQualityAnalysis.get(0).getLevelCode().getListID());
        assertEquals("message 1", validationQualityAnalysis.get(0).getResults().get(0).getValue());
        assertEquals("xpath1", validationQualityAnalysis.get(0).getReferencedItems().get(0).getValue());
        assertEquals("xpath2", validationQualityAnalysis.get(0).getReferencedItems().get(1).getValue());

        assertEquals("brId2", validationQualityAnalysis.get(1).getID().getValue());
        assertEquals("SALE_BR", validationQualityAnalysis.get(1).getID().getSchemeID());
        assertEquals("WAR", validationQualityAnalysis.get(1).getTypeCode().getValue());
        assertEquals("FLUX_GP_VALIDATION_TYPE", validationQualityAnalysis.get(1).getTypeCode().getListID());
        assertEquals("L02", validationQualityAnalysis.get(1).getLevelCode().getValue());
        assertEquals("FLUX_GP_VALIDATION_LEVEL", validationQualityAnalysis.get(1).getLevelCode().getListID());
        assertEquals("message 2", validationQualityAnalysis.get(1).getResults().get(0).getValue());
        assertTrue(validationQualityAnalysis.get(1).getReferencedItems().isEmpty());
    }

}