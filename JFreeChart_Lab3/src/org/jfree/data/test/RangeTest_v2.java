package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.*;

/**
 * This class contains test cases used for testing the functionality of the
 * RangeType class from the JFreechart application
 */
public class RangeTest_v2 {

	// private field used by some methods to test the constrain() method in the
	// RangeType class
	private Range constrainRangeTest;

	// private field used by some methods to test the contains() method in the
	// RangeType class
	private Range containsRangeTest;

	@Before
	public void setUp() throws Exception {
		constrainRangeTest = new Range(-2, 7);
		containsRangeTest = new Range(-11.5, 31.5);
	}

	
	//Constructor
	
	@Test
	/**
	 * A method to test the constructor giving an exception when the second input is
	 * lower than the first.
	 */
	public void switchedInputToConstructor()
	{
		boolean exception = false;
		try {
			Range values = new Range(5, -10);
		}catch(Exception e)
		{
			exception = true;
		}
		assertEquals(exception, true);
	}
	
	// getCentralValue

	@Test
	/**
	 * A method testing the getCentralValue() method for a negative output
	 */
	public void centralValueShouldBeNegative() {
		Range values = new Range(-10, 5);
		assertEquals("The central value of -10 and 5 should be -2.5", -2.5, values.getCentralValue(), .001d);
	}

	@Test
	/**
	 * A method testing the getCentralValue() method for a zero output
	 */
	public void centralValueShouldBeZero() {
		Range values = new Range(-10, 10);
		assertEquals("The central value of -10 and 10 should be 0", 0, values.getCentralValue(), .001d);
	}

	@Test
	/**
	 * A method testing the getCentralValue() method for a positive output
	 */
	public void centralValueShouldBePositive() {
		Range values = new Range(-5, 10);
		assertEquals("The central value of -5 and 10 should be 2.5", 2.5, values.getCentralValue(), .001d);
	}

	// contains

	@Test
	/**
	 * A method testing the contains() method for a value that is smaller than the
	 * lower boundary of a Range object
	 */
	public void containsTestForLessThanLowerBound() {
		assertEquals("The range of -11.5 to 31.5 does not conatin -13.1", false, containsRangeTest.contains(-13.1));

	}

	@Test
	/**
	 * A method testing the contains() method for a value that is within the lower
	 * boundary of a Range object
	 */
	public void containsTestForOnLowerBound() {
		assertEquals("The range of -11.5 to 31.5 does conatin -11.5", true, containsRangeTest.contains(-11.5));
	}

	@Test
	/**
	 * A method testing the contains() method for a value that is within the
	 * boundary of a Range object
	 */
	public void containsTestForInBetweenBounds() {
		assertEquals("The range of -11.5 to 31.5 does conatin 0", true, containsRangeTest.contains(0));
	}

	@Test
	/**
	 * A method testing the contains() method for a value that is equal to the upper
	 * bound limit of a Range object
	 */
	public void containsTestForOnUpperBound() {
		assertEquals("The range of -11.5 to 31.5 does conatin 31.5", true, containsRangeTest.contains(31.5));
	}

	@Test
	/**
	 * A method testing the contains() method for a value that is greater than the
	 * upper bound of a Range object
	 */
	public void containsTestForMoreThanUpperBound() {
		assertEquals("The range of -11.5 to 31.5 does not conatin 41.5", false, containsRangeTest.contains(41.5));
	}

	@Test
	/**
	 * A method testing the contains() method for a value that is equal to the
	 * minimum double value set as the upper bound of a Range object
	 */
	public void containsTestMin() {
		Range minRange = new Range(0, Double.MIN_VALUE);
		assertEquals("The range of 0 to MIN_Double does conatin MIN_Double", true, minRange.contains(Double.MIN_VALUE));
	}

	@Test
	/**
	 * A method testing the contains() method for a value that is equal to the
	 * maximum double value set as the upper bound of a Range object
	 */
	public void containsTestMax() {
		Range minRange = new Range(0, Double.MAX_VALUE);
		assertEquals("The range of 0 to MAX_Double does conatin MAX_Double", true, minRange.contains(Double.MAX_VALUE));
	}

	// equals

	@Test

	/**
	 * A method testing the equals method for equality between two Range objects
	 */
	public void equalsTestForSameRange() {
		Range values1 = new Range(-10, 10);
		Range values2 = new Range(-10, 10);
		assertEquals("Both input ranges have the same bounds", true, values1.equals(values2));
	}

	@Test
	/**
	 * A method testing the equals method for inequality between two Range objects
	 * using a lower range for the expecteed result
	 */
	public void equalsTestForLowerRange() {
		Range values1 = new Range(-10, 10);
		Range values2 = new Range(-20, 10);
		assertEquals("The input ranges have different lower bounds", false, values1.equals(values2));
	}

