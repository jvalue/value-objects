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

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

import org.jvalue.names.Name;

/**
 * TestCase for Name.
 */
public class NameTest {

	/**
	 *
	 */
	protected Name ne; // empty name
	protected Name n0; // ("")
	protected Name n1; // ("org", '.')
	protected Name n2; // ("jvalue.org", '.')
	protected Name n3; // ("www.jvalue.org", '.')
	
	@Before
	public void setUp() {
		ne = Name.EMPTY_NAME;
		n0 = getName("");
		n1 = getName("org", '.');
		n2 = getName("jvalue.org", '.');
		n3 = getName("www.jvalue.org", '.');
	}
	
	@Test
	public void testEquals() {
		assertEquals(n0, getName(""));
		assertEquals(n1, getName("org"));
		assertEquals(n2, getName("jvalue#org"));
		assertEquals(n3, getName("www#jvalue#org"));
	}
	
	@Test
	public void testToString() {
		String del0s = "";
		assertEquals(del0s, n0.toString());
		
		String del1s = "org";
		assertEquals(del1s, n1.toString());
		
		char del2 = n2.getDelimiterChar();
		String del2s = "jvalue" + del2 + "org";
		assertEquals(del2s, n2.toString());
		
		char del3 = n3.getDelimiterChar();
		String del3s = "www" + del3 + "jvalue" + del3 + "org";
		assertEquals(del3s, n3.toString());
	}
	
	@Test
	public void testDefaultValue() {
		Name defaultValue = getName("");
		assertEquals(defaultValue, n0.getDefaultValue());
		assertEquals(defaultValue, n1.getDefaultValue());
	}
	
	@Test
	public void testAppend() {
		assertEquals(n1, ne.append("org"));
		assertEquals(n2, getName("jvalue").append("org"));
		assertEquals(n3, getName("www#jvalue").append("org"));
	}
	
	@Test
	public void testAsString2() {
		assertEquals("", n0.asString('*'));
		assertEquals("org", n1.asString('*'));
		assertEquals("jvalue*org", n2.asString('*'));
		assertEquals("www*jvalue*org", n3.asString('*'));
	}
	
	@Test
	public void testAsStringArray() {
		String[] c0 = n0.asStringArray();
		assertEquals(1, c0.length);
		
		String[] c1 = n1.asStringArray();
		assertEquals(1, c1.length);
		assertEquals("org", c1[0]);

		String[] c2 = n2.asStringArray();
		assertEquals(2, c2.length);
		assertEquals("jvalue", c2[0]);
		assertEquals("org", c2[1]);

		String[] c3 = n3.asStringArray();
		assertEquals(3, c3.length);
		assertEquals("www", c3[0]);
		assertEquals("jvalue", c3[1]);
		assertEquals("org", c3[2]);
	}
	
	@Test
	public void testGetComponent() {
		try {
			assertEquals("org", n1.getComponent(0));
			assertEquals("jvalue", n2.getComponent(0));
			assertEquals("www", n3.getComponent(0));
			assertEquals("org", n2.getComponent(1));
			assertEquals("jvalue", n3.getComponent(1));
			assertEquals("org", n3.getComponent(2));
		} catch (IndexOutOfBoundsException iie) {
			fail("getComponent() threw exception on valid index");
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetComponent0() {
		n0.getComponent(1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetComponent1() {
		n1.getComponent(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetComponent2() {
		n3.getComponent(17);
	}
		
	@Test
	public void testGetComponents() {
		Iterator<String> ni = n3.components().iterator();
		for (int i = 0; i < n3.getNoComponents(); i++) {
			try {
				assertEquals(ni.next(), n3.getComponent(i));
			} catch (IndexOutOfBoundsException iie) {
				fail("getComponent() throw exception on valid index");
			}
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetContextName0() {
		n0.getContextName();
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetContextName1() {
		n1.getContextName();
	}
	
	@Test
	public void testGetContextName2() {
		try {
			assertEquals(getName("jvalue"), n2.getContextName());
			assertEquals(getName("www#jvalue"), n3.getContextName());
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
			assertEquals("org", n1.getFirstComponent());
			assertEquals("jvalue", n2.getFirstComponent());
			assertEquals("www", n3.getFirstComponent());
		} catch (IndexOutOfBoundsException iie) {
			fail("getFirstComponent with first name threw exception");
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsert0() {
		n0.insert(2, "test");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsert1() {
		n3.insert(-1, "test");
	}
	
	@Test
	public void testInsert2() {
		try {
			assertEquals(getName("test"), ne.insert(0, "test"));
			assertEquals(getName("test#"), n0.insert(0, "test"));
			assertEquals(getName("test#org"), n1.insert(0, "test"));
			assertEquals(getName("jvalue#test#org"), n2.insert(1, "test"));
			assertEquals(getName("www#test#jvalue#org"), n3.insert(1, "test"));
		} catch (IndexOutOfBoundsException iie) {
			fail("insert() rejected valid index");
		};
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(!n0.isEmpty());
		assertTrue(!n1.isEmpty());
		assertTrue(!n2.isEmpty());
		assertTrue(!n3.isEmpty());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetLastComponent0() {
		ne.getLastComponent();
	}
	
	@Test
	public void testGetLastComponent1() {
		try {
			assertEquals("", n0.getLastComponent());
			assertEquals("org", n1.getLastComponent());
			assertEquals("org", n2.getLastComponent());
			assertEquals("org", n3.getLastComponent());
		} catch (IndexOutOfBoundsException iie) {
			fail("getLastComponent() rejected valid index");
		}
	}
	
	@Test
	public void testGetNoComponent() {
		assertEquals(0, ne.getNoComponents());
		assertEquals(1, n0.getNoComponents());
		assertEquals(1, n1.getNoComponents());
		assertEquals(2, n2.getNoComponents());
		assertEquals(3, n3.getNoComponents());
	}
	
	@Test
	public void testPrepend() {
		assertEquals(getName("org"), ne.prepend("org"));
		assertEquals(getName("org#"), n0.prepend("org"));
		assertEquals(getName("jvalue#org"), n1.prepend("jvalue"));
		assertEquals(getName("www#jvalue#org"), n2.prepend("www"));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove0() {
		n0.remove(1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove1() {
		n3.remove(7);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove2() {
		n2.remove(-1);
	}

	@Test
	public void testRemove3() {
		try {
			assertEquals(getName(""), n1.remove(0));
			assertEquals(getName("jvalue"), n2.remove(1));
			assertEquals(getName("www#jvalue"), n3.remove(2));
		} catch (IndexOutOfBoundsException iie) {
			fail("remove() rejected valid index");
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testReplace0() {
		ne.replace(0, "test");
	}


	@Test(expected = IndexOutOfBoundsException.class)
	public void testReplace1() {
		n1.replace(-1, "test");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testReplace2() {
		n2.replace(3, "test");
	}

	@Test
	public void testReplace3() {
		try {
			assertEquals(getName("com"), n1.replace(0, "com"));
			assertEquals(getName("jvalue#com"), n2.replace(1, "com"));
			assertEquals(getName("test#jvalue#org"), n3.replace(0, "test"));
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
	
	/**
	 * 
	 */
	protected Name getEmptyName() {
		return Name.EMPTY_NAME;
	}

}
