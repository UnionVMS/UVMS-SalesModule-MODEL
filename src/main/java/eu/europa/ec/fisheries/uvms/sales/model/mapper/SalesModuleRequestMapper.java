package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.sales.*;
import eu.europa.ec.fisheries.uvms.rules.model.dto.ValidationResultDto;
import eu.europa.ec.fisheries.uvms.sales.model.exception.SalesMarshallException;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SalesModuleRequestMapper {

    public static String createSalesReportRequest(String request, ValidationResultDto validationResultDto, String pluginToSendResponseThrough) throws SalesMarshallException {
        List<ValidationQualityAnalysisType> validationQualityAnalysis = ValidationQualityAnalysisMapper.map(validationResultDto);

        SalesReportRequest salesReportRequest = new SalesReportRequest();
        salesReportRequest.setReport(checkNotNull(request));
        salesReportRequest.setMethod(SalesModuleMethod.REPORT);
        salesReportRequest.getValidationResults().addAll(validationQualityAnalysis);
        salesReportRequest.setMessageValidationStatus(FLUXGPResponseMapper.map(validationResultDto));
        salesReportRequest.setPluginToSendResponseThrough(pluginToSendResponseThrough);

        return JAXBMarshaller.marshallJaxBObjectToString(salesReportRequest);
    }

    public static String createSalesQueryRequest(String query, ValidationResultDto validationResultDto, String pluginToSendResponseThrough) throws SalesMarshallException {
        List<ValidationQualityAnalysisType> validationQualityAnalysis = ValidationQualityAnalysisMapper.map(validationResultDto);

        SalesQueryRequest salesQueryRequest = new SalesQueryRequest();
        salesQueryRequest.setQuery(checkNotNull(query));
        salesQueryRequest.setMethod(SalesModuleMethod.QUERY);
        salesQueryRequest.getValidationResults().addAll(validationQualityAnalysis);
        salesQueryRequest.setMessageValidationStatus(FLUXGPResponseMapper.map(validationResultDto));
        salesQueryRequest.setPluginToSendResponseThrough(pluginToSendResponseThrough);

        return JAXBMarshaller.marshallJaxBObjectToString(salesQueryRequest);
    }

    public static String createRespondToInvalidMessageRequest(String messageGuid, ValidationResultDto validationResultDto, String pluginToSendResponseThrough, String sender) throws SalesMarshallException {
        List<ValidationQualityAnalysisType> validationQualityAnalysis = ValidationQualityAnalysisMapper.map(validationResultDto);

        RespondToInvalidMessageRequest respondToInvalidMessageRequest = new RespondToInvalidMessageRequest();
        respondToInvalidMessageRequest.setMethod(SalesModuleMethod.INVALID_MESSAGE);
        respondToInvalidMessageRequest.getValidationResults().addAll(validationQualityAnalysis);
        respondToInvalidMessageRequest.setMessageValidationStatus(FLUXGPResponseMapper.map(validationResultDto));
        respondToInvalidMessageRequest.setPluginToSendResponseThrough(pluginToSendResponseThrough);
        respondToInvalidMessageRequest.setSender(sender);
        respondToInvalidMessageRequest.setMessageGuid(messageGuid);

        return JAXBMarshaller.marshallJaxBObjectToString(respondToInvalidMessageRequest);
    }


}
