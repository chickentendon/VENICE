package com.venice.mhcs.data.client;

public class Grid {
	public Grid(int GridLength,
				int GridHeight)
				{
					length = GridLength;
					height = GridHeight;
					grid = new Object[GridHeight][GridLength];
				}
	public Grid()
	{
		length = 100;
		height = 50;
		grid = new Object[50][100];
	}
	
	public int getLength()
	{
		return length;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public Object getItem(int xcoord,
						  int ycoord)
	{
		return grid[xcoord][ycoord];
	}
	private int length, height;
	private Object[][] grid;
}
