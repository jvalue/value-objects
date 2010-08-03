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

import java.io.*;

/**
 * A RangeBound represents the bound of a range. 
 * It holds a value plus a flag stating whether the bound is inclusive of that value or not.
 * Two range bounds are equal if both the value and the inclusive flag are equal.
 * The comparison logic for RangeBounds depends on their usage (as upper rsp. lower bound), therefore RangeBound is not a Comparable.
 */
public class RangeBound<T extends Comparable<T>> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4640837997902512973L;
	
	/**
	 * 
	 */
	private T value;
	private boolean inclusive = true;
	
	/**
	 * 
	 */
	public RangeBound(T newValue) {
		this(newValue, true);
	}
	
	/**
	 * 
	 */
	public RangeBound(T newValue, boolean isInclusive) {
		value = newValue;
		inclusive = isInclusive;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == this) {
			return true;
		} else if ((otherObject != null) && (otherObject instanceof RangeBound)) {
			RangeBound otherBound = (RangeBound) otherObject;
			return (value.equals(otherBound.value)) && (inclusive == otherBound.inclusive);
		} 
		return false;
	}
	
	/**
	 * 
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * @return True, if bound value is considered inclusive
	 */
	public boolean isInclusive() {
		return inclusive;
	}
	
}