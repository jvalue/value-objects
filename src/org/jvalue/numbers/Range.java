package org.jvalue.numbers;

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

/**
 * A Range represents a range of values.
 * It has a lower and an upper bound, made up of RangeBound values.
 * Bound values may be null, implying no limit to that range value.
 */
public class Range<T extends Comparable<T>> {

	/**
	 * 
	 */
	protected RangeBound<T> lowerBound;
	protected RangeBound<T> upperBound;

	/**
	 * @param newLowerBound Use null to indicate unlimited
	 * @param newUpperBound Use null to indicate unlimited
	 */
	public Range(RangeBound<T> newLowerBound, RangeBound<T> newUpperBound) {
		lowerBound = newLowerBound;
		upperBound = newUpperBound;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object otherObject) {
		if ((otherObject != null) && (otherObject instanceof Range)) {
			Range otherRange = (Range) otherObject;
			return lowerBound.equals(otherRange.lowerBound) && upperBound.equals(otherRange.upperBound); 
		}
		return false;
	}
	
	/**
	 * @return True, if a lower bound has been set (= not unlimited)
	 */
	public boolean hasLowerBound() {
		return lowerBound != null;
	}
	
	/**
	 * @return Lower bound of range
	 */
	public RangeBound<T> getLowerBound() {
		return lowerBound;
	}
	
	/**
	 * @return True, if an upper bound has been set (= not unlimited)
	 */
	public boolean hasUpperBound() {
		return upperBound != null;
	}
	
	/**
	 * @return Upper bound of range
	 */
	public RangeBound<T> getUpperBound() {
		return upperBound;
	}
	
	/**
	 * @return True, if provided bound value falls within range
	 */
	public boolean includes(T value) {
		return lowerBoundHolds(value) && upperBoundHolds(value);
	}
	
	/**
	 * 
	 */
	protected boolean lowerBoundHolds(T value) {
		if(!hasLowerBound()){
			return true;
		}
		
		int comp = lowerBound.getValue().compareTo(value);
		return (comp < 0) || ((comp == 0) && lowerBound.isInclusive());
	}

	/**
	 * 
	 */
	protected boolean upperBoundHolds(T value) {
		if(!hasUpperBound()){
			return true;
		}
		
		int comp = upperBound.getValue().compareTo(value);
		return (comp > 0) || ((comp == 0) && upperBound.isInclusive());
	}

}