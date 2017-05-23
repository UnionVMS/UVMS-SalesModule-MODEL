package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.rules.rule.v1.ErrorType;
import eu.europa.ec.fisheries.schema.sales.FLUXGPValidationType;

public class FLUXGPValidationTypeMapper {

    public static String map(ErrorType errorType) {

        switch (errorType) {
            case ERROR: return FLUXGPValidationType.ERR.name();
            case WARNING: return FLUXGPValidationType.WAR.name();
            default: throw new UnsupportedOperationException("No mapping provided for a validation message with error type " + errorType);
        }
    }

}
