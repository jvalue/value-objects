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

import java.util.Iterator;
import java.util.List;

/**
 * Utility class for parsing and masking Strings.
 */
public final class NameUtil {
	
	/**
	 * Returns -1 if searched-for char is not found.
	 */
	public static int findChar(String name, int startPos, char searched, char escape) {
		boolean eFlag = false;
		for (int i = startPos; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!eFlag && c == escape) {
				eFlag = true;
				continue;
			}
			if (!eFlag && c == searched) {
				return i;
			}
			eFlag = false;
		}
		return -1;
	}
	
	/**
	 * Returns -1 if searched-for char is not found.
	 */
	public static int findChar(String name, int startPos, char searched, char escape, int noOccurrences) {
		if (noOccurrences == 0) {
			return startPos;
		}
		for (int no = 0; no < noOccurrences; no++) {
			startPos = findChar(name, startPos, searched, escape);
			if (startPos == -1) {
				return -1;
			};
			startPos++;
		}
		return --startPos;
	}
	
	/**
	 * Returns number of occurrences of searched char in name.
	 */
	public static int getNoChars(String name, char searched, char escape) {
		int no = 0; // = number of occurrences of searched
		boolean eFlag = false; // flags escaped char
		final int size = name.length();
		for (int i = 0; i < size; i++) {
			if (eFlag) {
				eFlag = false;
				continue;
			}
			if (name.charAt(i) == escape) {
				eFlag = true;
			}
			if (name.charAt(i) == searched) {
				no++;
			}
		}
		return no;
	}
	
	/**
	 * Returns name argument stripped from masking control chars.
	 */
	public static String getUnmaskedString(String name, char escapeChar) {
		boolean eFlag = false; // flags escape char
		StringBuilder result = new StringBuilder();
		final int size = name.length();
		for (int i = 0; i < size; i++) {
			char c = name.charAt(i);
			if (!eFlag && (c == escapeChar)) {
				eFlag = true;
				continue;
			}
			result.append(c);
			eFlag = false;
		}
		return result.toString();
	}

	/**
	 * Returns name argument masked using control chars.
	 */
	public static String getMaskedString(String name, char delimiter, char escape) {
		StringBuilder result = new StringBuilder();
		final int size = name.length();
		for (int i = 0; i < size; i++) {
			char c = name.charAt(i);
			if (c == delimiter) {
				result.append(escape);
			}
			if (c == escape) {
				result.append(escape);
			}
			result.append(c);
		}
		
		return result.toString();
	}
	
	/**
	 * @param components
	 * @return
	 */
	public static String getMaskedString (List<String> components, char delimiter, char escape) { 
		String ds = String.valueOf(delimiter);
		StringBuilder sb = new StringBuilder();
		Iterator<String> i = components.iterator();
		while(i.hasNext()) {
			String component = i.next();
			String maskedComponent = NameUtil.getMaskedString(component, delimiter, escape);
			sb.append(maskedComponent);
			if (!i.hasNext()) {
				sb.append(ds);
			}
		}

		return sb.toString();
	}
	
}

