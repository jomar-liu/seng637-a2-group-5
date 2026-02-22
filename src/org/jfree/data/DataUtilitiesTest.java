package org.jfree.data;

import static org.junit.Assert.*;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;
import java.security.InvalidParameterException;

public class DataUtilitiesTest {
	

	// ----------------------------
	// calculateColumnTotal(Values2D data, int column)
	// data: { EC1: Valid non-null Values2D, EC2: null Values2D, 
	//         EC3: Values2D with some null cells }
	//       BVA: N/A
	// column: { EC4: column < 0, EC5: 0 <= column < columnCount, 
	//           EC6: column >= columnCount }
	//         BVA: { BLB: -1, LB: 0, BUB: columnCount - 1, UB: columnCount }
	// ----------------------------
	
	// EC1 + EC5 (LB)
	@Test
    public void calculateColumnTotalForTwoValuesWithLBColumn() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(7.5));
            oneOf(values).getValue(1, 0);
            will(returnValue(-2.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals(5.0, result, .000000001d);
        mockingContext.assertIsSatisfied();

        // tear-down: NONE in this test method
    }
	
	// EC2
	@Test(expected = InvalidParameterException.class)
	public void calculateColumnTotalForNull() {
		// exercise
		DataUtilities.calculateColumnTotal(null, 0);
	}
	
	// EC3 + EC5 (BUB)
	@Test
    public void calculateColumnTotalForValuesWithNullAndBUBColumn() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getColumnCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 1);
            will(returnValue(4.0));
            oneOf(values).getValue(1, 1);
            will(returnValue(null));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 1);

        // verify
        assertEquals(4.0, result, .000000001d);
        mockingContext.assertIsSatisfied();

        // tear-down: NONE in this test method
    }
	
	// EC4 (BLB)
	@Test
    public void calculateColumnTotalForBLBColumn() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            allowing(values).getValue(with(any(int.class)), with(equal(-1)));
            will(returnValue(null));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, -1);

        // verify
        assertEquals(0.0, result, .000000001d);
        mockingContext.assertIsSatisfied();

        // tear-down: NONE in this test method
    }
	
	// EC6 (UB)
	@Test
    public void calculateColumnTotalForUBColumn() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            allowing(values).getValue(with(any(int.class)), with(equal(2)));
            will(returnValue(null));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 2);

        // verify
        assertEquals(0.0, result, .000000001d);
        mockingContext.assertIsSatisfied();

        // tear-down: NONE in this test method
    }

	// ----------------------------
	// calculateRowTotal(Values2D data, int row)
	// data: { EC1: Valid non-null Values2D, EC2: null Values2D, 
	//         EC3: Values2D with some null cells }
	//	     BVA: N/A
	// row: { EC4: row < 0, EC5: 0 <= row < rowCount, EC6: row >= rowCount }
	//	    BVA: { BLB: -1, LB: 0, BUB: rowCount - 1, UB: rowCount }
	// ----------------------------

	// EC1 + EC5 (LB)
	@Test
    public void calculateRowTotalForTwoValuesWithLBRow() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(-2.0));
            oneOf(values).getValue(0, 1);
            will(returnValue(4.0));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values, 0);

        // verify
        assertEquals(2.0, result, .000000001d);
        mockingContext.assertIsSatisfied();

        // tear-down: NONE in this test method
    }
	
	// EC2
	@Test(expected = InvalidParameterException.class)
	public void calculateRowTotalForNull() {
		// exercise
		DataUtilities.calculateRowTotal(null, 0);
	}
	
	// EC3 + EC5 (BUB)
	@Test
    public void calculateRowTotalForValuesWithNullAndBUBRow() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(2));
            oneOf(values).getValue(1, 0);
            will(returnValue(6.0));
            oneOf(values).getValue(1, 1);
            will(returnValue(null));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values, 1);

        // verify
        assertEquals(6.0, result, .000000001d);
        mockingContext.assertIsSatisfied();

        // tear-down: NONE in this test method
    }
	
	// EC4 (BLB)
	@Test
    public void calculateRowTotalForBLBRow() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(2));
            allowing(values).getValue(with(equal(-1)), with(any(int.class)));
            will(returnValue(null));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values, -1);

        // verify
        assertEquals(0.0, result, .000000001d);
        mockingContext.assertIsSatisfied();

        // tear-down: NONE in this test method
    }
	
	// EC6 (UB)
	@Test
    public void calculateRowTotalForUBRow() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(2));
            allowing(values).getValue(with(equal(2)), with(any(int.class)));
            will(returnValue(null));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values, 2);

        // verify
        assertEquals(0.0, result, .000000001d);
        mockingContext.assertIsSatisfied();

        // tear-down: NONE in this test method
    }

	// ----------------------------
	// createNumberArray(double[] data)
	// data: { EC1: valid non-null array, EC2: null array, EC3: empty array,
	//	       EC4: mixed negative/positive values, EC5: contains NaN/Infinity }
	//	     BVA: N/A
	// ----------------------------
	

	// ----------------------------
	// createNumberArray2D(double[][] data)
	// data: { EC1: valid non-null rectangular 2D array, EC2: null outer array,
	//	       EC3: contains null inner row, EC4: empty outer array,
	//	       EC5: some empty inner rows, EC6: contains NaN/Infinity }
	//	     BVA: N/A
	// ----------------------------
	

	// ----------------------------
	// getCumulativePercentages(KeyedValues data)
	// data: { EC1: valid non-null with positive values,
	//	       EC2: null data,
	//	       EC3: contains zeros,
	//	       EC4: contains negative values,
	//	       EC5: contains null item values,
	//	       EC6: sum of all values = 0 }
	//	      BVA: N/A
	// ----------------------------

}
