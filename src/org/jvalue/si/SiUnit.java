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
	public static final SiUnit NONE =   new SiUnit(new int[] {0, 0, 0, 0, 0, 0, 0}); // dimensionless
	
	/**
	 * 
	 */
	public static final SiUnit m =   new SiUnit(new int[] {1, 0, 0, 0, 0, 0, 0}); // meter
	public static final SiUnit kg =  new SiUnit(new int[] {0, 1, 0, 0, 0, 0, 0}); // kilogram
	public static final SiUnit s =   new SiUnit(new int[] {0, 0, 1, 0, 0, 0, 0}); // second
	public static final SiUnit A =   new SiUnit(new int[] {0, 0, 0, 1, 0, 0, 0}); // ampere
	public static final SiUnit K =   new SiUnit(new int[] {0, 0, 0, 0, 1, 0, 0}); // kelvin
	public static final SiUnit mol = new SiUnit(new int[] {0, 0, 0, 0, 0, 1, 0}); // mole
	public static final SiUnit cd =  new SiUnit(new int[] {0, 0, 0, 0, 0, 0, 1}); // candela
	
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
	 * 
	 */
	public SiUnit(int[] myDimensions) {
		dimensions = myDimensions;
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
	public int getDimension(int index) {
		assert (index >= 0) && (index < 7);
		return dimensions[index];
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