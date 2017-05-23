package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.sales.SalesModuleMethod;
import eu.europa.ec.fisheries.schema.sales.SalesQueryRequest;
import eu.europa.ec.fisheries.schema.sales.SalesReportRequest;
import eu.europa.ec.fisheries.schema.sales.ValidationQualityAnalysisType;
import eu.europa.ec.fisheries.uvms.rules.model.dto.ValidationResultDto;
import eu.europa.ec.fisheries.uvms.sales.model.exception.SalesMarshallException;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SalesModuleRequestMapper {

    public static String createSalesReportRequest(String request, ValidationResultDto validationResultDto) throws SalesMarshallException {
        List<ValidationQualityAnalysisType> validationQualityAnalysis = ValidationQualityAnalysisMapper.map(validationResultDto);

        SalesReportRequest salesReportRequest = new SalesReportRequest();
        salesReportRequest.setReport(checkNotNull(request));
        salesReportRequest.setMethod(SalesModuleMethod.REPORT);
        salesReportRequest.getValidationResults().addAll(validationQualityAnalysis);
        salesReportRequest.setMessageValidationStatus(FLUXGPResponseMapper.map(validationResultDto));

        return JAXBMarshaller.marshallJaxBObjectToString(salesReportRequest);
    }

    public static String createSalesQueryRequest(String query, ValidationResultDto validationResultDto) throws SalesMarshallException {
        List<ValidationQualityAnalysisType> validationQualityAnalysis = ValidationQualityAnalysisMapper.map(validationResultDto);

        SalesQueryRequest salesQueryRequest = new SalesQueryRequest();
        salesQueryRequest.setQuery(checkNotNull(query));
        salesQueryRequest.setMethod(SalesModuleMethod.QUERY);
        salesQueryRequest.getValidationResults().addAll(validationQualityAnalysis);
        salesQueryRequest.setMessageValidationStatus(FLUXGPResponseMapper.map(validationResultDto));

        return JAXBMarshaller.marshallJaxBObjectToString(salesQueryRequest);
    }

}
