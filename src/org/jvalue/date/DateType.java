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

package org.jvalue.date;

import java.util.Calendar;

import org.jvalue.numbers.Range;

/**
 * A DateType captures the type restrictions of its instances.
 * It accepts range restrictions on the allowed date.
 */
public class DateType {

	/**
	 * 
	 */
	protected Range<Calendar> rangeRestriction;
	
	/**
	 * 
	 */
	public DateType(Calendar lowerBound, Calendar upperBound) {
		this(new Range<Calendar>(lowerBound, upperBound));
	}
	
	/**
	 * 
	 */
	public DateType(Range<Calendar> rangeRestriction) {
		this.rangeRestriction = rangeRestriction;
	}
	
	/**
	 * 
	 */
	public boolean isValidInstance(Calendar value) {
		return rangeRestriction.includes(value);
	}

	/**
	 * 
	 */
	public void assertIsValidInstance(Calendar value) throws IllegalArgumentException {
		if(!isValidInstance(value)){
			throw new IllegalArgumentException("incompatible type");
		}
	}

}
