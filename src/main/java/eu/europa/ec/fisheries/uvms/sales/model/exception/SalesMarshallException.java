package eu.europa.ec.fisheries.uvms.sales.model.exception;

/**
 * Created by MATBUL on 9/02/2017.
 */
public class SalesMarshallException extends Throwable {

    public SalesMarshallException(String s, Throwable rootException) {
        super(s, rootException);
    }
}
