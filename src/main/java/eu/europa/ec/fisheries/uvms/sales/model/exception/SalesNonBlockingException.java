package eu.europa.ec.fisheries.uvms.sales.model.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class SalesNonBlockingException extends SalesServiceException {

    public SalesNonBlockingException(String s) {
        super(s);
    }

    public SalesNonBlockingException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
