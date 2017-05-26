package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import com.google.common.collect.Lists;
import eu.europa.ec.fisheries.schema.rules.rule.v1.ErrorType;
import eu.europa.ec.fisheries.schema.rules.rule.v1.ValidationMessageType;
import eu.europa.ec.fisheries.uvms.rules.model.dto.ValidationResultDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SalesModuleRequestMapperTest {

    @Test
    public void createSalesReportRequest() throws Exception {
        String report = "<report />";
        String plugin = "FLUX";

        ValidationMessageType validationMessage1 = new ValidationMessageType();
        validationMessage1.setBrId("brId1");
        validationMessage1.setErrorType(ErrorType.ERROR);
        validationMessage1.setLevel("L01");
        validationMessage1.setMessage("message 1");

        ValidationResultDto validationResultDto = new ValidationResultDto();
        validationResultDto.setIsError(true);
        validationResultDto.setValidationMessages(Lists.newArrayList(validationMessage1));

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<SalesReportRequest xmlns=\"eu.europa.ec.fisheries.schema.sales\" xmlns:ns2=\"urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:20\">\n" +
                "    <method>REPORT</method>\n" +
                "    <validationResult>\n" +
                "        <ns2:LevelCode>L01</ns2:LevelCode>\n" +
                "        <ns2:TypeCode>ERR</ns2:TypeCode>\n" +
                "        <ns2:Result>message 1</ns2:Result>\n" +
                "        <ns2:ID>brId1</ns2:ID>\n" +
                "    </validationResult>\n" +
                "    <messageValidationStatus>NOK</messageValidationStatus>\n" +
                "    <pluginToSendResponseThrough>FLUX</pluginToSendResponseThrough>\n" +
                "    <report>&lt;report /&gt;</report>\n" +
                "</SalesReportRequest>\n", SalesModuleRequestMapper.createSalesReportRequest(report, validationResultDto, plugin));
    }

    @Test
    public void createSalesQueryRequest() throws Exception {
        String report = "<query />";
        String plugin = "FLUX";

        ValidationMessageType validationMessage1 = new ValidationMessageType();
        validationMessage1.setBrId("brId1");
        validationMessage1.setErrorType(ErrorType.WARNING);
        validationMessage1.setLevel("L01");
        validationMessage1.setMessage("message 1");

        ValidationResultDto validationResultDto = new ValidationResultDto();
        validationResultDto.setIsWarning(true);
        validationResultDto.setValidationMessages(Lists.newArrayList(validationMessage1));

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<SalesQueryRequest xmlns=\"eu.europa.ec.fisheries.schema.sales\" xmlns:ns2=\"urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:20\">\n" +
                "    <method>QUERY</method>\n" +
                "    <validationResult>\n" +
                "        <ns2:LevelCode>L01</ns2:LevelCode>\n" +
                "        <ns2:TypeCode>WAR</ns2:TypeCode>\n" +
                "        <ns2:Result>message 1</ns2:Result>\n" +
                "        <ns2:ID>brId1</ns2:ID>\n" +
                "    </validationResult>\n" +
                "    <messageValidationStatus>WOK</messageValidationStatus>\n" +
                "    <pluginToSendResponseThrough>FLUX</pluginToSendResponseThrough>\n" +
                "    <query>&lt;query /&gt;</query>\n" +
                "</SalesQueryRequest>\n", SalesModuleRequestMapper.createSalesQueryRequest(report, validationResultDto, plugin));
    }

    @Test
    public void createRespondToInvalidMessageRequest() throws Exception {
        String plugin = "FLUX";
        String sender = "STIJN";
        String messageGuid = "abc";

        ValidationMessageType validationMessage1 = new ValidationMessageType();
        validationMessage1.setBrId("brId1");
        validationMessage1.setErrorType(ErrorType.ERROR);
        validationMessage1.setLevel("L01");
        validationMessage1.setMessage("message 1");

        ValidationResultDto validationResultDto = new ValidationResultDto();
        validationResultDto.setIsError(true);
        validationResultDto.setValidationMessages(Lists.newArrayList(validationMessage1));

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<RespondToInvalidMessageRequest xmlns=\"eu.europa.ec.fisheries.schema.sales\" xmlns:ns2=\"urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:20\">\n" +
                "    <method>INVALID_MESSAGE</method>\n" +
                "    <validationResult>\n" +
                "        <ns2:LevelCode>L01</ns2:LevelCode>\n" +
                "        <ns2:TypeCode>ERR</ns2:TypeCode>\n" +
                "        <ns2:Result>message 1</ns2:Result>\n" +
                "        <ns2:ID>brId1</ns2:ID>\n" +
                "    </validationResult>\n" +
                "    <messageValidationStatus>NOK</messageValidationStatus>\n" +
                "    <pluginToSendResponseThrough>FLUX</pluginToSendResponseThrough>\n" +
                "    <sender>STIJN</sender>\n" +
                "    <messageGuid>abc</messageGuid>\n" +
                "</RespondToInvalidMessageRequest>\n", SalesModuleRequestMapper.createRespondToInvalidMessageRequest(messageGuid, validationResultDto, plugin, sender));
    }

}