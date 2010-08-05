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

import org.jvalue.SimpleValueType;
import org.jvalue.ExactValueRestriction;
import org.jvalue.RangeRestriction;
import org.jvalue.Restriction;

/**
 * A NumberType specifies type restrictions on number (value objects).
 * Accepts ranges, exact values, etc.
 */
public class NumberType<T extends Comparable<T>> extends SimpleValueType<T> {

	/**
	 * 
	 */
	public NumberType(T exactValue) {
		this(new ExactValueRestriction<T>(exactValue));
	}
	
	/**
	 * 
	 */
	public NumberType(T lowerBound, T upperBound) {
		this(new RangeRestriction<T>(lowerBound, upperBound));
	}
	
	/**
	 * 
	 */
	public NumberType(Range<T> range) {
		this(new RangeRestriction<T>(range));
	}

	/**
	 * 
	 */
	public NumberType(Restriction<T> restriction) {
		super(restriction);
	}

	
}
