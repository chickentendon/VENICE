package com.venice.mhcs.data.client;

public class Grid {
	/**
	 * Constructor that is called when specific dimensions for a grid are required.
	 * @param GridLength
	 * @param GridHeight
	 */
	public Grid(int GridLength, int GridHeight)
	{
		length = GridLength;
		height = GridHeight;
		grid = new Object[GridHeight][GridLength];
	}
	/**
	 * Constructor called when no dimensions given.
	 */
	public Grid()
	{
		length = 100;
		height = 50;
		grid = new Object[height][length];
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
	public Object getItem(int xcoord, int ycoord)
	{
		Object module;
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
	public boolean setItem(int xcoord, int ycoord, Object module)			   
	{
		boolean result;
		if(xcoord < length && xcoord >= 1 && ycoord < height && ycoord >= 1)
		{
			grid[xcoord - 1][ycoord - 1] = module;
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
	private int length, height;
	private Object[][] grid;
}
