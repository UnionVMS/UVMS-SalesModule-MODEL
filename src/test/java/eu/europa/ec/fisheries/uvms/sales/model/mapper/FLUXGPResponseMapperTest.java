package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.sales.FLUXGPResponse;
import eu.europa.ec.fisheries.uvms.rules.model.dto.ValidationResultDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FLUXGPResponseMapperTest {

    @Test
    public void mapWhenErrorsAndWarnings() throws Exception {
        ValidationResultDto validationResultDto = new ValidationResultDto();
        validationResultDto.setIsError(true);
        validationResultDto.setIsWarning(true);
        assertEquals(FLUXGPResponse.NOK, FLUXGPResponseMapper.map(validationResultDto));
    }

    @Test
    public void mapWhenErrors() throws Exception {
        ValidationResultDto validationResultDto = new ValidationResultDto();
        validationResultDto.setIsError(true);
        validationResultDto.setIsWarning(false);
        assertEquals(FLUXGPResponse.NOK, FLUXGPResponseMapper.map(validationResultDto));
    }

    @Test
    public void mapWhenWarnings() throws Exception {
        ValidationResultDto validationResultDto = new ValidationResultDto();
        validationResultDto.setIsError(false);
        validationResultDto.setIsWarning(true);
        assertEquals(FLUXGPResponse.WOK, FLUXGPResponseMapper.map(validationResultDto));
    }

    @Test
    public void mapWhenOK() throws Exception {
        ValidationResultDto validationResultDto = new ValidationResultDto();
        validationResultDto.setIsError(false);
        validationResultDto.setIsWarning(false);
        assertEquals(FLUXGPResponse.OK, FLUXGPResponseMapper.map(validationResultDto));
    }

}