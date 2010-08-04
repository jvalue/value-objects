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

package org.jvalue.si;

import org.junit.*;
import static org.junit.Assert.*;

import org.jvalue.numbers.Range;
import org.jvalue.numbers.RangeBound;

/**
 * Tests for self-defined QuantityUnit types
 */
public class QuantityUnitTypeTest {

	/**
	 * 
	 */
	@Test
	public void testTypeRestriction() {
		QuantityUnit addend = new QuantityUnit(7.0, SiUnit.m);
		QuantityUnit augend = new QuantityUnit(8.0, SiUnit.m);
		
		RangeBound<Double> lb = new RangeBound<Double>(0.0);
		RangeBound<Double> up = new RangeBound<Double>(10.0);
		Range<Double> range1 = new Range<Double>(lb, up);
		QuantityUnitType myLengthType = new QuantityUnitType(range1, SiUnit.m);

		assertTrue(myLengthType.isValidInstance(addend));
		assertTrue(myLengthType.isValidInstance(augend));
		assertFalse(myLengthType.isValidInstance(addend.add(augend)));
	}

	/**
	 * 
	 */
	public static class TotemPole {

		private QuantityUnit height;
		
		private static QuantityUnitType heightType;
		
		static{
			RangeBound<Double> lb = new RangeBound<Double>(0.0);
			RangeBound<Double> up = new RangeBound<Double>(10.0);
			Range<Double> range1 = new Range<Double>(lb, up);
			heightType = new QuantityUnitType(range1, SiUnit.m);
		}

		public QuantityUnit getHeight() {
			return height;
		}

		public void setHeight(QuantityUnit height) {
			heightType.assertIsValidInstance(height);
			this.height = height;
		}
	
	}
	
	/**
	 * 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAttributeRestrictions() {
		TotemPole r = new TotemPole();
		QuantityUnit height = new QuantityUnit(50.0, SiUnit.m);
		r.setHeight(height);
	}

}
