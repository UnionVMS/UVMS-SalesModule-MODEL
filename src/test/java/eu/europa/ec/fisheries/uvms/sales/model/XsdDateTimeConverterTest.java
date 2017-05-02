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
package eu.europa.ec.fisheries.uvms.sales.model;

import eu.europa.ec.fisheries.uvms.sales.model.mapper.XsdDateTimeConverter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class XsdDateTimeConverterTest {

    @Test
    public void testUnmarshal() {
        DateTime expected = new DateTime(2001, 10, 26, 19, 32, 52, DateTimeZone.UTC);
        assertEquals(expected, XsdDateTimeConverter.unmarshal("2001-10-26T21:32:52+02:00"));
    }

    @Test
    public void testMarshalDate() {
        DateTime dateTime = new DateTime(2017,1,1,12,13, DateTimeZone.UTC);
        assertEquals("2017-01-01Z", XsdDateTimeConverter.marshalDate(dateTime));
    }

    @Test
    public void testMarshalDateTime() {
        DateTime dateTime = new DateTime(2017,1,1,12,13, DateTimeZone.UTC);
        assertEquals("2017-01-01T12:13:00Z", XsdDateTimeConverter.marshalDateTime(dateTime));
    }

}