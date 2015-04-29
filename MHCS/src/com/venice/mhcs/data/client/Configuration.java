package com.venice.mhcs.data.client;

import java.util.ArrayList;
import java.util.Collections;

import com.venice.mhcs.data.client.Module;

/*
 * Class intended to store configurations for modules
 * both Minimum and Full. Stores ArrayLists of modules
 * 
 * @author Nathan Gunnarson
 * 
 */
public class Configuration {
	
	/*
	 * Sets min1 if it is minimum
	 * @param An ArrayList of Modules
	 * @return true if isMinimum and set successful, false if not
	 */
	public boolean setMinimum1(ArrayList<Module> list){
		if(isMinimum(list)){
			min1 = list;
			return true;
		}
		else return false;
	}
	
	/*
	 * Sets min2 if it is minimum
	 * @param An ArrayList of Modules
	 * @return true if isMinimum and set successful, false if not
	 */
	public boolean setMinimum2(ArrayList<Module> list){
		if(isMinimum(list)){
			min2 = list;
			return true;
		}
		else return false;
	}
	
	public ArrayList<Module> getMinimum1(){
		return min1;
	}
	
	public ArrayList<Module> getMinimum2(){
		return min2;
	}
	
	/*
	 *Tests to make sure the list of modules contains
	 *enough to make a minimum configuration
	 *@param An ArrayList of modules
	 *@return true if minimum, false if not minimum
	 */
	public boolean isMinimum(ArrayList<Module> config){
		int airlock = 0, canteen = 0, control = 0, dorm = 0, foodwater = 0, plain = 0, power = 0;
		for(int i = 0; i < config.size() ; i++){
			Module test = config.get(i);
			if(test.getModuleType() == ModuleType.AIRLOCK) airlock++;
			else if(test.getModuleType() == ModuleType.CANTEEN) canteen++;
			else if(test.getModuleType() == ModuleType.CONTROL) control++;
			else if(test.getModuleType() == ModuleType.DORMITORY) dorm++;
			else if(test.getModuleType() == ModuleType.FOOD_WATER) foodwater++;
			//else if(test.getModuleType() == ModuleType.GYM_RELAXATION) gym++;
			//else if(test.getModuleType() == ModuleType.MEDICAL) medical++;		//Commented to be used 
			else if(test.getModuleType() == ModuleType.PLAIN) plain++;				//for Full Config Test
			else if(test.getModuleType() == ModuleType.POWER) power++;
			//else if(test.getModuleType() == ModuleType.SANITATION) sanitation++;
			else {}
		}
		if(airlock >= 1 && control >= 1 && power >= 1 && foodwater >= 1 && dorm >= 1 
			&& canteen >= 1 && plain >= 3) return true;
		else return false;
	}
	
	
	/*
	 * Modifies coordinates of module to place it above another
	 * @param x the x coordinate of module to be below newly placed module
	 * @param y the y coordinate of module to be below newly placed module
	 * @return the module with modified coordinates to be above the x,y coordinates
	 */
	private Module attachTop(int x, int y, Module attachMod){
		int newY = y + 1;
		attachMod.setY(Integer.toString(newY));
		attachMod.setX(Integer.toString(x));
		return attachMod;
	}
	
	/*
	 * Modifies coordinates of module to place it below another
	 * @param x the x coordinate of module to be above newly placed module
	 * @param y the y coordinate of module to be above newly placed module
	 * @return the module with modified coordinates to be below the x,y coordinates
	 */
	private Module attachBottom(int x, int y, Module attachMod){
		int newY = y - 1;
		attachMod.setY(Integer.toString(newY));
		attachMod.setX(Integer.toString(x));
		return attachMod;
	}
	
	/*
	 * Modifies coordinates of module to place it left of another
	 * @param x the x coordinate of module to be right of newly placed module
	 * @param y the y coordinate of module to be right of newly placed module
	 * @return the module with modified coordinates to be left of the x,y coordinates
	 */
	private Module attachLeft(int x, int y, Module attachMod){
		int newX = x - 1;
		attachMod.setX(Integer.toString(newX));
		attachMod.setY(Integer.toString(y));
		return attachMod;
	}
	
	/*
	 * Modifies coordinates of module to place it right of another
	 * @param x the x coordinate of module to be left of newly placed module
	 * @param y the y coordinate of module to be left of newly placed module
	 * @return the module with modified coordinates to be right of the x,y coordinates
	 */
	private Module attachRight(int x, int y, Module attachMod){
		int newX = x + 1;
		attachMod.setX(Integer.toString(newX));
		attachMod.setY(Integer.toString(y));
		return attachMod;
	}

