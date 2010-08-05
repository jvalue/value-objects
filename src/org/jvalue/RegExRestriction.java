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

import java.util.regex.Pattern;


/**
 * A Restriction that ensures a given value object has a specific value.
 */
public class RegExRestriction implements Restriction<String> {

	/**
	 * 
	 */
	protected Pattern pattern;
	
	/**
	 * 
	 */
	public RegExRestriction(String regex) {
		this.pattern = Pattern.compile(regex);
	}
	
	/**
	 * 
	 */
	public RegExRestriction(Pattern pattern) {
		this.pattern = pattern;
	}
	
	/**
	 * 
	 */
	public boolean isSatisfiedBy(String value) {
		return pattern.matcher(value).matches();
	}
	
}
