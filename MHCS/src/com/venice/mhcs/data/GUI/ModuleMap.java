package com.venice.mhcs.data.GUI;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.venice.mhcs.data.client.*;

public class ModuleMap 
{
	private static Grid g;
	/**
	 * creates a new map with the given modules
	 * @param moduleMap
	 */
	public static void initalizeMap(ModuleGrid moduleMap)
	{
		g = new Grid(50, 100);
		g.setSize("5000px", "2500px");
		g.setBorderWidth(1);
		for(int row = 0; row < g.getRowCount(); row++)
	    {
	    	for(int col = 0; col < g.getColumnCount(); col++)
	    	{
	    		g.getCellFormatter().setHeight(row, col, "50px");
    			g.getCellFormatter().setWidth(row, col, "50px");
	    	}
	    }
		g.setVisible(true);
		updateMap(moduleMap);
	}
	/**
	 * updates the map given a module map
	 * @param moduleMap
	 */
	public static void updateMap(ModuleGrid moduleMap)
	{
		ModuleGrid map = moduleMap;
		g.clear();
		for(int row = 0; row < g.getRowCount(); row++)
	    {
	    	for(int col = 0; col < g.getColumnCount(); col++)
	    	{
	    		if(map.getItem(col + 1, g.getRowCount() - row) == null)
	    		{
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).isTerrain())
	    		{
	    			g.setWidget((row), col, new Image("images/X-Terrain.png"));
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).getType().equals("Plain"))
	    		{
	    			g.setWidget((row), col, new Image("images/Plain.jpg"));
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).getType().equals("Airlock"))
	    		{
	    			g.setWidget((row), col, new Image("images/Airlock.jpg"));
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).getType().equals("Canteen"))
	    		{
	    			g.setWidget((row), col, new Image("images/Canteen.jpg"));
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).getType().equals("Control"))
	    		{
	    			g.setWidget((row), col, new Image("images/Control.jpg"));
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).getType().equals("Dormitory"))
	    		{
	    			g.setWidget((row), col, new Image("images/Dormitory.jpg"));
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).getType().equals("Food and Water"))
	    		{
	    			g.setWidget((row), col, new Image("images/Food.jpg"));
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).getType().equals("Gym and Relaxation"))
	    		{
	    			g.setWidget((row), col, new Image("images/Gym.jpg"));
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).getType().equals("Power"))
	    		{
	    			g.setWidget((row), col, new Image("images/Power.jpg"));
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).getType().equals("Sanitation"))
	    		{
	    			g.setWidget((row), col, new Image("images/Sanitation.jpg"));
	    		}
	    		else if(map.getItem(col + 1, g.getRowCount() - row).getType().equals("Medical"))
	    		{
	    			g.setWidget((row), col, new Image("images/Medical.jpg"));
	    		}
			  }
	    }
	}
	
	public static Grid getGrid()
	{
		return g;
	}
}
