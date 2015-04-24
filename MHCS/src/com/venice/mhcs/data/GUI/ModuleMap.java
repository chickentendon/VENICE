package com.venice.mhcs.data.GUI;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.venice.mhcs.data.client.*;

public class ModuleMap 
{
	private static Grid g;
	private static ModuleGrid map;
	public static void initalizeMap(ModuleGrid moduleMap)
	{
		g = new Grid(50, 100);
		map = moduleMap;
		g.setSize("5000px", "2500px");
		for(int row = 0; row < g.getRowCount(); row++)
	    {
	    	for(int col = 0; col < g.getColumnCount(); col++)
	    	{
	    		g.getCellFormatter().setHeight(row, col, "50px");
    			g.getCellFormatter().setWidth(row, col, "50px");
	    	}
	    }
		g.setVisible(true);
		updateMap();
	}
	public static void updateMap()
	{
		for(int row = 0; row < g.getRowCount(); row++)
	    {
	    	for(int col = 0; col < g.getColumnCount(); col++)
	    	{
	    		if(map.getItem(col + 1, g.getRowCount() - row) == null)
	    		{
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
			  }
	    }
	}
	
	public static Grid getGrid()
	{
		return g;
	}
}
