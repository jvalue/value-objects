package org.jvalue.si;

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
 * 
 */
public class QuantityUnitTest {

	/**
	 * 
	 */
	protected SiUnit siUnit_m = new SiUnit(new int[] {1, 0, 0, 0, 0, 0, 0});
	protected SiUnit siUnit_s = new SiUnit(new int[] {0, 0, 1, 0, 0, 0, 0});
	protected SiUnit siUnit_cd = new SiUnit(new int[] {0, 0, 0, 0, 0, 0, 1});
	

	@Test
	public void testAdd() {
		QuantityUnit addend = getQuantityUnit(3.0);
		QuantityUnit augend = getQuantityUnit(4.0);
		QuantityUnit result = getQuantityUnit(7.0);
		assertEquals(addend.add(augend), result);
	}
	
	@Test
	public void testAddWithCompatibleUnits() {
		QuantityUnit add0 = getQuantityUnit(3.0, siUnit_m);
		QuantityUnit aug0 = getQuantityUnit(4.0, siUnit_m);
		QuantityUnit res0 = getQuantityUnit(7.0, siUnit_m);
		assertEquals(add0.add(aug0), res0);

		QuantityUnit add1 = getQuantityUnit(-17.0, siUnit_s);
		QuantityUnit aug1 = getQuantityUnit(17.0, siUnit_s);
		QuantityUnit res1 = getQuantityUnit(0.0, siUnit_s);
		assertEquals(add1.add(aug1), res1);

		QuantityUnit add2 = getQuantityUnit(42.0, siUnit_cd);
		QuantityUnit aug2 = getQuantityUnit(-4.0, siUnit_cd);
		QuantityUnit res2 = getQuantityUnit(38.0, siUnit_cd);
		assertEquals(add2.add(aug2), res2);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testAddWithIncompatibleUnits() {
		QuantityUnit addend = getQuantityUnit(3.0, siUnit_m);
		QuantityUnit augend = getQuantityUnit(4.0, siUnit_s);
		addend.add(augend);
	}
	
	@Test
	public void testSubtract() {
		QuantityUnit arg0 = getQuantityUnit(3.0, siUnit_m);
		QuantityUnit arg1 = getQuantityUnit(4.0, siUnit_m);
		QuantityUnit result = getQuantityUnit(-1.0, siUnit_m);
		assertEquals(arg0.subtract(arg1), result);
	}
	
	@Test
	public void testMultiply() {
		QuantityUnit arg0 = getQuantityUnit(3.0, siUnit_m);
		QuantityUnit arg1 = getQuantityUnit(4.0, siUnit_m);
		QuantityUnit result = arg0.multiply(arg1);
	}
	
	@Test
	public void testDivide() {
		QuantityUnit arg0 = getQuantityUnit(3.0, siUnit_m);
		QuantityUnit arg1 = getQuantityUnit(4.0, siUnit_m);
		QuantityUnit result = arg0.divide(arg1);
	}
	
	/**
	 * 
	 */
	protected QuantityUnit getQuantityUnit(double number) {
		return getQuantityUnit(number, SiUnit.getDefaultValue());
	}
	
	/**
	 * 
	 */
	protected QuantityUnit getQuantityUnit(double number, SiUnit siUnit) {
		return new QuantityUnit(number, siUnit);
	}
	
}
