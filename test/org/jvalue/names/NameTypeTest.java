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

package org.jvalue.names;

import org.junit.*;
import static org.junit.Assert.*;
import org.jvalue.names.Name;

/**
 * TestCase for NameType.
 */
public class NameTypeTest {

	/**
	 * 
	 */
	@Test
	public void testExactNoComponentRestriction() {
		Name name = new Name("www.jvalue.org", '.');
		NameType nameType = new NameType(3);
		assertTrue(nameType.isValidInstance(name));
	}
	
	/**
	 * 
	 */
	@Test
	public void testNoComponentRangeRestriction() {
		NameType nameType = new NameType(3, 4);

		Name name0 = new Name("www.jvalue.org", '.');
		assertTrue(nameType.isValidInstance(name0));
		
		Name name1 = new Name("org.jvalue.names.Name", '.');
		assertTrue(nameType.isValidInstance(name1));
		
		Name name2 = new Name("haleluya");
		assertFalse(nameType.isValidInstance(name2));
		
		Name name3 = new Name("org.jvalue.si.QuantityUnitTypeTest.TotemPole", '.');
		assertFalse(nameType.isValidInstance(name3));
		
		Name name4 = new Name("x", 'x');
		assertFalse(nameType.isValidInstance(name4));
		name4 = name4.append("x");
		assertTrue(nameType.isValidInstance(name4));
		name4 = name4.append("x");
		assertTrue(nameType.isValidInstance(name4));
		name4 = name4.append("x");
		assertFalse(nameType.isValidInstance(name4));
	}
	
	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidInstanceException0() {
		Name name = Name.EMPTY_NAME;
		NameType nameType = new NameType(1);
		nameType.assertIsValidInstance(name);
	}

	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidInstanceException1() {
		Name name = new Name("www.jvalue.org", '.');
		NameType nameType = new NameType(2);
		nameType.assertIsValidInstance(name);
	}

}
