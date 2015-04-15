package com.venice.mhcs.data.client;

import com.venice.mhcs.data.client.Module;

public class Cell 
{
	public Cell(Module mod)
	{
		ID = mod.getID();
		type = mod.getModuleType().toString();
		terrain = false;
		
	}
	public Cell()
	{
		ID = "N/A";
		type = "N/A";
		terrain = true;
	}
	
	public void cellOverwrite(Cell oldCell)
	{
		this.terrain = oldCell.isTerrain();
	}
	
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
	
	
	
	
	private String type;
	private String ID;
	private boolean terrain;
}
