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
	protected double quantity;
	protected SiUnit unit;
	
	/**
	 * 
	 */
	public QuantityUnit(double quantity) {
		this(quantity, SiUnit.NONE);
	}
	
	/**
	 * 
	 */
	public QuantityUnit(SiUnit unit) {
		this(0.0, unit);
	}
	
	/**
	 * 
	 */
	public QuantityUnit(double quantity, SiUnit unit) {
		this.quantity = quantity;
		this.unit = unit;
	}
	
	/**
	 * 
	 */
	public boolean equals(Object otherObject) {
		if (otherObject == this) {
			return true;
		} else if ((otherObject != null) && (otherObject instanceof QuantityUnit)) {
			QuantityUnit qu = (QuantityUnit) otherObject;
			return (qu.quantity == quantity) && unit.equals(qu.unit);
		}
		return false;
	}
	
	/**
	 * @return Returns quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * @return Returns unit
	 */
	public SiUnit getUnit() {
		return unit;
	}

	/**
	 * 
	 */
	public QuantityUnit add(QuantityUnit augend) {
		assertHasSameUnit(augend);
		double result = quantity + augend.quantity;
		return new QuantityUnit(result, unit);
	}
	
	/**
	 * 
	 */
	public QuantityUnit subtract(QuantityUnit subtrahend) {
		assertHasSameUnit(subtrahend);
		double result = quantity - subtrahend.quantity;
		return new QuantityUnit(result, unit);
	}
	
	/**
	 * 
	 */
	public QuantityUnit multiply(QuantityUnit multiplier) {
		double newNumber = quantity * multiplier.quantity;
		SiUnit newSiUnit = unit.multiply(multiplier.unit);
		return new QuantityUnit(newNumber, newSiUnit);
	}
	
	/**
	 * 
	 */
	public QuantityUnit divide(QuantityUnit divisor) {
		double newNumber = quantity / divisor.quantity;
		SiUnit newSiUnit = unit.divide(divisor.unit);
		return new QuantityUnit(newNumber, newSiUnit);
	}

	/**
	 * 
	 */
	public final QuantityUnit pow(int toPower) {
		return power(toPower);
	}
	
	/**
	 * 
	 */
	public QuantityUnit power(int toPower) {
		return new QuantityUnit(Math.pow(quantity, toPower), unit.power(toPower));
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