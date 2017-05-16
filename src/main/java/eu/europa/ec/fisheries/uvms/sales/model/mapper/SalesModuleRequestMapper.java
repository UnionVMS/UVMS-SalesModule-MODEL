package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.sales.SalesModuleMethod;
import eu.europa.ec.fisheries.schema.sales.SalesQueryRequest;
import eu.europa.ec.fisheries.schema.sales.SalesReportRequest;
import eu.europa.ec.fisheries.uvms.sales.model.exception.SalesMarshallException;

import static com.google.common.base.Preconditions.checkNotNull;

public class SalesModuleRequestMapper {

    public static String createSalesReportRequest(String request, SalesModuleMethod method) throws SalesMarshallException {
        SalesReportRequest salesReportRequest = new SalesReportRequest();
        salesReportRequest.setMethod(checkNotNull(method));
        salesReportRequest.setReport(checkNotNull(request));

        return JAXBMarshaller.marshallJaxBObjectToString(salesReportRequest);
    }

    public static String createSalesQueryRequest(String query, SalesModuleMethod method) throws SalesMarshallException {
        SalesQueryRequest salesQueryRequest = new SalesQueryRequest();
        salesQueryRequest.setQuery(checkNotNull(query));
        salesQueryRequest.setMethod(checkNotNull(method));

        return JAXBMarshaller.marshallJaxBObjectToString(salesQueryRequest);
    }

}
