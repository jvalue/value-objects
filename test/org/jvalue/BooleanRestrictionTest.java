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

package org.jvalue;

import org.junit.*;
import static org.junit.Assert.*;

import org.jvalue.RangeRestriction;
import org.jvalue.numbers.NumberType;

/**
 * Tests boolean restrictions.
 */
public class BooleanRestrictionTest {
	
	/**
	 * 
	 */
	@Test
	public void testCompositeAnd() {
		RangeRestriction<Integer> rr1 = new RangeRestriction<Integer>(5, 9);
		RangeRestriction<Integer> rr2 = new RangeRestriction<Integer>(7, 10);
		NumberType<Integer> it = new NumberType<Integer>(rr1.and(rr2));
		
		assertFalse(it.isValidInstance(5));
		assertFalse(it.isValidInstance(6));
		assertTrue(it.isValidInstance(7));
		assertTrue(it.isValidInstance(9));
		assertFalse(it.isValidInstance(10));
		assertFalse(it.isValidInstance(4));
	}
		
	/**
	 * 
	 */
	@Test
	public void testCompositeOr() {
		RangeRestriction<Integer> rr1 = new RangeRestriction<Integer>(5, 7);
		RangeRestriction<Integer> rr2 = new RangeRestriction<Integer>(9, 10);
		NumberType<Integer> it = new NumberType<Integer>(rr1.or(rr2));
		
		assertTrue(it.isValidInstance(5));
		assertTrue(it.isValidInstance(6));
		assertTrue(it.isValidInstance(7));
		assertTrue(it.isValidInstance(9));
		assertTrue(it.isValidInstance(10));
		assertFalse(it.isValidInstance(4));
		assertFalse(it.isValidInstance(8));
		assertFalse(it.isValidInstance(11));
	}
	
}
