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
 * An SiUnit represents a unit in the Système international d'unités (former metric system).
 */
public class SiUnit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7416307373870496898L;

	/**
	 * 
	 */
	public static final int SI_UNIT_m = 0; // meter
	public static final int SI_UNIT_kg = 1; // kilogram
	public static final int SI_UNIT_s = 2; // second
	public static final int SI_UNIT_A = 3; // ampere
	public static final int SI_UNIT_K = 4; // kelvin
	public static final int SI_UNIT_mol = 5; // mole
	public static final int SI_UNIT_cd = 6; // candela
	
	/**
	 * 
	 */
	protected int[] dimensions = new int[7];
	
	/**
	 * 
	 */
	public SiUnit(int[] myDimensions) {
		dimensions = myDimensions;
	}
	
	/**
	 * 
	 */
	public static SiUnit getDefaultValue() {
		return new SiUnit();
	}
	
	/**
	 * 
	 */
	private SiUnit() {
		// do nothing
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object otherObject) {
		if ((otherObject != null) && (otherObject instanceof SiUnit)) {
			SiUnit otherSiUnit = (SiUnit) otherObject;
			return Arrays.equals(dimensions, otherSiUnit.dimensions);
		}
		return false;
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
	
}