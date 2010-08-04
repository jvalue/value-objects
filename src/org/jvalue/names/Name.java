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

import java.io.*;
import java.util.*;

/**
 * A Name has a sequence of name components. A name component is a string.
 *
 * For a specific sub-type of Name, the number of name components may be
 * limited and subject to access restrictions. Also, the name components
 * may have special meaning, depending on the type of Name.
 * 
 * Each Name can be represented using a common format, based on delimiter
 * and control chars. In this format, the name components of the Name are
 * separated by special delimiter chars. Control chars, including the
 * delimiter char, are escaped using a special escape char. Default values
 * for the delimiter and escape char are defined below, but typically
 * method should accept arguments that override the default values.
 *
 * Also, each specific type of Name should have constructors that take
 * strings formatted using this scheme as an input.
 */
public class Name implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1156016700271883281L;

	/**
	 * 
	 */
	public static final char DEFAULT_DELIMITER_CHAR = '#';
	public static final char DEFAULT_ESCAPE_CHAR = '\\';
	
	/**
	 * Represents name as a single string
	 */
	protected String name;
	
	/**
	 * Saves number of components for performance reasons 
	 * A value of -1 implies that value has not yet been counted
	 */
	protected int noComponents =-1;
	
	/**
	 * @param name String representation of name with default component delimiter
	 */
	public Name(String name) {
		this(name, DEFAULT_DELIMITER_CHAR);
	}
	
	/**
	 * @param name String representation of name 
	 * @param delimiter Component delimiter to use when parsing string
	 */
	public Name(String name, char delimiter) {
		this(name, delimiter, DEFAULT_ESCAPE_CHAR);
	}
	
	/**
	 * @param components List of string components to create name from
	 */
	public Name(List<String> components) {
		this(NameUtil.getMaskedString(components, DEFAULT_DELIMITER_CHAR, DEFAULT_ESCAPE_CHAR));
	}
		
	/**
	 * @param name String representation of name 
	 * @param delimiter Component delimiter to use when parsing string
	 * @param escape Escape character to use when parsing string
	 */
	public Name(String name, char delimiter, char escape) {
		if ((delimiter != getDelimiterChar()) || (escape != getEscapeChar())) {
			name = switchDelEscScheme(name, delimiter, escape, getDelimiterChar(), getEscapeChar());
		}

		this.name = name;
	}
	
	/**
	 *
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == this) {
			return true;
		} else if ((otherObject != null) && (otherObject instanceof Name)) {
			Name otherName = (Name) otherObject;
			return name.equals(otherName.name);
		}
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * 
	 */
	public String asString() {
		int size = name.length();
		StringBuilder result = new StringBuilder(size);

		boolean escapeOn = false;
		char escape = getEscapeChar();
		for (int i = 0; i < size; i++) {
			char c = name.charAt(i);
			if (escapeOn) {
				result.append(c);
				escapeOn = false;
			} else if (c == escape) {
				escapeOn = true;
			} else {
				result.append(c);
			}
		}
		
		return result.toString();
	}
	
	/**
	**/
	public String asString(char delimiter) {
		if (delimiter == getDelimiterChar()) {
			return asString();
		}
		
		int size = name.length();
		StringBuilder result = new StringBuilder(size);

		boolean escapeOn = false;
		char escape = getEscapeChar();
		char oldDelimiter = getDelimiterChar();
		for (int i = 0; i < size; i++) {
			char c = name.charAt(i);
			if (escapeOn) {
				result.append(c);
				escapeOn = false;
			} else if (c == oldDelimiter) {
				result.append(delimiter);
			} else if (c == escape) {
				escapeOn = true;
			} else {
				result.append(c);
			}
		}
		
		return result.toString();
	}
	
	/**
	 * 
	 */
	public String[] asStringArray() {
		int max = getNoComponents();
		String[] sa = new String[max];
		for (int i = 0; i < max; i++) {
			sa[i] = getComponent(i);
		}
		return sa;
	}
	
	/**
	**/
	public char getDelimiterChar() {
		return DEFAULT_DELIMITER_CHAR;
	}
	
	/**
	**/
	public char getEscapeChar() {
		return DEFAULT_ESCAPE_CHAR;
	}

	/**
	 * 
	 */
	public boolean isEmpty() {
		return name.equals("");
	}
	
	/**
	 *
	 */
	public Name getContextName() {
		assertIsValidIndex(getNoComponents() - 1);
		if (getNoComponents() == 1) {
			return new Name("");
		}

		int endPos = getIndexOfEndOfComponent(getNoComponents() - 2);
		return new Name(name.substring(0, endPos));
	}
	
	/**
	 *
	 */
	public int getNoComponents() {
		if (noComponents == -1) {
			if (!isEmpty()) {
				noComponents = NameUtil.getNoChars(name, getDelimiterChar(), getEscapeChar()) + 1;
			} else {
				noComponents = 0;
			}
		}
		return noComponents;
	}
	
	/**
	 *
	 */
	public Iterable<String> components() {
		return Arrays.asList(asStringArray()); // TODO
	}
	
	/**
	 *
	 */
	public boolean hasComponent(int index) {
		return (0 <= index) && (index < getNoComponents());
	}
	
	/**
	 *
	 */
	public String getComponent(int index) {
		assertIsValidIndex(index);
		return doGetComponent(index);
	}
	
	/**
	 *
	 */
	public String getFirstComponent() {
		return getComponent(0);
	}
	
	/**
	 *
	 */
	public String getLastComponent() {
		assertIsValidIndex(getNoComponents() - 1);
		return getComponent(getNoComponents() - 1);
	}
	
	/**
	 *
	 */
	public Name append(String component) {
		return doInsert(getNoComponents(), component);
	}
	
	/**
	 *
	 */
	public Name insert(int index, String component) {
		assertIsValidIndex(index, getNoComponents()+1);
		return doInsert(index, component);
	}
	
	/**
	 *
	 */
	public Name prepend(String component) {
		if (getNoComponents() != 0) {
			return doInsert(0, component);
		} else {
			return new Name(component);
		}
	}

	/**
	 *
	 */
	public Name remove(int index) {
		assertIsValidIndex(index);
		return doRemove(index);
	}
	
	/**
	 *
	 */
	public Name replace(int index, String component) {
		assertIsValidIndex(index);
		return doReplace(index, component);
	}
	
	/**
	 * Returns -1 if index is not available.
	 */
	protected int getIndexOfStartOfComponent(int index) {
		if (index == 0) {
			return 0;
		}
		
		int pos = NameUtil.findChar(name, 0, getDelimiterChar(), getEscapeChar(), index);
		if (pos == -1) {
			return -1;
		}
		
		return pos+1;
	}
	
	/**
	 * Returns -1 if index is not available.
	 */
	protected int getIndexOfEndOfComponent(int index) {
		if (index == (getNoComponents() - 1)) {
			return name.length();
		}
		
		return getIndexOfStartOfComponent(index + 1) - 1;
	}
	
	/**
	 * 
	 */
	public Name getDefaultValue() {
		return new Name("");
	}
	
	/**
	 * @return A String masked with new delimiter and escape chars.
	 */
	protected String switchDelEscScheme(String name, char fromDel, char fromEsc, char toDel, char toEsc) {
		StringBuilder result = new StringBuilder();
		int length = name.length();
		if (length != 0) {
			for (int end = 0; end < length; end++) {
				int startPos = end;
				String component = "";
				if (startPos < name.length()) {
					int endPos = NameUtil.findChar(name, startPos, fromDel, fromEsc);
					if (endPos == -1) {
						endPos = name.length();
					}
					end = endPos;
				
					component = name.substring(startPos, endPos);
					component = NameUtil.getUnmaskedString(component, fromEsc);
				}

				result.append(NameUtil.getMaskedString(component, toDel, toEsc));
				result.append(toDel);
			}
			result.setLength(result.length() - 1);
		}
		
		return result.toString();
	}

	/**
	 *　Asserts that given index is valid
	 */
	protected void assertIsValidIndex(int i) throws IndexOutOfBoundsException {
		assertIsValidIndex(i, getNoComponents());
	}
	
	/**
	　*　Asserts that given index is valid
	　*/
	protected void assertIsValidIndex(int i, int upperLimit) throws IndexOutOfBoundsException {
		if ((i < 0) || (i >= upperLimit)) {
			String msg = String.valueOf(i) + "(of " + String.valueOf(getNoComponents()) +")";
			throw new IndexOutOfBoundsException(msg);
		}
	}
	
	/**
	 *
	 */
	protected String doGetComponent(int i) {
		return NameUtil.getUnmaskedString(doGetMaskedComponent(i), getEscapeChar());
	}
	
	/**
	 *
	 */
	protected Name doInsert(int index, String component) {
		component = NameUtil.getMaskedString(component, getDelimiterChar(), getEscapeChar());
	
		if (getNoComponents() == 0) {
			return new Name(component);
		}
	
		if (index == getNoComponents()) {
			return new Name(name + getDelimiterChar() + component);
		}
	
		component = component + getDelimiterChar();
		int pos = getIndexOfStartOfComponent(index);
		StringBuilder sb = new StringBuilder(name);
		sb.insert(pos, component);

		return new Name(sb.toString());
	}
	
	/**
	**/
	protected Name doRemove(int index) {
		int startPos = getIndexOfStartOfComponent(index);
		int endPos = getIndexOfEndOfComponent(index);
		StringBuilder sb = new StringBuilder();
	
		if (index == 0) {
			if (getNoComponents() != 1) {
				sb.append(name.substring(endPos + 1, name.length()));
			}
		} else if (index == (getNoComponents() - 1)) {
			sb.append(name.substring(0, startPos - 1));
		} else {
			sb.append(name.substring(0, startPos));
			sb.append(name.substring(endPos + 1, name.length()));
		}
		
		return new Name(sb.toString());
	}
	
	/**
	**/
	protected Name doReplace(int index, String component) {
		component = NameUtil.getMaskedString(component, getDelimiterChar(), getEscapeChar());
		int startPos = getIndexOfStartOfComponent(index);
		int endPos = getIndexOfEndOfComponent(index);
		StringBuilder sb = new StringBuilder(name.substring(0, startPos));
		sb.append(component);
		sb.append(name.substring(endPos, name.length()));
		return new Name(sb.toString());
	}
	
	/**
	**/
	protected String doGetMaskedComponent(int i) {
		int startPos = getIndexOfStartOfComponent(i);
		int endPos = getIndexOfEndOfComponent(i);
		return name.substring(startPos, endPos);
	}

}
