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

import org.jvalue.ValueType;
import org.jvalue.numbers.Range;

/**
 * A NameType captures the type restrictions of its instances.
 * It accepts restrictions on number of components as well as String pattern restrictions on components.
 */
public class NameType extends ValueType<Name>{

	/**
	 * 
	 */
	Range<Integer> countRestriction;
	
	/**
	 * 
	 */
	public NameType(int exactNumber) {
		this(new Range<Integer>(exactNumber));
	}

	/**
	 * 
	 */
	public NameType(int lowerBound, int upperBound) {
		this(new Range<Integer>(lowerBound, upperBound));
	}
	
	/**
	 * 
	 */
	public NameType(Range<Integer> countRestriction) {
		this.countRestriction = countRestriction;
	}
	
	/**
	 * 
	 */
	public boolean isValidInstance(Name value) {
		return countRestriction.includes(value.getNoComponents());
	}

}
