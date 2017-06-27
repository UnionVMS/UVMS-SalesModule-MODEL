package eu.europa.ec.fisheries.uvms.sales.model.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class SalesDatabaseException extends RuntimeException {

    public SalesDatabaseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SalesDatabaseException(String s) {
        super(s);
    }
}
