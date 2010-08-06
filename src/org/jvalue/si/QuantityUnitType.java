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

import org.jvalue.ValueType;
import org.jvalue.numbers.*;

/**
 * A QuanityUnitType captures the type restrictions of its instances.
 * It accepts range restrictions as well as unit restrictions.
 * Unit restrictions are a fixed unit that an instances must conform to.
 */
public class QuantityUnitType extends ValueType<QuantityUnit> {
	
	/**
	 * 
	 */
	protected NumberType<Double> quantityType;
	protected SiUnitType unitType;
	
	/**
	 * 
	 */
	public QuantityUnitType(Range<Double> range, SiUnit unit) {
		this(new NumberType<Double>(range), new SiUnitType(unit));
	}
	
	/**
	 * 
	 */
	public QuantityUnitType(NumberType<Double> quantityType, SiUnitType unitType) {
		this.quantityType = quantityType;
		this.unitType = unitType;
	}

	/**
	 * 
	 */
	@Override
	public boolean isValidInstance(QuantityUnit value) {
		return quantityType.isValidInstance(value.quantity) && unitType.isValidInstance(value.unit);
	}

}
