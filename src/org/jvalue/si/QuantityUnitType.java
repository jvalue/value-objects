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

package org.jvalue.si;

import org.jvalue.numbers.*;

/**
 * A QuanityUnitType captures the type constraints of its instances.
 * It accepts range constraints as well as unit constraints.
 * Unit constraints are a fixed unit that an instances must conform to.
 */
public class QuantityUnitType {
	
	/**
	 * 
	 */
	protected Range<Double> rangeConstraint;
	protected SiUnit unitConstraint;
	
	/**
	 * 
	 */
	public QuantityUnitType(Range<Double> range, SiUnit unit) {
		rangeConstraint = range;
		unitConstraint = unit;
	}

	/**
	 * 
	 */
	public boolean isValidInstance(QuantityUnit value) {
		return unitConstraint.equals(value.getUnit()) && 
			rangeConstraint.includes(value.getQuantity());
	}

	/**
	 * 
	 */
	public void assertIsValidInstance(QuantityUnit value) throws IllegalArgumentException {
		if(!isValidInstance(value)){
			throw new IllegalArgumentException("incompatible type");
		}
	}

}
