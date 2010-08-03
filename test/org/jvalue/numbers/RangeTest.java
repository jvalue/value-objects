package org.jvalue.numbers;

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

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests Range implementation
 */
public class RangeTest {

	/**
	 * Tests bounded, inclusive ranges
	 */
	@Test
	public void testIncludes1() {
		RangeBound<Double> lb = new RangeBound<Double>(0.0);
		RangeBound<Double> up = new RangeBound<Double>(10.0);
		Range<Double> range1 = new Range<Double>(lb, up);
		assertTrue(range1.includes(5.0));
		assertTrue(range1.includes(0.0));
		assertTrue(range1.includes(10.0));
		assertFalse(range1.includes(-1.0));
		assertFalse(range1.includes(11.0));
	}
	
	/**
	 * Tests non-inclusive bounds
	 */
	@Test
	public void testIncludes2() {
		RangeBound<Double> lb = new RangeBound<Double>(-10.0, false);
		RangeBound<Double> up = new RangeBound<Double>(-3.0, false);
		Range<Double> range1 = new Range<Double>(lb, up);
		assertTrue(range1.includes(-5.0));
		assertFalse(range1.includes(-10.0));
		assertFalse(range1.includes(-3.0));
	}
	
	/**
	 * Tests unbounded ranges
	 */
	@Test
	public void testIncludes3() {
		Range<Double> range1 = new Range<Double>(new RangeBound<Double>(5.0, false), null);
		assertTrue(range1.includes(17.0));
		assertFalse(range1.includes(5.0));
		assertFalse(range1.includes(0.0));
		
		Range<Double> range2 = new Range<Double>(null, new RangeBound<Double>(5.0, false));
		assertTrue(range2.includes(-17.0));
		assertFalse(range2.includes(5.0));
		assertFalse(range2.includes(12.0));		
	}
	
}
