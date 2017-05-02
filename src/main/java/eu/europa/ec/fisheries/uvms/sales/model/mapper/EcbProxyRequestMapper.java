package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.schema.sales.proxy.ecb.types.v1.EcbProxyRequestMethod;
import eu.europa.ec.fisheries.schema.sales.proxy.ecb.types.v1.GetExchangeRateRequest;
import eu.europa.ec.fisheries.uvms.sales.model.exception.SalesMarshallException;
import org.joda.time.DateTime;

public class EcbProxyRequestMapper {

    public static String createGetExchangeRateRequest(String sourceCurrency, String targetCurrency, DateTime date) throws SalesMarshallException {
        GetExchangeRateRequest convertCurrencyRequest = new GetExchangeRateRequest()
                .withSourceCurrency(sourceCurrency)
                .withTargetCurrency(targetCurrency)
                .withDate(date)
                .withMethod(EcbProxyRequestMethod.GET_EXCHANGE_RATE);
        return JAXBMarshaller.marshallJaxBObjectToString(convertCurrencyRequest);
    }

}
