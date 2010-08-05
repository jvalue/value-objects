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
 * A ValueType is the abstraction of the various specific value type objects.
 * In general, a value type captures type restrictions and can be configured.
 */
public abstract class ValueType<T> {
	
	/**
	 * 
	 */
	public T validate(T value) throws IllegalArgumentException{
		assertIsValidInstance(value);
		return value;
	}

	/**
	 * 
	 */
	public abstract boolean isValidInstance(T value);

	/**
	 * 
	 */
	public void assertIsValidInstance(T value) throws IllegalArgumentException {
		if(!isValidInstance(value)) {
			throw new IllegalArgumentException("incompatible type");
		}
	}
	
}
