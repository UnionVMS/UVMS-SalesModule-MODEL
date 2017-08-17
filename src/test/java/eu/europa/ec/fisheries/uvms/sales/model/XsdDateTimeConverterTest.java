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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import static eu.europa.ec.fisheries.uvms.sales.model.mapper.XsdDateTimeConverter.*;
import static org.junit.Assert.assertEquals;

public class XsdDateTimeConverterTest {

    private final String TIME =     "2017-05-11T12:10:38+02:00";
    private final String DATE = "2017-05-11+02:00";

    @Test
    public void testUnmarshal() throws Exception {
        DateTime time = unmarshal(TIME);

        assertEquals(2017, time.year().get());
        assertEquals(5, time.monthOfYear().get());
        assertEquals(11, time.dayOfMonth().get());
        assertEquals(12, time.hourOfDay().get());
        assertEquals(10, time.minuteOfHour().get());
        assertEquals(38, time.secondOfMinute().get());
        assertEquals(DateTimeZone.forOffsetHours(2), time.getZone());
    }

    @Test
    public void testMarshalDate() throws Exception {
        String marshalledDate = marshalDate(DateTime.parse(TIME));

        assertEquals(DATE, marshalledDate);
    }

    @Test
    public void testMarshalDateTime() throws Exception {
        String marshalledDate = marshalDateTime(DateTime.parse(TIME));

        assertEquals(TIME, marshalledDate);
    }

}