package org.jvalue;

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
import java.util.*;

/**
 * TestCase for Name.
 */
public class NameTest {

	/**
	 *
	 */
	protected Name fN0; // ("")
	protected Name fN1; // ("org", '.')
	protected Name fN2; // ("jvalue.org", '.')
	protected Name fN3; // ("www.jvalue.org", '.')
	
	@Before
	public void setUp() {
		fN0 = getName("");
	
		fN1 = getName("org", '.');
		fN2 = getName("jvalue.org", '.');
		fN3 = getName("www.jvalue.org", '.');
	}
	
	@Test
	public void testEquals() {
		assertEquals(fN0, getName(""));
		assertEquals(fN1, getName("org"));
		assertEquals(fN2, getName("jvalue#org"));
		assertEquals(fN3, getName("www#jvalue#org"));
	}
	
	@Test
	public void testToString() {
		String del0s = "";
		assertEquals(del0s, fN0.toString());
		
		String del1s = "org";
		assertEquals(del1s, fN1.toString());
		
		char del2 = fN2.getDelimiterChar();
		String del2s = "jvalue" + del2 + "org";
		assertEquals(del2s, fN2.toString());
		
		char del3 = fN3.getDelimiterChar();
		String del3s = "www" + del3 + "jvalue" + del3 + "org";
		assertEquals(del3s, fN3.toString());
	}
	
	@Test
	public void testDefaultValue() {
		Name defaultValue = getName("");
		assertEquals(defaultValue, fN0.getDefaultValue());
		assertEquals(defaultValue, fN1.getDefaultValue());
	}
	
	@Test
	public void testAppend() {
		assertEquals(fN1, getName("").append("org"));
		assertEquals(fN2, getName("jvalue").append("org"));
		assertEquals(fN3, getName("www#jvalue").append("org"));
	}
	
	@Test
	public void testAsString2() {
		assertEquals("", fN0.asString('*'));
		assertEquals("org", fN1.asString('*'));
		assertEquals("jvalue*org", fN2.asString('*'));
		assertEquals("www*jvalue*org", fN3.asString('*'));
	}
	
	@Test
	public void testAsStringArray() {
		String[] c0 = fN0.asStringArray();
		assertEquals(0, c0.length);
		
		String[] c1 = fN1.asStringArray();
		assertEquals(1, c1.length);
		assertEquals("org", c1[0]);

		String[] c2 = fN2.asStringArray();
		assertEquals(2, c2.length);
		assertEquals("jvalue", c2[0]);
		assertEquals("org", c2[1]);

		String[] c3 = fN3.asStringArray();
		assertEquals(3, c3.length);
		assertEquals("www", c3[0]);
		assertEquals("jvalue", c3[1]);
		assertEquals("org", c3[2]);
	}
	