	/**
	 * A method testing the equals method for inequality between two Range objects
	 * using a higher range for the expected result
	 */
	@Test
	public void equalsTestForHigherRange() {
		Range values1 = new Range(-10, 10);
		Range values2 = new Range(-10, 20);
		assertEquals("The input ranges have different upper bounds", false, values1.equals(values2));
	}
	
	/**
	 * A method testing that equals returns false if the input is not a Range object.
	 */
	@Test
	public void equalsFalseForNonRange() {
		Range values1 = new Range(-10, 10);
		double value2 = 5;
		assertEquals(false, values1.equals(value2));
	}

	// getLowerBound

	/**
	 * A method testing the getLowerBound() method for the correct lower bound of a
	 * Range object
	 */
	@Test
	public void getLowerBoundTest() {
		Range values = new Range(-10, 10);
		assertEquals("Lower bound should be -10", -10, values.getLowerBound(), .001d);
	}

	// getUpperBound

	/**
	 * A method testing the getUpperBound() method for the correct upper bound of a
	 * Range object
	 */
	@Test
	public void getUpperBoundTest() {
		Range values = new Range(-10, 10);
		assertEquals("Upper bound should be 10", 10, values.getUpperBound(), .001d);
	}

	// combine

	/**
	 * A method testing the combine() method for the the correct combination of two
	 * Range objects
	 */
	@Test
	public void combineTestIntersect() {
		Range test1, test2;
		Range result, expected;

		test1 = new Range(0, 10);
		test2 = new Range(5, 15);
		expected = new Range(0, 15);
		result = Range.combine(test1, test2);
		assertEquals("Combine intersecting ranges", expected, result);
	}

	/**
	 * A method testing the combine() method for the the correct combination of two
	 * Range objects with no overlap
	 */
	@Test
	public void combineTestNoOverlap() {
		Range test1, test2;
		Range result, expected;

		test1 = new Range(0, 10);
		test2 = new Range(15, 20);
		expected = new Range(0, 20);
		result = Range.combine(test1, test2);
		assertEquals("Combine non overlapping ranges", expected, result);
	}

	/**
	 * A method testing the combine() method for the the correct combination of a
	 * null Range object and an initialized Range object
	 */
	@Test
	public void combineTestInput1IsNull() {
		Range test1, test2;
		Range result, expected;

		test1 = null;
		test2 = new Range(0, 10);
		expected = new Range(0, 10);
		result = Range.combine(test1, test2);
		assertEquals("Combine initilized range with a null range", expected, result);
	}
	
	/**
	 * A method testing the combine() method for the the correct combination of an 
	 * initialized Range object and a null Range object
	 */
	@Test
	public void combineTestInput2IsNull() {
		Range test1, test2;
		Range result, expected;

		test2 = null;
		test1 = new Range(0, 10);
		expected = new Range(0, 10);
		result = Range.combine(test1, test2);
		assertEquals("Combine initilized range with a null range", expected, result);
	}
	

	// constrain

	/**
	 * A method testing the constrain() method for value within the range that is
	 * closest to the specified value. This value is within the range of the Range
	 * object
	 */
	@Test
	public void constrainTestMiddleOfRange() {
		double result;
		result = constrainRangeTest.constrain(2.5);
		assertEquals("Range of -2 to 7 should constrain a value of 2.5 to 2.5", 2.5, result, 0.001d);
	}

	/**
	 * A method testing the constrain() method for value within the range that is
	 * closest to the specified value. The value is greater than the range of the
	 * Range object
	 */
	@Test
	public void constrainTestOutsideRangeAbove() {
		double result;
		result = constrainRangeTest.constrain(8);
		assertEquals("Range of -2 to 7 should constrain a value of 8 to 7", 7, result, 0.001d);
	}

	/**
	 * A method testing the constrain() method for value within the range that is
	 * closest to the specified value.
	 */
	@Test
	public void constrainTestOutsideRangeBelow() {
		double result;
		result = constrainRangeTest.constrain(-3);
		assertEquals("Range of -2 to 7 should constrain a value of -3 to -2", -2, result, 0.001d);
	}

	/**
	 * A method testing the constrain() method for value within the range that is
	 * closest to the specified value. The value compared is equal to the lower
	 * range of the Range object
	 */
	@Test
	public void constrainTestOnLower() {
		double result;
		result = constrainRangeTest.constrain(-2);
		assertEquals("Range of -2 to 7 should constrain a value of -2 to -2", -2, result, 0.001d);
	}

	/**
	 * A method testing the constrain() method for value within the range that is
	 * closest to the specified value. The value compared is equal to the upper
	 * range of the Range object
	 */
	@Test
	public void constrainTestOnUpper() {
		double result;
		result = constrainRangeTest.constrain(7);
		assertEquals("Range of -2 to 7 should constrain a value of 7 to 7", 7, result, 0.001d);
	}

