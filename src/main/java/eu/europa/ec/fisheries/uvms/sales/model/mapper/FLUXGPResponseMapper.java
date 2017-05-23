package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.sales.FLUXGPResponse;
import eu.europa.ec.fisheries.uvms.rules.model.dto.ValidationResultDto;

/**
 * Created by stihft on 23/05/2017.
 */
public class FLUXGPResponseMapper {

    public static FLUXGPResponse map(ValidationResultDto validationResultDto) {
        if (validationResultDto.isError()) {
            return FLUXGPResponse.NOK;
        } else if (validationResultDto.isWarning()) {
            return FLUXGPResponse.WOK;
        } else {
            return FLUXGPResponse.OK;
        }
    }

}
