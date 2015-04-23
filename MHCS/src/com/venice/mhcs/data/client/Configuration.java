package com.venice.mhcs.data.client;

import java.util.ArrayList;
import com.venice.mhcs.data.client.Module;

public class Configuration {
	
	/*
	 * Sets min1 if it is minimum
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
	 * 
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
			//else if(test.getModuleType() == ModuleType.MEDICAL) medical++;
			else if(test.getModuleType() == ModuleType.PLAIN) plain++;
			else if(test.getModuleType() == ModuleType.POWER) power++;
			//else if(test.getModuleType() == ModuleType.SANITATION) sanitation++;
			else {}
		}
		if(airlock >= 1 && control >= 1 && power >= 1 && foodwater >= 1 && dorm >= 1 
			&& canteen >= 1 && plain >= 3) return true;
		else return false;
	}
	
	private ArrayList<Module> min1 = new ArrayList<Module>();
	private ArrayList<Module> min2 = new ArrayList<Module>();

}
