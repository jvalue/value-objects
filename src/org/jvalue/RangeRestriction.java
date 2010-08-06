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

package org.jvalue;

import org.jvalue.numbers.Range;

/**
 * A Restriction that ensures a given value falls into the value range specified by the restriction.
 */
public class RangeRestriction<T extends Comparable<T>> extends Restriction<T> {

	/**
	 * 
	 */
	protected Range<T> range;
	
	/**
	 * 
	 */
	public RangeRestriction(T lowerBound, T upperBound) {
		this(new Range<T>(lowerBound, upperBound));
	}
	
	/**
	 * 
	 */
	public RangeRestriction(Range<T> range) {
		this.range = range;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isSatisfiedBy(T value) {
		return range.includes(value);
	}
	
}
