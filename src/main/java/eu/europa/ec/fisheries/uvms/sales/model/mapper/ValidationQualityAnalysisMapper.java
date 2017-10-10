package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.rules.rule.v1.ValidationMessageType;
import eu.europa.ec.fisheries.schema.sales.CodeType;
import eu.europa.ec.fisheries.schema.sales.IDType;
import eu.europa.ec.fisheries.schema.sales.TextType;
import eu.europa.ec.fisheries.schema.sales.ValidationQualityAnalysisType;
import eu.europa.ec.fisheries.uvms.rules.model.dto.ValidationResultDto;

import java.util.ArrayList;
import java.util.List;

public class ValidationQualityAnalysisMapper {

    private ValidationQualityAnalysisMapper() {}

    public static List<ValidationQualityAnalysisType> map(ValidationResultDto validationResultDto) {
        List<ValidationQualityAnalysisType> validationQualityAnalysis = new ArrayList<>();
        for (ValidationMessageType validationMessage: validationResultDto.getValidationMessages()) {
            validationQualityAnalysis.add(new ValidationQualityAnalysisType()
                    .withID(new IDType().withValue(validationMessage.getBrId())
                                        .withSchemeID("SALE_BR"))
                    .withLevelCode(new CodeType()   .withValue(validationMessage.getLevel())
                                                    .withListID("FLUX_GP_VALIDATION_LEVEL"))
                    .withTypeCode(new CodeType()    .withValue(FLUXGPValidationTypeMapper.map(validationMessage.getErrorType()))
                                                    .withListID("FLUX_GP_VALIDATION_TYPE"))
                    .withResults(new TextType().withValue(validationMessage.getMessage())));
        }
        return validationQualityAnalysis;
    }

}
