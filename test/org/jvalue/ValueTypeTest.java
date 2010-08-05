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

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

import org.jvalue.date.DateType;
import org.jvalue.numbers.Range;
import org.jvalue.si.QuantityUnit;
import org.jvalue.si.QuantityUnitType;
import org.jvalue.si.SiUnit;

/**
 * TestCase for ValueType abstraction.
 */
public class ValueTypeTest {
	
	/**
	 * 
	 */
	public static class Movie {
		
		/**
		 * 
		 */
		protected Calendar releaseDate;
		protected QuantityUnit length;

		/**
		 * 
		 */
		static protected DateType releaseDateType = getMovieReleaseDateType();
		static protected QuantityUnitType lengthType = new QuantityUnitType(new Range<Double>(0.0, null), SiUnit.s);
		
		/**
		 * Soon to be ... manager.getValueType("com.biz.app.Movie.releaseDate");
		 */
		protected static DateType getMovieReleaseDateType() {
			Calendar firstMovieDate = Calendar.getInstance();
			firstMovieDate.set(1890, 1, 1);
			Range<Calendar> movieDateRange = new Range<Calendar>(firstMovieDate, null);
			return new DateType(movieDateRange);
		}
		
		/**
		 * 
		 */
		public Movie(Calendar releaseDate, QuantityUnit length) {
			this.releaseDate = releaseDateType.validate(releaseDate);
			this.length = lengthType.validate(length);
		}
		
		/**
		 * 
		 */
		public Calendar getReleaseDate() {
			return releaseDate;
		}
		
		/**
		 * 
		 */
		public void setReleaseDate(Calendar releaseDate) {
			this.releaseDate = releaseDateType.validate(releaseDate);
		}
		
		/**
		 * 
		 */
		public QuantityUnit getLength() {
			return length;
		}
		
		/**
		 * 
		 */
		public void setLength(QuantityUnit length) {
			this.length = lengthType.validate(length);
		}
		
	}
	
	/**
	 * 
	 */
	protected Movie strangeDays;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		Calendar releaseDate = Calendar.getInstance();
		releaseDate.set(1995, 10, 13); // wanna puke
		strangeDays = new Movie(releaseDate, new QuantityUnit(145 * 60, SiUnit.s));
	}

	/**
	 * 
	 */
	@Test
	public void testSimpleMovie() {
		assertTrue(strangeDays.getLength().getQuantity() == 145.0 * 60);
	}
	
	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testReleaseDate() {
		Calendar noMovieReleaseDate = Calendar.getInstance();
		noMovieReleaseDate.set(1, 1, 1);
		strangeDays.setReleaseDate(noMovieReleaseDate);
	}
	
}
