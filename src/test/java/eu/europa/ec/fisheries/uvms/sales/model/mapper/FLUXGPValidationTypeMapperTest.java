package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.rules.rule.v1.ErrorType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FLUXGPValidationTypeMapperTest {

    @Test
    public void mapToFLUXGPValidationTypeWhenWarning() throws Exception {
        assertEquals("WAR", FLUXGPValidationTypeMapper.map(ErrorType.WARNING));
    }

    @Test
    public void mapToFLUXGPValidationTypeWhenError() throws Exception {
        assertEquals("ERR", FLUXGPValidationTypeMapper.map(ErrorType.ERROR));
    }

}