	private ArrayList<Module> setPlains(int x, int y, ArrayList<Module> modList){
		ArrayList<Module> pList = new ArrayList<Module>();
		
		for(int i = 0; i < modList.size(); i++){
			if(modList.get(i).getModuleType() == ModuleType.PLAIN){
				pList.add(modList.remove(i));
				i--;
			}
		}
		
		for(int i = 0; i < pList.size(); i++){
			if(i == 0){
				pList.get(i).setX(Integer.toString(x));
				pList.get(i).setY(Integer.toString(y));
			}
			else if(i == 1){
				pList.get(i).setX(Integer.toString(x+1));
				pList.get(i).setY(Integer.toString(y));
			}
			else if(i == 2){
				pList.get(i).setX(Integer.toString(x-1));
				pList.get(i).setY(Integer.toString(y));
			}
		}
		
		for(int i = 0; i < pList.size(); i++){
			modList.add(pList.get(i));
		}
		return modList;
	}
	
	private int findXSite(ArrayList<Module> minList){
		int tempX = 0;
		for(int i = 0; i < minList.size(); i++){
			tempX += Integer.parseInt(minList.get(i).getX());
		}
		return tempX/minList.size();
	}
	
	private int findYSite(ArrayList<Module> minList){
		int tempY = 0;
		for(int i = 0; i < minList.size(); i++){
			tempY += Integer.parseInt(minList.get(i).getY());
		}
		return tempY/minList.size();
	}
	
	public Configuration buildMin1(Configuration min){
		ArrayList<Module> minList = min.getMinimum1();
		int x = findXSite(minList);
		System.out.println(x);
		int y = findYSite(minList);
		System.out.println(y);

		if(min.isMinimum(minList)){
			minList = setPlains(x, y, minList);
			for(int i = 0; i < minList.size();i++){
				
				Module curMod = minList.get(i);
				
				if(curMod.getModuleType() == ModuleType.AIRLOCK){
					curMod = attachLeft(x, y, curMod);
					curMod = attachLeft(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				
				else if(curMod.getModuleType() == ModuleType.CANTEEN){
					curMod = attachLeft(x, y, curMod);
					curMod = attachTop(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.DORMITORY){
					curMod = attachTop(x, y, curMod);
				}
				else if(curMod.getModuleType() == ModuleType.CONTROL){
					curMod = attachRight(x, y, curMod);
					curMod = attachTop(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.SANITATION){
					curMod = attachRight(x, y, curMod);
					curMod = attachRight(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.POWER){
					curMod = attachRight(x, y, curMod);
					curMod = attachBottom(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.FOOD_WATER){
					curMod = attachLeft(x, y, curMod);
					curMod = attachBottom(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
			minList.remove(i);
			minList.add(i, curMod);
			}
		}
		min.setMinimum1(minList);
		return min;
	}
	
	public Configuration buildMin2(Configuration min){
		ArrayList<Module> minList = min.getMinimum2();
		int x = findXSite(minList);
		System.out.println(x);
		int y = findYSite(minList);
		System.out.println(y);

		if(min.isMinimum(minList)){
			minList = setPlains(x, y, minList);
			for(int i = 0; i < minList.size();i++){
				
				Module curMod = minList.get(i);
				
				if(curMod.getModuleType() == ModuleType.AIRLOCK){
					curMod = attachLeft(x, y, curMod);
					curMod = attachBottom(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.CANTEEN){
					curMod = attachRight(x, y, curMod);
					curMod = attachBottom(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.DORMITORY){
					curMod = attachRight(x, y, curMod);
					curMod = attachRight(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.CONTROL){
					curMod = attachTop(x, y, curMod);
				}
				else if(curMod.getModuleType() == ModuleType.SANITATION){
					curMod = attachLeft(x, y, curMod);
					curMod = attachLeft(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.POWER){
					curMod = attachLeft(x, y, curMod);
					curMod = attachTop(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
				else if(curMod.getModuleType() == ModuleType.FOOD_WATER){
					curMod = attachRight(x, y, curMod);
					curMod = attachTop(Integer.parseInt(curMod.getX()), Integer.parseInt(curMod.getY()), curMod);
				}
			minList.remove(i);
			minList.add(i, curMod);
			}
		}
		min.setMinimum2(minList);
		return min;
	}
	
	
	private ArrayList<Module> min1 = new ArrayList<Module>();
	private ArrayList<Module> min2 = new ArrayList<Module>();

}
