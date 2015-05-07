package com.venice.mhcs.data.client;

import com.venice.mhcs.data.client.Module;

public class Cell 
{
	/**
	 * creates a cell based on data from the module given
	 * @param mod
	 */
	public Cell(Module mod)
	{
		ID = mod.getID();
		type = mod.getModuleType().toString();
		terrain = false;
		
	}
	/**
	 * creates a cell which is set to terrain because no module was given
	 */
	public Cell()
	{
		ID = "N/A";
		type = "N/A";
		terrain = true;
	}
	
	/**
	 * creates a new cell given the data that was in the old cell, this is done to preserve terrain
	 * @param oldCell
	 */
	public void cellOverwrite(Cell oldCell)
	{
		this.terrain = oldCell.isTerrain();
	}
	
	/**
	 * removes a module from the given cell, done to preserve terrain
	 */
	public void clear()
	{
		ID = "N/A";
		type = "N/A";
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public boolean isTerrain()
	{
		return terrain;
	}
	
	public void setTerrain(boolean value)
	{
		terrain = value;
	}
	
	private String type;
	private String ID;
	private boolean terrain;
}
