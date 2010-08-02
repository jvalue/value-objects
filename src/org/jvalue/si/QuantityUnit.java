package org.jvalue.si;

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

import java.io.*;
import java.util.*;

/**
 * A QuantityUnit combines a Quantity (double for now) with a Unit of measure.
 * For now, this package covers the SI (former metric system). Imperial measures will follow
 * Also, we'll allow for apples and oranges to be computed with.
 */
public class QuantityUnit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2558960741511212664L;
	
	/**
	 * 
	 */
	protected double number = 0.0;
	protected SiUnit unit = SiUnit.getDefaultValue();
	
	/**
	 * 
	 */
	public QuantityUnit(SiUnit unit) {
		this(0.0, unit);
	}
	
	/**
	 * 
	 */
	public QuantityUnit(double myNumber, SiUnit myUnit) {
		number = myNumber;
		unit = myUnit;
	}
	
	/**
	 * 
	 */
	public boolean equals(Object otherObject) {
		if ((otherObject != null) && (otherObject instanceof QuantityUnit)) {
			QuantityUnit qu = (QuantityUnit) otherObject;
			return (qu.number == number) && unit.equals(qu.unit);
		}
		return false;
	}
	
	/**
	 * 
	 */
	public QuantityUnit add(QuantityUnit augend) {
		assertHasSameUnit(augend);
		double result = number + augend.number;
		return new QuantityUnit(result, unit);
	}
	
	/**
	 * 
	 */
	public QuantityUnit subtract(QuantityUnit subtrahend) {
		assertHasSameUnit(subtrahend);
		double result = number - subtrahend.number;
		return new QuantityUnit(result, unit);
	}
	
	/**
	 * 
	 */
	public QuantityUnit multiply(QuantityUnit multiplier) {
		double newNumber = number * multiplier.number;
		SiUnit newSiUnit = unit.multiply(multiplier.unit);
		return new QuantityUnit(newNumber, newSiUnit);
	}
	
	/**
	 * 
	 */
	public QuantityUnit divide(QuantityUnit divisor) {
		double newNumber = number * divisor.number;
		SiUnit newSiUnit = unit.divide(divisor.unit);
		return new QuantityUnit(newNumber, newSiUnit);
	}
	
	/**
	 * 
	 */
	protected void assertHasSameUnit(QuantityUnit qu) throws IllegalArgumentException {
		if (!unit.equals(qu.unit)) {
			throw new IllegalArgumentException("incompatible unit");
		}
	}
}