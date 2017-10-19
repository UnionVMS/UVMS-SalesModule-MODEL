package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.sales.CodeType;
import eu.europa.ec.fisheries.schema.sales.IDType;
import eu.europa.ec.fisheries.schema.sales.TextType;
import eu.europa.ec.fisheries.schema.sales.ValidationQualityAnalysisType;

import java.util.Collection;

public class ValidationQualityAnalysisMapper {

    private ValidationQualityAnalysisMapper() {}

    public static ValidationQualityAnalysisType map(String businessRuleId, String level, String errorType, String message, Collection<String> xPaths) {
            ValidationQualityAnalysisType validationQualityAnalysisType =
                    new ValidationQualityAnalysisType()
                        .withID(new IDType().withValue(businessRuleId)
                                .withSchemeID("SALE_BR"))
                        .withLevelCode(new CodeType().withValue(level)
                                .withListID("FLUX_GP_VALIDATION_LEVEL"))
                        .withTypeCode(new CodeType().withValue(errorType)
                                .withListID("FLUX_GP_VALIDATION_TYPE"))
                        .withResults(new TextType().withValue(message));

            for (String xPath : xPaths) {
                validationQualityAnalysisType
                        .getReferencedItems().add(new TextType().withValue(xPath));
            }

            return validationQualityAnalysisType;
    }

}
