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

/**
 * 
 */
public class SiUnitTest {

	/**
	 * 
	 */
	@Test
	public void testNoneValue() {
		assertTrue(new SiUnit(new int[7]).equals(SiUnit.NONE));
	}

	/**
	 * 
	 */
	@Test
	public void testMultiply() {
		SiUnit s = SiUnit.s;
		assertEquals(s.multiply(s), s.power(2));
	}
	
	/**
	 * 
	 */
	@Test
	public void testDivide() {
		SiUnit none = SiUnit.NONE;
		SiUnit s = SiUnit.s;
		assertSame(none.divide(s).getDimension(SiUnit.s_INDEX), -1);
	}
	
	/**
	 * 
	 */
	@Test
	public void testPower() {
		assertTrue(SiUnit.m.power(13).getDimension(SiUnit.m_INDEX) == 13);
		assertTrue(SiUnit.m.power(-42).getDimension(SiUnit.m_INDEX) == -42);
	}
	
}
