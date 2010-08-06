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

/**
 * This class allows to combine individual restrictions, using either AND or OR.
 *
 * @param <T>
 */
public abstract class BooleanRestriction<T> extends Restriction<T> {
	
	/**
	 * 
	 */
	public static class And<T> extends BooleanRestriction<T> {
		public And(Restriction<T> r1, Restriction<T> r2) {
			super(r1, r2);
		}
		
		/**
		 * 
		 */
		@Override
		public boolean isSatisfiedBy(T value) {
			return r1.isSatisfiedBy(value) && r2.isSatisfiedBy(value);
		}		
	}
	
	/**
	 * 
	 */
	public static class Or<T> extends BooleanRestriction<T> {
		public Or(Restriction<T> r1, Restriction<T> r2) {
			super(r1, r2);
		}
		
		/**
		 * 
		 */
		@Override
		public boolean isSatisfiedBy(T value) {
			return r1.isSatisfiedBy(value) || r2.isSatisfiedBy(value);
		}		
	}	

	/**
	 * 
	 */
	protected Restriction<T> r1;
	protected Restriction<T> r2;

	/**
	 * 
	 */
	public BooleanRestriction(Restriction<T> r1, Restriction<T> r2) {
		this.r1 = r1;
		this.r2 = r2;
	}

}
