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
import java.util.concurrent.*;

/**
 * An SiUnit represents a unit in the Système international d'unités (former metric system).
 */
public class SiUnit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7416307373870496898L;
	
	/**
	 * Cache for sharing value objects.
	 */
	protected static ConcurrentMap<SiUnit, SiUnit> unitCache = new ConcurrentHashMap<SiUnit, SiUnit>();
	
	/**
	 * 
	 */
	public static final SiUnit NONE = getValue(new int[] {0, 0, 0, 0, 0, 0, 0}); // dimensionless
	
	/**
	 * 
	 */
	public static final SiUnit m =   getValue(new int[] {1, 0, 0, 0, 0, 0, 0}); // meter
	public static final SiUnit kg =  getValue(new int[] {0, 1, 0, 0, 0, 0, 0}); // kilogram
	public static final SiUnit s =   getValue(new int[] {0, 0, 1, 0, 0, 0, 0}); // second
	public static final SiUnit A =   getValue(new int[] {0, 0, 0, 1, 0, 0, 0}); // ampere
	public static final SiUnit K =   getValue(new int[] {0, 0, 0, 0, 1, 0, 0}); // kelvin
	public static final SiUnit mol = getValue(new int[] {0, 0, 0, 0, 0, 1, 0}); // mole
	public static final SiUnit cd =  getValue(new int[] {0, 0, 0, 0, 0, 0, 1}); // candela
	
	/**
	 * 
	 */
	public static final int m_INDEX = 0;
	public static final int kg_INDEX = 1;
	public static final int s_INDEX = 2;
	public static final int A_INDEX = 3;
	public static final int K_INDEX = 4;
	public static final int mol_INDEX = 5;
	public static final int cd_INDEX = 6;
	
	/**
	 * 
	 */
	protected int[] dimensions = new int[7];
	
	/**
	 * Thread-safe thanks to ConcurrentMap.putIfAbsent()
	 */
	public static SiUnit getValue(int[] dimensions) {
		SiUnit key = new SiUnit(dimensions);
		SiUnit result = unitCache.get(key);
		if (result == null) {
			key.dimensions = Arrays.copyOf(dimensions, 7);
			unitCache.putIfAbsent(key, key);
			result = unitCache.get(key);
		}
		
		return result;
	}
	
	/**
	 * 
	 */
	public SiUnit(int[] dimensions) {
		this.dimensions = dimensions;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private SiUnit() {
		// do nothing
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == this) {
			return true;
		} else if ((otherObject != null) && (otherObject instanceof SiUnit)) {
			SiUnit otherSiUnit = (SiUnit) otherObject;
			return Arrays.equals(dimensions, otherSiUnit.dimensions);
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return Arrays.hashCode(dimensions);
	}
	
	/**
	 * 
	 */
	public int getDimension(int index) {
		assert (index >= 0) && (index < 7);
		return dimensions[index];
	}
	
	/**
	 * 
	 */
	public int[] getDimensionsCopy() {
		return Arrays.copyOf(dimensions, 7);
	}
	
	/**
	 * 
	 */
	public SiUnit multiply(SiUnit otherSiUnit) {
		int[] result = new int[7];
		for (int i = 0; i < 7; i++) {
			result[i] = dimensions[i] + otherSiUnit.dimensions[i];
		}
		
		return new SiUnit(result);
	}

	/**
	 * 
	 */
	public SiUnit divide(SiUnit otherSiUnit) {
		int[] result = new int[7];
		for (int i = 0; i < 7; i++) {
			result[i] = dimensions[i] - otherSiUnit.dimensions[i];
		}
		
		return new SiUnit(result);
	}
	
	/**
	 * 
	 */
	public SiUnit power(int toPower) {
		int[] newDimensions = new int[7];
		for (int i = 0; i < 7; i++) {
			newDimensions[i] = dimensions[i] * toPower;
		}
		
		return new SiUnit(newDimensions);
	}
	
}