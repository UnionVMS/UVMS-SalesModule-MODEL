package eu.europa.ec.fisheries.uvms.sales.model.remote;

import eu.europa.ec.fisheries.schema.sales.Report;
import eu.europa.ec.fisheries.schema.sales.ReportQuery;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ReportDomainModel {

    /**
     * Finds a report by its GUID, internally known as extId.
     *
     * @param extId internal name for GUID
     * @return the found report
     * @throws javax.persistence.NoResultException if nothing is found
     */
    Report findByExtId(String extId);

    /**
     * Creates a report.
     *
     * @param report the report to be created
     * @return the created report
     */
    Report create(Report report);

    /**
     * Get a {@link Report} with all products eagerly loaded. If no object has been found, {@link javax.persistence.NoResultException} is thrown.
     * @param extId extId
     * @return the FluxReport with matching extId
     */
    Report findSalesDetails(String extId);

    /**
     * Search reports which apply to the provided query.
     * @param fluxReportQuery query
     * @return the found reports. When nothing found, an empty list is returned.
     */
    List<Report> search(ReportQuery fluxReportQuery);

    /**
     * Count how many reports apply to the provided query.
     * Paging and sorting is not taken into account.
     * @param fluxReportQuery query
     * @return how many reports apply to the provided query
     */
    long count(ReportQuery fluxReportQuery);

}
