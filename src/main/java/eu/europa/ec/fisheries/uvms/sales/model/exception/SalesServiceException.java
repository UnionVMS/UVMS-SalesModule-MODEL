package eu.europa.ec.fisheries.uvms.sales.model.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class SalesServiceException extends RuntimeException {

    public SalesServiceException(String s) {
        super(s);
    }

    public SalesServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
