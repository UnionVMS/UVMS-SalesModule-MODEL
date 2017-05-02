package eu.europa.ec.fisheries.uvms.sales.model.remote;

import eu.europa.ec.fisheries.schema.sales.SavedSearchGroup;
import eu.europa.ec.fisheries.uvms.sales.model.exception.SalesDatabaseException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface SavedSearchGroupDomainModel {
    List<SavedSearchGroup> findByUser(String user);
    SavedSearchGroup create(SavedSearchGroup savedSearchGroup);
    void delete(SavedSearchGroup savedSearchGroup);
    void delete(Integer id) throws SalesDatabaseException;
}
