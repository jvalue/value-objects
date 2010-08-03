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
public class QuantityUnitTest {

	/**
	 * 
	 */
	@Test
	public void testAdd() {
		QuantityUnit addend = new QuantityUnit(3.0);
		QuantityUnit augend = new QuantityUnit(4.0);
		QuantityUnit result = new QuantityUnit(7.0);
		assertEquals(addend.add(augend), result);
	}
	
	/**
	 * 
	 */
	@Test
	public void testAddWithCompatibleValues() {
		QuantityUnit add0 = new QuantityUnit(3.0, SiUnit.m);
		QuantityUnit aug0 = new QuantityUnit(4.0, SiUnit.m);
		QuantityUnit res0 = new QuantityUnit(7.0, SiUnit.m);
		assertEquals(add0.add(aug0), res0);

		QuantityUnit add1 = new QuantityUnit((-17.0), SiUnit.s);
		QuantityUnit aug1 = new QuantityUnit(17.0, SiUnit.s);
		QuantityUnit res1 = new QuantityUnit(0.0, SiUnit.s);
		assertEquals(add1.add(aug1), res1);

		QuantityUnit add2 = new QuantityUnit(42.0, SiUnit.cd);
		QuantityUnit aug2 = new QuantityUnit((-4.0), SiUnit.cd);
		QuantityUnit res2 = new QuantityUnit(38.0, SiUnit.cd);
		assertEquals(add2.add(aug2), res2);
	}
	
	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class) 
	public void testAddWithIncompatibleValues() {
		QuantityUnit addend = new QuantityUnit(3.0, SiUnit.m);
		QuantityUnit augend = new QuantityUnit(4.0, SiUnit.s);
		addend.add(augend);
	}
	
	/**
	 * 
	 */
	@Test
	public void testSubtract() {
		QuantityUnit arg0 = new QuantityUnit(3.0, SiUnit.m);
		QuantityUnit arg1 = new QuantityUnit(4.0, SiUnit.m);
		QuantityUnit result = new QuantityUnit((-1.0), SiUnit.m);
		assertEquals(arg0.subtract(arg1), result);
	}
	
	/**
	 * 
	 */
	@Test
	public void testMultiply() {
		QuantityUnit arg0 = new QuantityUnit(3.0, SiUnit.m);
		QuantityUnit arg1 = new QuantityUnit(4.0, SiUnit.m);
		QuantityUnit result = new QuantityUnit(12.0, SiUnit.m.power(2));
		assertEquals(result, arg0.multiply(arg1));
	}
	
	/**
	 * 
	 */
	@Test
	public void testDivide() {
		QuantityUnit arg0 = new QuantityUnit(3.0, SiUnit.m);
		QuantityUnit arg1 = new QuantityUnit(4.0, SiUnit.m);
		QuantityUnit result = new QuantityUnit(3.0 / 4.0);
		assertEquals(result, arg0.divide(arg1));
	}
	
	/**
	 * 
	 */
	@Test
	public void testPower() {
		QuantityUnit base = new QuantityUnit(13.2, SiUnit.cd);
		QuantityUnit result = new QuantityUnit(Math.pow(13.2, 13), SiUnit.cd.power(13));
		assertEquals(result, base.power(13));
	}
}
