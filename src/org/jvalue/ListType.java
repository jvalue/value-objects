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

package org.jvalue;

import org.jvalue.numbers.Range;

/**
 * A ListType specifies type restrictions on lists.
 */
public class ListType<E> extends ValueType<Iterable<E>> {
	
	/**
	 * 
	 */
	protected Restriction<Integer> lengthRestriction;
	protected ValueType<E> elementType;

	/**
	 * 
	 */
	public ListType(int exactLength) {
		this(new ExactValueRestriction<Integer>(exactLength));
	}
	
	/**
	 * 
	 */
	public ListType(int minLength, int maxLength) {
		this(new RangeRestriction<Integer>(minLength, maxLength));
	}
	
	/**
	 * 
	 */
	public ListType(Range<Integer> range) {
		this(new RangeRestriction<Integer>(range));
	}

	/**
	 * 
	 */
	public ListType(Restriction<Integer> lengthRestriction) {
		this(lengthRestriction, null);
	}
	
	/**
	 * 
	 */
	public ListType(ValueType<E> elementType) {
		this(new NullRestriction<Integer>(), elementType);
	}

	/**
	 * 
	 */
	public ListType(Restriction<Integer> lengthRestriction, ValueType<E> elementType) {
		this.lengthRestriction = lengthRestriction;
		this.elementType = elementType;
	}

	/**
	 * 
	 */
	@Override
	public boolean isValidInstance(Iterable<E> iterable) {
		int length = 0;
		for(E element : iterable) {
			if((elementType != null) && (!elementType.isValidInstance(element))) {
				return false;
			}
			length++;
		}
		
		return lengthRestriction.isSatisfiedBy(length);
	}	
}
