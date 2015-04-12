package gridTesting;

import static org.junit.Assert.*;
import com.venice.mhcs.data.client.Grid;

import org.junit.Test;

public class GridTests {

	@Test
	public void testBasics() 
	{
		Grid grid1 = new Grid();
		grid1.setItem(1, 1, 3);
		grid1.setItem(2, 2, 20);
		
		assertTrue(grid1.getItem(1, 1).equals(3));
		assertTrue(grid1.getItem(2, 2).equals(20));
	}
	
	@Test 
	public void testErrorsGetting()
	{
		Grid grid1 = new Grid();
		grid1.setItem(1, 1, 3);
		grid1.setItem(2, 2, 20);
		assertTrue(grid1.getItem(0, 1) == (null));
		assertTrue(grid1.getItem(1, 0) == (null));
		assertTrue(grid1.getItem(101, 5) == (null));
		assertTrue(grid1.getItem(5, 51) == (null));
	}

}