	@Test
	public void testGetComponent() {
		try {
			assertEquals("org", fN1.getComponent(0));
			assertEquals("jvalue", fN2.getComponent(0));
			assertEquals("www", fN3.getComponent(0));
			assertEquals("org", fN2.getComponent(1));
			assertEquals("jvalue", fN3.getComponent(1));
			assertEquals("org", fN3.getComponent(2));
		} catch (IndexOutOfBoundsException iie) {
			fail("getComponent() threw exception on valid index");
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetComponent0() {
		fN0.getComponent(1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetComponent1() {
		fN1.getComponent(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetComponent2() {
		fN3.getComponent(17);
	}
		
	@Test
	public void testGetComponents() {
		Iterator<String> ni = fN3.components().iterator();
		for (int i = 0; i < fN3.getNoComponents(); i++) {
			try {
				assertEquals(ni.next(), fN3.getComponent(i));
			} catch (IndexOutOfBoundsException iie) {
				fail("getComponent() throw exception on valid index");
			}
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetContextName0() {
		fN0.getContextName();
	}
	
	@Test
	public void testGetContextName1() {
		try {
			assertEquals(getName(""), fN1.getContextName());
			assertEquals(getName("jvalue"), fN2.getContextName());
			assertEquals(getName("www#jvalue"), fN3.getContextName());
		} catch (IndexOutOfBoundsException nioob) {
			fail("getContextName of non-empty Name threw exception");
		}
	}

	@Test
	public void testDelEscScheme() {
		char td = '*';
		char te = '&';
	
		Name gn3 = getName("w\\\\ww#jva\\#lue#or\\#\\\\\\#g");
	
		assertEquals("w\\ww jva#lue or#\\#g", gn3.asString(' '));
		assertEquals(getName("w\\ww*jva#lue*or#\\#g", td, te), gn3);
	
		Name gn4 = getName("w&ww#jva*lue#or*&*g");
	
		assertEquals(getName("w&&ww*jva&*lue*or&*&&&*g", td, te), gn4);
	}
	
	@Test
	public void testGetFirstComponent() {
		try {
			assertEquals("org", fN1.getFirstComponent());
			assertEquals("jvalue", fN2.getFirstComponent());
			assertEquals("www", fN3.getFirstComponent());
		} catch (IndexOutOfBoundsException iie) {
			fail("getFirstComponent with first name threw exception");
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsert0() {
		fN0.insert(2, "test");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsert1() {
		fN3.insert(-1, "test");
	}
	
	@Test
	public void testInsert2() {
		try {
			assertEquals(getName("test"), fN0.insert(0, "test"));
			assertEquals(getName("test#org"), fN1.insert(0, "test"));
			assertEquals(getName("jvalue#test#org"), fN2.insert(1, "test"));
			assertEquals(getName("www#test#jvalue#org"), fN3.insert(1, "test"));
		} catch (IndexOutOfBoundsException iie) {
			fail("insert() rejected valid index");
		};
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(fN0.isEmpty());
		assertTrue(!fN1.isEmpty());
		assertTrue(!fN2.isEmpty());
		assertTrue(!fN3.isEmpty());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetLastComponent0() {
		fN0.getLastComponent();
	}
	
	@Test
	public void testGetLastComponent1() {
		try {
			assertEquals("org", fN1.getLastComponent());
			assertEquals("org", fN2.getLastComponent());
			assertEquals("org", fN3.getLastComponent());
		} catch (IndexOutOfBoundsException iie) {
			fail("getLastComponent() rejected valid index");
		}
	}
	
	@Test
	public void testGetNoComponent() {
		assertEquals(0, fN0.getNoComponents());
		assertEquals(1, fN1.getNoComponents());
		assertEquals(2, fN2.getNoComponents());
		assertEquals(3, fN3.getNoComponents());
	}
	
	@Test
	public void testPrepend() {
		assertEquals(getName("org"), fN0.prepend("org"));
		assertEquals(getName("jvalue#org"), fN1.prepend("jvalue"));
		assertEquals(getName("www#jvalue#org"), fN2.prepend("www"));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove0() {
		fN0.remove(1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove1() {
		fN3.remove(7);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove2() {
		fN2.remove(-1);
	}

	@Test
	public void testRemove3() {
		try {
			assertEquals(getName(""), fN1.remove(0));
			assertEquals(getName("jvalue"), fN2.remove(1));
			assertEquals(getName("www#jvalue"), fN3.remove(2));
		} catch (IndexOutOfBoundsException iie) {
			fail("remove() rejected valid index");
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testReplace0() {
		fN0.replace(0, "test");
	}


	@Test(expected = IndexOutOfBoundsException.class)
	public void testReplace1() {
		fN1.replace(-1, "test");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testReplace2() {
		fN2.replace(3, "test");
	}

	@Test
	public void testReplace3() {
		try {
			assertEquals(getName("com"), fN1.replace(0, "com"));
			assertEquals(getName("jvalue#com"), fN2.replace(1, "com"));
			assertEquals(getName("test#jvalue#org"), fN3.replace(0, "test"));
		} catch (IndexOutOfBoundsException iie) {
			fail("replace() rejected valid index");
		};
	}
	
	/**
	 *
	 */
	protected Name getName(String name) {
		return getName(name, Name.DEFAULT_DELIMITER_CHAR);
	}
	
	/**
	 *
	 */
	protected Name getName(String name, char delimiter) {
		return getName(name, delimiter, Name.DEFAULT_ESCAPE_CHAR);
	}
	
	/**
	 *
	 */
	protected Name getName(String name, char delimiter, char escape) {
		return new Name(name, delimiter, escape);
	}
	
}
