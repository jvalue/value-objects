package org.jvalue.numbers;

import static org.junit.Assert.*;

import org.junit.*;


public class RangeTest {

	
	/**
	 * Tests bounded, inclusive ranges
	 */
	@Test
	public void testIncludes1(){
		Range<Double> range1 = 
			new Range<Double>(new RangeBound<Double>(0.0),
				      new RangeBound<Double>(10.0));
		assertTrue(range1.includes(5.0));
		assertTrue(range1.includes(0.0));
		assertTrue(range1.includes(10.0));
		assertFalse(range1.includes(-1.0));
		assertFalse(range1.includes(11.0));
	}
	
	/**
	 * Tests non-inclusive bounds
	 */
	@Test
	public void testIncludes2(){
		Range<Double> range1 = 
			new Range<Double>(new RangeBound<Double>(-10.0, false),
				      new RangeBound<Double>(-3.0, false));
		assertTrue(range1.includes(-5.0));
		assertFalse(range1.includes(-10.0));
		assertFalse(range1.includes(-3.0));
	}
	
	/**
	 * Tests unbounded ranges
	 */
	@Test
	public void testIncludes3(){
		Range<Double> range1 = 
			new Range<Double>(new RangeBound<Double>(5.0, false),null);
		assertTrue(range1.includes(17.0));
		assertFalse(range1.includes(5.0));
		assertFalse(range1.includes(0.0));
		
		Range<Double> range2 = 
			new Range<Double>(null, new RangeBound<Double>(5.0, false));
		assertTrue(range2.includes(-17.0));
		assertFalse(range2.includes(5.0));
		assertFalse(range2.includes(12.0));
		
	}
}
