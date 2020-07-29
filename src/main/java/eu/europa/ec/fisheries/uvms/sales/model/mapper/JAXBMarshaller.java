/*
﻿Developed with the contribution of the European Commission - Directorate General for Maritime Affairs and Fisheries
© European Union, 2015-2016.

This file is part of the Integrated Fisheries Data Management (IFDM) Suite. The IFDM Suite is free software: you can
redistribute it and/or modify it under the terms of the GNU General Public License as published by the
Free Software Foundation, either version 3 of the License, or any later version. The IFDM Suite is distributed in
the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a
copy of the GNU General Public License along with the IFDM Suite. If not, see <http://www.gnu.org/licenses/>.
 */
package eu.europa.ec.fisheries.uvms.sales.model.mapper;

import eu.europa.ec.fisheries.uvms.commons.xml.AbstractJAXBMarshaller;
import eu.europa.ec.fisheries.uvms.commons.xml.JAXBRuntimeException;
import eu.europa.ec.fisheries.uvms.sales.model.exception.SalesMarshallException;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.HashMap;
import java.util.Map;

public class JAXBMarshaller extends AbstractJAXBMarshaller {

    private JAXBMarshaller() {}

    /**
     * Marshalls a JAXB Object to a XML String representation
     *
     * @param <T>
     * @param data
     * @return
     * @throws SalesMarshallException
     */
    public static <T> String marshallJaxBObjectToString(T data) throws SalesMarshallException {
        try {
            Map<String,Object> properties = new HashMap<>();
            properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            return marshallToString(data, properties);
        } catch (JAXBException | JAXBRuntimeException ex) {
            throw new SalesMarshallException("Error when marshalling Object to String", ex);
        }
    }

    /**
     * Unmarshalls a textMessage to the desired class.
     *
     * @param textMessage
     * @param clazz class to marshall to. The class must be the
     * root object of the unmarshalled message!
     * @return
     * @throws
     * SalesMarshallException
     */
    public static <R> R unmarshallTextMessage(TextMessage textMessage, Class<R> clazz) throws SalesMarshallException {
        try {
            return unmarshallString(textMessage.getText(), clazz);
        } catch (JMSException ex) {
            throw new SalesMarshallException("Error when unmarshalling text message", ex);
        }
    }

    /**
     * Unmarshalls a string to the desired class.
     *
     * @param text
     * @param clazz class to marshall to. The class must be the
     * root object of the unmarchalled message!
     * @return
     * @throws
     * SalesMarshallException
     */
    public static <R> R unmarshallString(String text, Class<R> clazz) throws SalesMarshallException {
        try {
            return unmarshallTo(text,clazz);
        } catch (JAXBException | JAXBRuntimeException ex) {
            throw new SalesMarshallException("Error when unmarshalling text", ex);
        }
    }
}
