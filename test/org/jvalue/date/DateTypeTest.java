/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jvalue.date;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

import org.jvalue.numbers.Range;
import org.jvalue.numbers.RangeBound;

/**
 * TestCase for NameType.
 */
public class DateTypeTest {

	DateType dateType1;
	DateType dateType2;
	
	/**
	 * create date types for tests
	 */
	@Before
	public void setup(){
		Calendar lowerBound1 = Calendar.getInstance();
		lowerBound1.set(1970, 1, 1); 
		Calendar upperBound1 = Calendar.getInstance();
		upperBound1.set(1980, 1, 1); 
		dateType1 = new DateType(lowerBound1, upperBound1);
		
		Calendar lower2 = Calendar.getInstance();
		lower2.set(1968, 12, 31);
		RangeBound<Calendar> lowerBound2 = new RangeBound<Calendar> (lower2, false); 
		dateType2 = new DateType(new Range<Calendar>(lowerBound2, null));
		
	}
	
	/**
	 * test inclusive range restriction on Calendar values
	 */
	@Test
	public void testDateRangeRestriction1() {

		Calendar date1 = Calendar.getInstance();
		date1.set(1970, 12, 1);
		assertTrue(dateType1.isValidInstance(date1));
		
		Calendar date2 = Calendar.getInstance();
		date2.set(2000, 3, 5);
		assertFalse(dateType1.isValidInstance(date2));
	}

	
	/**
	 * test non-inclusive range restriction on Calendar values
	 */
	@Test
	public void testDateRangeRestriction2() {
		Calendar date1 = Calendar.getInstance();
		date1.set(1969, 1, 1);
		assertTrue(dateType2.isValidInstance(date1));
		
		Calendar date2 = Calendar.getInstance();
		date2.set(1968, 12, 31);
		assertFalse(dateType2.isValidInstance(date2));
	}
		
	
	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidInstanceException0() {
		Calendar date = Calendar.getInstance();
		date.set(1789, 14, 1);
		dateType1.assertIsValidInstance(date);
	}
}
