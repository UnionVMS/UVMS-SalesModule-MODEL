package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.sales.*;
import eu.europa.ec.fisheries.uvms.sales.model.exception.SalesMarshallException;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SalesModuleRequestMapper {

    private SalesModuleRequestMapper() {}

    public static String createSalesReportRequest(String request, String status, List<ValidationQualityAnalysisType> validationQualityAnalysis,
                                                  String pluginToSendResponseThrough) throws SalesMarshallException {
        SalesReportRequest salesReportRequest = new SalesReportRequest();
        salesReportRequest.setReport(checkNotNull(request));
        salesReportRequest.setMethod(SalesModuleMethod.SAVE_REPORT);
        salesReportRequest.getValidationQualityAnalysises().addAll(validationQualityAnalysis);
        salesReportRequest.setMessageValidationStatus(status);
        salesReportRequest.setPluginToSendResponseThrough(pluginToSendResponseThrough);

        return JAXBMarshaller.marshallJaxBObjectToString(salesReportRequest);
    }

    public static String createSalesQueryRequest(String query, String status, List<ValidationQualityAnalysisType> validationQualityAnalysis, String pluginToSendResponseThrough) throws SalesMarshallException {
        SalesQueryRequest salesQueryRequest = new SalesQueryRequest();
        salesQueryRequest.setQuery(checkNotNull(query));
        salesQueryRequest.setMethod(SalesModuleMethod.QUERY);
        salesQueryRequest.getValidationQualityAnalysises().addAll(validationQualityAnalysis);
        salesQueryRequest.setMessageValidationStatus(status);
        salesQueryRequest.setPluginToSendResponseThrough(pluginToSendResponseThrough);

        return JAXBMarshaller.marshallJaxBObjectToString(salesQueryRequest);
    }

    public static String createRespondToInvalidMessageRequest(String messageGuid, List<ValidationQualityAnalysisType> validationQualityAnalysis,
                                                              String pluginToSendResponseThrough,
                                                              String sender, SalesIdType salesIdType) throws SalesMarshallException {

        RespondToInvalidMessageRequest respondToInvalidMessageRequest =
                fillRespondToInvalidMessageRequest(messageGuid, validationQualityAnalysis, pluginToSendResponseThrough, sender, salesIdType);

        return JAXBMarshaller.marshallJaxBObjectToString(respondToInvalidMessageRequest);
    }

    private static RespondToInvalidMessageRequest fillRespondToInvalidMessageRequest(String messageGuid,
                                                List<ValidationQualityAnalysisType> validationQualityAnalysis,
                                                     String pluginToSendResponseThrough, String sender, SalesIdType salesIdType) {
        RespondToInvalidMessageRequest respondToInvalidMessageRequest = new RespondToInvalidMessageRequest();
        respondToInvalidMessageRequest.setMethod(SalesModuleMethod.CREATE_INVALID_MESSAGE);
        respondToInvalidMessageRequest.getValidationQualityAnalysises().addAll(validationQualityAnalysis);
        respondToInvalidMessageRequest.setPluginToSendResponseThrough(pluginToSendResponseThrough);
        respondToInvalidMessageRequest.setSender(sender);
        respondToInvalidMessageRequest.setMessageGuid(messageGuid);
        respondToInvalidMessageRequest.setTypeOfId(salesIdType);
        return respondToInvalidMessageRequest;
    }

    public static String createFindReportByIdRequest(String id) throws SalesMarshallException {
        checkNotNull(id, "Cannot find reports with id null");

        FindReportByIdRequest findReportByIdRequest = new FindReportByIdRequest()
                .withMethod(SalesModuleMethod.FIND_REPORT_BY_ID)
                .withId(id);

        return JAXBMarshaller.marshallJaxBObjectToString(findReportByIdRequest);
    }

    public static String createFindReportByIdResponse(String report) throws SalesMarshallException {
        // Report can be null
        FindReportByIdResponse findReportByIdResponse = new FindReportByIdResponse()
                .withReport(report);

        return JAXBMarshaller.marshallJaxBObjectToString(findReportByIdResponse);
    }

    public static String createCheckForUniqueIdRequest(List<String> ids, SalesMessageIdType type) throws SalesMarshallException {
        CheckForUniqueIdRequest checkForUniqueIdRequest = new CheckForUniqueIdRequest()
                .withMethod(SalesModuleMethod.CHECK_UNIQUE_ID)
                .withType(type)
                .withIds(ids);

        return JAXBMarshaller.marshallJaxBObjectToString(checkForUniqueIdRequest);
    }

    public static String createCheckForUniqueIdResponse(boolean isUnique) throws SalesMarshallException {
        CheckForUniqueIdResponse checkForUniqueIdResponse = new CheckForUniqueIdResponse()
                .withUnique(isUnique);

        return JAXBMarshaller.marshallJaxBObjectToString(checkForUniqueIdResponse);
    }
}