	/**
	 * A method testing the constrain() method for value within the range that is
	 * closest to the specified value using the minimum double value for comparison
	 */
	@Test
	public void constrainTestOnMin() {
		double precision = 0.00000000000000000000000000000000000000000000001d;
		Range minRange = new Range(0, Double.MIN_VALUE);
		double result = minRange.constrain(Double.MIN_VALUE);
		assertEquals("Range of 0 to Double.MIN_VALUE should constrain a value of MIN_VALUE to MIN_VALUE", Double.MIN_VALUE, result, precision);
	}

	/**
	 * A method testing the constrain() method for value within the range that is
	 * closest to the specified value using the maximum double value for comparison
	 */
	@Test
	public void constrainTestOnMax() {
		double precision = 0.00000000000000000000000000000000000000000000001d;
		Range minRange = new Range(0, Double.MAX_VALUE);
		double result = minRange.constrain(Double.MAX_VALUE);
		assertEquals("Range of 0 to Double.MAX_VALUE should constrain a value of MAX_VALUE to MAX_VALUE", Double.MAX_VALUE, result, precision);

	}
	
	
	//shift(Range, double, boolean)
	
	/**
	 * Method that tests that shift method works when Zero Crossing is set to true.
	 */
	@Test
	public void shiftWithZeroCrossing()
	{
		Range test = new Range(-10, 10);
		Range result = new Range(10, 30);
		test = Range.shift(test, 20, true);
		assertEquals(test.equals(result), true);
	}
	
	/**
	 * A method that tests whether the shift method works when setting Zero Crossing to
	 * false and trying to cross from a higher value.
	 */
	@Test
	public void shiftWithNoZeroCrossingWithValuesAboveZero() {
		Range test = new Range(-10, 10);
		Range result = new Range(-30, 0);
		test = Range.shift(test, -20, false);
		assertEquals(test.equals(result), true);
	}
	
	/**
	 * A method that tests whether the shift method works when setting Zero Crossing to
	 * false and trying to cross from a lower value.
	 */
	@Test
	public void shiftWithNoZeroCrossingWithValuesBelowZero() {
		Range test = new Range(-10, 10);
		Range result = new Range(0, 30);
		test = Range.shift(test, 20, false);
		assertEquals(test.equals(result), true);
	}
	
	/**
	 * A method that tests whether the shift method works when setting Zero Crossing to
	 * false and using zero values.
	 */
	@Test
	public void shiftWithNoZeroCrossingWithZeroValues() {
		Range test = new Range(0, 10);
		Range result = new Range(-20, 0);
		test = Range.shift(test, -20, false);
		assertEquals(test.equals(result), true);
	}
	
	// CombineIgnoringNaN
	
	@Test
	public void CombineIgnoringNaNBothNULL() {
		
	}

	//  getLength()
	/**
	 * A method that tests getLength when the range has different values.
	 */
	@Test
	public void  getLengthDifferentValues() {
		Range test = new Range(5, 10);
		assertEquals("getLength with different values", 5, test.getLength(), .001d);
	}
	
	/**
	 * A method that tests getLength when the range has the same value for upper and lower
	 */
	@Test
	public void  getLengthSameValues() {
		Range test = new Range(5, 5);
		assertEquals("getLength with same values", 0, test.getLength(), .001d);
	}
	
	 // intersects(double b0, double b1)
	@Test
	/**
	 * A method that tests intersects() when the intersect range to goes from outside to inside the range
	 */
	public void intersectsOutIn() {
		Range test = new Range(5, 10);
		assertEquals(test.intersects(0, 7), true);
	}
	
	@Test
	/**
	 * A method that tests intersects when the intersect range to goes from inside to outside the range
	 */
	public void intersectsInOut() {
		Range test = new Range(5, 10);
		assertEquals(test.intersects(7, 15), true);
	}
	
	@Test
	/**
	 * A method that tests intersects when the intersect range to goes from inside to inside the range
	 */
	public void intersectsInIn() {
		Range test = new Range(5, 10);
		assertEquals(test.intersects(7, 8), true);
	}
	
	@Test
	/**
	 * A method that tests intersects when the intersect range to goes from outside to outside below the range
	 */
	public void intersectsOutOutLow() {
		Range test = new Range(5, 10);
		assertEquals(test.intersects(1, 4), false);
	}
	
	@Test
	/**
	 * A method that tests intersects when the intersect range to goes from outside to outside below the range
	 */
	public void intersectsOutOutHigh() {
		Range test = new Range(5, 10);
		assertEquals(test.intersects(11, 15), false);
	}
	
	@Test
	/**
	 * A method that tests intersects when the intersect range to goes from outside below to outside above the range
	 */
	public void intersectsOutOutLowHigh() {
		Range test = new Range(5, 6);
		assertEquals(test.intersects(4, 7), true);
	}
	
	// interests(Range range)
	@Test
	/**
	 * A method that tests intersects(Range range) when the intersect range intersects the test range
	 */
	public void intersectsRange() {
		Range test = new Range(5, 10);
		Range intersect = new Range(7, 9);
		assertEquals(test.intersects(intersect), true);
	}
}
