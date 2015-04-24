package com.venice.mhcs.data.client;

import java.util.ArrayList;
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
	public Module attachTop(int x, int y, Module attachMod){
		int newY = y + 1;
		attachMod.setY(Integer.toString(newY));
		return attachMod;
	}
	
	private ArrayList<Module> min1 = new ArrayList<Module>();
	private ArrayList<Module> min2 = new ArrayList<Module>();

}
