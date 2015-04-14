package com.venice.mhcs.data.client;

import com.venice.mhcs.data.client.Module;

public class Cell 
{
	public Cell(Module mod)
	{
		ID = mod.getID();
		type = mod.getModuleType().toString();
		
	}
	public Cell()
	{
		ID = "N/A";
		type = "Terrain";
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getID()
	{
		return ID;
	}
	
	private String type;
	private String ID;
}
