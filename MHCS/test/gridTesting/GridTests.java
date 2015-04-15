package gridTesting;

import static org.junit.Assert.*;

import com.venice.mhcs.data.client.Grid;
import com.venice.mhcs.data.client.Module;

import org.junit.Test;

public class GridTests {

	@Test
	public void testIDRetreival() 
	{
		Grid grid1 = new Grid();
		
		int x1 = Integer.valueOf(module1.getX());
		int y1 = Integer.valueOf(module1.getY());
		
		int x2 = Integer.valueOf(module2.getX());
		int y2 = Integer.valueOf(module2.getY());
		
		int x3 = Integer.valueOf(module3.getX());
		int y3 = Integer.valueOf(module3.getY());
		
		grid1.setItem(x1, y1, module1);
		grid1.setItem(x2, y2, module2);
		grid1.setItem(x3, y3, module3);
		
		assertTrue(grid1.getItem(1, 1).getID().equals("23"));
		assertTrue(grid1.getItem(2, 2).getID().equals("3"));
		assertTrue(grid1.getItem(3, 3).getID().equals("14"));
	}
	
	@Test 
	public void testErrorsGetting()
	{
		Grid grid1 = new Grid();
		
		int x1 = Integer.valueOf(module1.getX());
		int y1 = Integer.valueOf(module1.getY());
		
		int x2 = Integer.valueOf(module2.getX());
		int y2 = Integer.valueOf(module2.getY());
		
		int x3 = Integer.valueOf(module3.getX());
		int y3 = Integer.valueOf(module3.getY());
		
		grid1.setItem(x1, y1, module1);
		grid1.setItem(x2, y2, module2);
		grid1.setItem(x3, y3, module3);
		
		assertTrue(grid1.getItem(0, 1) == (null));
		assertTrue(grid1.getItem(1, 0) == (null));
		assertTrue(grid1.getItem(101, 5) == (null));
		assertTrue(grid1.getItem(5, 51) == (null));
	}
	
	
	@Test
	public void testAddingArray()
	{
		Grid grid1 = new Grid();
		grid1.addArray(moduleArray);
		
		assertTrue(grid1.getItem(1, 1).getID().equals("23"));
		assertTrue(grid1.getItem(2, 2).getID().equals("3"));
		assertTrue(grid1.getItem(3, 3).getID().equals("14"));
		
	}
	
	@Test
	public void testRemovingCells()
	{
		Grid grid1 = new Grid();
		
		int x1 = Integer.valueOf(module1.getX());
		int y1 = Integer.valueOf(module1.getY());
		
		int x2 = Integer.valueOf(module2.getX());
		int y2 = Integer.valueOf(module2.getY());
		
		int x3 = Integer.valueOf(module3.getX());
		int y3 = Integer.valueOf(module3.getY());
		
		grid1.setItem(x1, y1, module1);
		grid1.setItem(x2, y2, module2);
		grid1.setItem(x3, y3, module3);
		
		grid1.removeItem(x1, y1);
		grid1.removeItem(x2, y2);
		
		assertTrue(grid1.getItem(1, 1) == null);
		assertTrue(grid1.getItem(2, 2) == null);
		assertTrue(grid1.getItem(3, 3).getID().equals("14"));
	}
	
	
	public Module module1 = new Module("23","Good", "1", "1", "2");
	public Module module2 = new Module("3", "Bad", "2", "2", "0");
	public Module module3 = new Module("14", "Moderate", "3", "3", "1");
	
	public Module[] moduleArray = {module1, module2, module3};

}
