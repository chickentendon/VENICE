package com.venice.mhcs.data.client;

import java.util.ArrayList;

public class ModuleGrid {
	/**
	 * Constructor that is called when specific dimensions for a grid are required.
	 * @param GridLength
	 * @param GridHeight
	 */
	public ModuleGrid(int GridLength, int GridHeight)
	{
		length = GridLength;
		height = GridHeight;
		grid = new Cell[GridHeight][GridLength];
	}
	/**
	 * Constructor called when no dimensions given.
	 */
	public ModuleGrid()
	{
		length = 100;
		height = 50;
		grid = new Cell[height][length];
	}
	
	/**
	 * Function that returns the length of the grid.
	 * @return length
	 */
	public int getLength()
	{
		return length;
	}
	
	/**
	 * Function that returns the height of the grid.
	 * @return height
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * returns the object at the given dimensions, returns null if out-of-bounds or doesn't exist
	 * @param xcoord
	 * @param ycoord
	 * @return object 
	 */
	public Cell getItem(int xcoord, int ycoord)
	{
		Cell module;
		if(xcoord < length && xcoord >= 1 && ycoord < height && ycoord >= 1)
		{
			module = grid[xcoord - 1][ycoord - 1];
		}
		else
		{
			module = null;
		}
		return module;
	}
	
	/**
	 * Function that sets the object in the grid to the given object at the given coordinates.
	 * @param xcoord
	 * @param ycoord
	 * @param module
	 * @return boolean 
	 */
	public boolean setItem(int xcoord, int ycoord, Module module)			   
	{
		boolean result;
		Cell cell = new Cell(module);
		if(xcoord < length && xcoord >= 1 && ycoord < height && ycoord >= 1)
		{
			if(grid[xcoord - 1][ycoord - 1] == null)
			{
				grid[xcoord - 1][ycoord - 1] = cell;
				result = true;
			}
			else
			{
				Cell oldCell = grid[xcoord - 1][ycoord - 1];
				cell.cellOverwrite(oldCell);
				grid[xcoord - 1][ycoord - 1] = cell;
				result = true;
			}
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	public boolean removeItem(int xcoord, int ycoord)			   
	{
		boolean result;
		if(xcoord < length && xcoord >= 1 && ycoord < height && ycoord >= 1)
		{
			if(grid[xcoord - 1][ycoord-1].isTerrain())
			{
				grid[xcoord - 1][ycoord - 1].clear();
			}
			else
			{
				grid[xcoord - 1][ycoord - 1] = null;
			}
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	public void addArray(ArrayList<Module> modules)
	{
		int size = modules.size();
		for(int i = 0; i < size; i++)
		{
			Module curModule = modules.get(i);
			Cell curCell = new Cell(curModule);
			int modX = (int) Integer.valueOf(curModule.getX());
			int modY = (int) Integer.valueOf(curModule.getY());
			
			grid [modX - 1][modY - 1] = curCell;
		}
	}
	
	public boolean isTerrain(int xcoord, int ycoord)
	{
		boolean result;
		if(grid[xcoord - 1][ycoord - 1] != null)
		{
			result = grid[xcoord - 1][ycoord - 1].isTerrain();
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	public void addTerrain(int xcoord, int ycoord)
	{
		if(grid[xcoord - 1][ycoord - 1] != null)
		{
			grid[xcoord - 1][ycoord - 1].setTerrain(true);
		}
		else
		{
			grid[xcoord - 1][ycoord - 1] = new Cell();
		}
	}
	
	public boolean removeTerrain(int xcoord, int ycoord)
	{
		boolean result;
		if(grid[xcoord - 1][ycoord - 1] == null)
		{
			result = false;
		}
		else if(grid[xcoord - 1][ycoord - 1].getID().equals("N/A"))
		{
			grid[xcoord - 1][ycoord - 1] = null;
			result = true;
		}
		else
		{
			grid[xcoord - 1][ycoord - 1].setTerrain(false);
			result = true;
		}
		return result;
	}
	
	//TODO DISTANCE METHOD
	public int distance(int xCoord1, int yCoord1, int xCoord2, int yCoord2)
	{
		return 0;
	}
	
	
	private int length, height;
	private Cell[][] grid;
}
