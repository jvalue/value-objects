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

package org.jvalue.numbers;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * TestCase for RangeBound values.
 */
public class RangeBoundTest {

	/**
	 * Tests equals along different number types
	 */
	@Test
	public void testEquals1() {
		RangeBound<Double> rbd1 = new RangeBound<Double>(6.0);
		RangeBound<Double> rbd2 = new RangeBound<Double>(5.0);
		assertFalse(rbd1.equals(rbd2));

		RangeBound<Integer> rbi1 = new RangeBound<Integer>(-4);
		RangeBound<Integer> rbi2 = new RangeBound<Integer>(-4);
		assertTrue(rbi1.equals(rbi2));
		
		RangeBound<Double> rbd3 = new RangeBound<Double>(0.0);
		RangeBound<Integer> rbi3 = new RangeBound<Integer>(0);
		assertFalse(rbd3.equals(rbi3));
	}

	/**
	 * Tests compareTo along different value/inclusive combinations
	 */
	@Test
	public void testEquals2() {
		RangeBound<Integer> rbi3 = new RangeBound<Integer>(7, true);
		RangeBound<Integer> rbi4 = new RangeBound<Integer>(7, false);
		assertFalse(rbi3.equals(rbi4));

		RangeBound<Integer> rbi5 = new RangeBound<Integer>(7, false);
		assertTrue(rbi4.equals(rbi5));
	}

	/**
	 * Test String range, using lexical ordering relation
	 */
	@Test
	public void testCompareTo3() {
		RangeBound<String> rbs1 = new RangeBound<String>("def");
		RangeBound<String> rbs2 = new RangeBound<String>("abc");
		assertFalse(rbs1.equals(rbs2));
	}

}