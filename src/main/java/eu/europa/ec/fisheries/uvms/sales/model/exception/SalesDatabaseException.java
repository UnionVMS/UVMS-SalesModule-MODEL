package eu.europa.ec.fisheries.uvms.sales.model.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class SalesDatabaseException extends Exception {

    public SalesDatabaseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SalesDatabaseException(String s) {
        super(s);
    }
}
