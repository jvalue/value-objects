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

import org.junit.runner.*;
import org.junit.runners.*;

/**
 * Wolf sagt durch die Brust ins Auge annotiert.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses( {
	org.jvalue.names.NameTest.class,
	org.jvalue.names.NameTypeTest.class,

	org.jvalue.numbers.RangeBoundTest.class,
	org.jvalue.numbers.RangeTest.class,

	org.jvalue.si.QuantityUnitTest.class,
	org.jvalue.si.QuantityUnitTypeTest.class,
	org.jvalue.si.SiUnitTest.class
})

public class AllTests {

}
