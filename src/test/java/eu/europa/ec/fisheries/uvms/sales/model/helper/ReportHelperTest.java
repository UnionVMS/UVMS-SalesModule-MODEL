package eu.europa.ec.fisheries.uvms.sales.model.helper;

import com.google.common.collect.Lists;
import eu.europa.ec.fisheries.schema.sales.*;
import eu.europa.ec.fisheries.uvms.sales.model.constant.Purpose;
import eu.europa.ec.fisheries.uvms.sales.model.mother.AAPProductTypeMother;
import eu.europa.ec.fisheries.uvms.sales.model.mother.ReportMother;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ReportHelperTest {

    private ReportHelper reportHelper;

    @Before
    public void init() {
        reportHelper = new ReportHelper();
    }

    @Test
    public void testIsReportCorrectedOrDeletedWhenFalse() {
        CodeType purpose = new CodeType().withValue(Purpose.ORIGINAL.getNumericCode()+"");
        FLUXReportDocumentType fluxReportDocument = new FLUXReportDocumentType()
                .withPurposeCode(purpose);
        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(fluxReportDocument);
        Report report = new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertFalse(reportHelper.isReportCorrectedOrDeleted(report));
    }

    @Test
    public void testIsReportCorrectedOrDeletedWhenCorrected() {
        CodeType purpose = new CodeType().withValue(Purpose.CORRECTION.getNumericCode()+"");
        FLUXReportDocumentType fluxReportDocument = new FLUXReportDocumentType()
                .withPurposeCode(purpose);
        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(fluxReportDocument);
        Report report = new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertTrue(reportHelper.isReportCorrectedOrDeleted(report));
    }

    @Test
    public void testIsReportCorrectedOrDeletedWhenDeleted() {
        CodeType purpose = new CodeType().withValue(Purpose.DELETE.getNumericCode()+"");
        FLUXReportDocumentType fluxReportDocument = new FLUXReportDocumentType()
                .withPurposeCode(purpose);
        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(fluxReportDocument);
        Report report = new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertTrue(reportHelper.isReportCorrectedOrDeleted(report));
    }

    @Test
    public void testGetSalesLocationCountry() {
        String salesLocationCountry = "BEL";
        Report report = ReportMother.with(null, null, salesLocationCountry, null, null);
        assertEquals(salesLocationCountry, reportHelper.getSalesLocationCountry(report));
    }

    @Test
    public void testGetLandingCountry() {
        String landingCountry = "BEL";
        Report report = ReportMother.with(null, landingCountry, null, null, null);
        assertEquals(landingCountry, reportHelper.getLandingCountry(report));
    }


    @Test
    public void testGetVesselFlagState() {
        String flagState = "BEL";
        Report report = ReportMother.with(null, null,null, "BEL", null);
        assertEquals(flagState, reportHelper.getVesselFlagState(report));
    }

    @Test
    public void testGetVesselExtId() {
        String extId = "bla";
        VesselTransportMeansType vessel = new VesselTransportMeansType()
                .withIDS(new IDType().withValue(extId));


        FishingActivityType fishingActivity = new FishingActivityType()
                .withRelatedVesselTransportMeans(vessel);

        SalesDocumentType salesDocument = new SalesDocumentType()
                .withSpecifiedFishingActivities(fishingActivity);

        SalesReportType salesReport = new SalesReportType()
                .withIncludedSalesDocuments(salesDocument);

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withSalesReports(salesReport);

        Report report =  new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertEquals(extId, reportHelper.getVesselExtId(report));
    }

    @Test
    public void testGetDocumentDate() {
        DateTime date = new DateTime(2017, 4, 18, 0, 0);

        SalesEventType salesEvent = new SalesEventType()
                .withOccurrenceDateTime(new DateTimeType().withDateTime(date));

        SalesDocumentType salesDocument = new SalesDocumentType()
                .withSpecifiedSalesEvents(salesEvent);

        SalesReportType salesReport = new SalesReportType()
                .withIncludedSalesDocuments(salesDocument);

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withSalesReports(salesReport);

        Report report =  new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertEquals(date, reportHelper.getDocumentDate(report));
    }

    @Test
    public void testGetDocumentCurrency() {
        String currency = "EUR";

        SalesDocumentType salesDocument = new SalesDocumentType()
                .withCurrencyCode(new CodeType().withValue(currency));

        SalesReportType salesReport = new SalesReportType()
                .withIncludedSalesDocuments(salesDocument);

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withSalesReports(salesReport);

        Report report =  new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertEquals(currency, reportHelper.getDocumentCurrency(report));
    }

    @Test
    public void testGetProductsOfReport() {
        AAPProductType product1 = AAPProductTypeMother.withTotalPrice(10);
        AAPProductType product2 = AAPProductTypeMother.withTotalPrice(30);
        List<AAPProductType> products = Lists.newArrayList(product1, product2);

        SalesBatchType salesBatch = new SalesBatchType()
                .withSpecifiedAAPProducts(products);

        SalesDocumentType salesDocument = new SalesDocumentType()
                .withSpecifiedSalesBatches(salesBatch);

        SalesReportType salesReport = new SalesReportType()
                .withIncludedSalesDocuments(salesDocument);

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withSalesReports(salesReport);

        Report report =  new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertEquals(products, reportHelper.getProductsOfReport(report));
    }

    @Test
    public void testGetFLUXReportDocumentOwner() {
        FLUXPartyType fluxParty = new FLUXPartyType()
                .withIDS(new IDType().withValue("test"));

        FLUXReportDocumentType fluxReportDocument = new FLUXReportDocumentType()
                .withOwnerFLUXParty(fluxParty);

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(fluxReportDocument);

        Report report = new Report()
            .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertEquals(fluxParty, reportHelper.getFLUXReportDocumentOwner(report));
    }

    @Test
    public void testGetFLUXReportDocumentOwnerId() {
        FLUXPartyType fluxParty = new FLUXPartyType()
                .withIDS(new IDType().withValue("test"));

        FLUXReportDocumentType fluxReportDocument = new FLUXReportDocumentType()
                .withOwnerFLUXParty(fluxParty);

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(fluxReportDocument);

        Report report = new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertEquals("test", reportHelper.getFLUXReportDocumentOwnerId(report));
    }

    @Test
    public void testGetFLUXReportDocumentId() {
        FLUXReportDocumentType fluxReportDocument = new FLUXReportDocumentType()
                .withIDS(new IDType().withValue("abc"));

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(fluxReportDocument);

        Report report = new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertEquals("abc", reportHelper.getFLUXReportDocumentId(report));
    }

    @Test
    public void testGetFLLUXReportDocumentReferencedId() {
        FLUXReportDocumentType fluxReportDocument = new FLUXReportDocumentType()
                .withReferencedID(new IDType().withValue("abc"));

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(fluxReportDocument);

        Report report = new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertEquals("abc", reportHelper.getFLUXReportDocumentReferencedId(report));
    }

    @Test(expected = NullPointerException.class)
    public void testGetFLLUXReportDocumentReferencedIdWhenNoReferencedId() {
        FLUXReportDocumentType fluxReportDocument = new FLUXReportDocumentType();

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(fluxReportDocument);

        Report report = new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertEquals("abc", reportHelper.getFLUXReportDocumentReferencedId(report));
    }

    @Test
    public void testGetFLLUXReportDocumentReferencedIdOrNull() {
        FLUXReportDocumentType fluxReportDocument = new FLUXReportDocumentType()
                .withReferencedID(new IDType().withValue("abc"));

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(fluxReportDocument);

        Report report = new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertEquals("abc", reportHelper.getFLUXReportDocumentReferencedIdOrNull(report));
    }

    @Test
    public void testGetFLLUXReportDocumentReferencedIdOrNullWhenNoReferencedId() {
        FLUXReportDocumentType fluxReportDocument = new FLUXReportDocumentType();

        FLUXSalesReportMessage fluxSalesReportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(fluxReportDocument);

        Report report = new Report()
                .withFLUXSalesReportMessage(fluxSalesReportMessage);

        assertNull(reportHelper.getFLUXReportDocumentReferencedIdOrNull(report));
    }

    @Test
    public void testGetId() {
        FLUXSalesReportMessage reportMessage = new FLUXSalesReportMessage()
                .withFLUXReportDocument(new FLUXReportDocumentType()
                    .withIDS(new IDType().withValue("id")));

        assertEquals("id", reportHelper.getId(reportMessage));
    }

    @Test
    public void testGetCreationDate() {
        DateTime date = new DateTime();
        Report report = ReportMother.withCreationDate(date);
        assertEquals(date, reportHelper.getCreationDate(report));
    }

    @Test
    public void testGetReferenceIdsToTakeOverDocumentsWhenSuccess() {
        Report report = new Report()
                .withFLUXSalesReportMessage(new FLUXSalesReportMessage()
                    .withSalesReports(new SalesReportType()
                        .withIncludedSalesDocuments(new SalesDocumentType()
                            .withTakeoverDocumentIDs(new IDType().withValue("hello"),
                                                     new IDType().withValue("hi")))));

        List<String> referenceIdsToTakeOverDocuments = reportHelper.getReferenceIdsToTakeOverDocuments(report);
        assertEquals(Lists.newArrayList("hello", "hi"), referenceIdsToTakeOverDocuments);
    }

    @Test
    public void testGetReferenceIdsToTakeOverDocumentsWhenListOfTakeOverDocumentIdsIsEmpty() {
        Report report = new Report()
                .withFLUXSalesReportMessage(new FLUXSalesReportMessage()
                        .withSalesReports(new SalesReportType()
                                .withIncludedSalesDocuments(new SalesDocumentType())));

        List<String> referenceIdsToTakeOverDocuments = reportHelper.getReferenceIdsToTakeOverDocuments(report);
        assertTrue(referenceIdsToTakeOverDocuments.isEmpty());
    }

    @Test
    public void testGetReferenceIdsToTakeOverDocumentsWhenThereAreNoSalesReports() {
        Report report = new Report()
                .withFLUXSalesReportMessage(new FLUXSalesReportMessage());

        List<String> referenceIdsToTakeOverDocuments = reportHelper.getReferenceIdsToTakeOverDocuments(report);
        assertTrue(referenceIdsToTakeOverDocuments.isEmpty());
    }

    @Test
    public void testHasReferencesToTakeOverDocumentsWhenTrue() {
        Report report = new Report()
                .withFLUXSalesReportMessage(new FLUXSalesReportMessage()
                        .withSalesReports(new SalesReportType()
                                .withIncludedSalesDocuments(new SalesDocumentType()
                                        .withTakeoverDocumentIDs(new IDType().withValue("hello world")))));

        assertTrue(reportHelper.hasReferencesToTakeOverDocuments(report));
    }

    @Test
    public void testHasReferencesToTakeOverDocumentsWhenFalseBecauseListOfTakeOverDocumentIdsIsEmpty() {
        Report report = new Report()
                .withFLUXSalesReportMessage(new FLUXSalesReportMessage()
                        .withSalesReports(new SalesReportType()
                                .withIncludedSalesDocuments(new SalesDocumentType())));

        assertFalse(reportHelper.hasReferencesToTakeOverDocuments(report));
    }

    @Test
    public void testHasReferencesToTakeOverDocumentsWhenFalseBecauseThereAreNoSalesReports() {
        Report report = new Report()
                .withFLUXSalesReportMessage(new FLUXSalesReportMessage());

        assertFalse(reportHelper.hasReferencesToTakeOverDocuments(report));
    }
}