package gridTesting;

import static org.junit.Assert.*;

import java.util.ArrayList;

import com.venice.mhcs.data.client.ModuleGrid;
import com.venice.mhcs.data.client.Module;

import org.junit.Test;

public class GridTests {

	@Test
	public void testIDRetreival() 
	{
		ModuleGrid grid1 = new ModuleGrid();
		
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
	public void testTypeRetreival()
	{
		ModuleGrid grid1 = new ModuleGrid();
		
		int x1 = Integer.valueOf(module1.getX());
		int y1 = Integer.valueOf(module1.getY());
		
		int x4 = Integer.valueOf(module4.getX());
		int y4 = Integer.valueOf(module4.getY());
		
		int x5 = Integer.valueOf(module5.getX());
		int y5 = Integer.valueOf(module5.getY());
		
		grid1.addTerrain(6, 6);
		
		grid1.setItem(x1, y1, module1);
		grid1.setItem(x4, y4, module4);
		grid1.setItem(x5, y5, module5);
		
		assertTrue(grid1.getItem(1, 1).getType().equals("Plain"));
		assertTrue(grid1.getItem(4, 4).getType().equals("Sanitation"));
		assertTrue(grid1.getItem(5, 5).getType().equals("Dormitory"));
		assertTrue(grid1.getItem(6, 6).getType().equals("N/A"));
	}
	@Test
	public void testTerrain()
	{
		ModuleGrid grid1 = new ModuleGrid();
		
		grid1.addTerrain(1,1);
		grid1.addTerrain(2, 2);
		grid1.addTerrain(3, 4);
		
		assertTrue(grid1.isTerrain(1, 1));
		assertTrue(grid1.isTerrain(2, 2));
		assertFalse(grid1.isTerrain(3, 3));
		assertTrue(grid1.isTerrain(3,4));
	}
	
	@Test 
	public void testErrorsGetting()
	{
		ModuleGrid grid1 = new ModuleGrid();
		
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
		ModuleGrid grid1 = new ModuleGrid();
		ArrayList<Module> moduleArray =  new ArrayList<Module>();
		moduleArray.add(module1);
		moduleArray.add(module2);
		moduleArray.add(module3);
		grid1.addArray(moduleArray);
		
		assertTrue(grid1.getItem(1, 1).getID().equals("23"));
		assertTrue(grid1.getItem(2, 2).getID().equals("3"));
		assertTrue(grid1.getItem(3, 3).getID().equals("14"));
		
	}
	
	@Test
	public void testRemovingCells()
	{
		ModuleGrid grid1 = new ModuleGrid();
		
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
	
	
	public Module module1 = new Module("23","Good", "1", "1", "2"); //Plain
	public Module module2 = new Module("3", "Bad", "2", "2", "0"); //Plain
	public Module module3 = new Module("14", "Moderate", "3", "3", "1"); //Plain
	public Module module4 = new Module("99", "Good", "4", "4", "2"); //Sanitation
	public Module module5 = new Module("66", "Bad", "5", "5", "0"); //Dormitory
	



}
