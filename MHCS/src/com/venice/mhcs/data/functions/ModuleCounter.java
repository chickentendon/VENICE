package com.venice.mhcs.data.functions;
import java.util.ArrayList;

import com.venice.mhcs.data.client.*;

public class ModuleCounter 
{
	public void initCount(ArrayList<Module> modules, ModuleGrid map)
	{
		airlock = 0;
		canteen = 0;
		control = 0;
		dorm = 0;
		foodwater = 0;
		plain = 0;
		power = 0;
		gym = 0;
		medical = 0;
		sanitation = 0;
		
		for(int i = 0; i < modules.size() ; i++){
			Module test = modules.get(i);
			if(!map.isTerrain(Integer.valueOf(test.getX()), Integer.valueOf(test.getY())))
			{
				if(test.getModuleType() == ModuleType.AIRLOCK) airlock++;
				else if(test.getModuleType() == ModuleType.CANTEEN) canteen++;
				else if(test.getModuleType() == ModuleType.CONTROL) control++;
				else if(test.getModuleType() == ModuleType.DORMITORY) dorm++;
				else if(test.getModuleType() == ModuleType.FOOD_WATER) foodwater++;
				else if(test.getModuleType() == ModuleType.GYM_RELAXATION) gym++;
				else if(test.getModuleType() == ModuleType.MEDICAL) medical++;		//Commented to be used 
				else if(test.getModuleType() == ModuleType.PLAIN) plain++;				//for Full Config Test
				else if(test.getModuleType() == ModuleType.POWER) power++;
				else if(test.getModuleType() == ModuleType.SANITATION) sanitation++;
				else {}
				
				totalX =+ (int) Integer.valueOf(test.getX());
				totalY =+ (int) Integer.valueOf(test.getY());
				moduleCount++;
			}
		}
	}
	
	public int getAirlock()
	{
		return airlock;
	}
	
	public int getCanteen()
	{
		return canteen;
	}
	
	public int getControl()
	{
		return control;
	}
	
	public int getDorm()
	{
		return dorm;
	}
	
	public int getFoodWater()
	{
		return foodwater;
	}
	
	public int getPlain()
	{
		return plain;
	}
	
	public int getPower()
	{
		return power;
	}
	
	public int getGym()
	{
		return gym;
	}
	
	public int getMedical()
	{
		return medical;
	}
	
	public int getSanitation()
	{
		return sanitation;
	}
	
	public int getAverageX()
	{
		return totalX / moduleCount;
	}
	
	public int getAverageY()
	{
		return totalY / moduleCount;
	}
	
	public int moduleCount()
	{
		return moduleCount;
	}
	
	public boolean isMinimum()
	{
		return (airlock >= 1 && control >= 1 && power >= 1 && foodwater >= 1 && dorm >= 1 
				&& canteen >= 1 && plain >= 3);
	}
	
	
	private int airlock, canteen, control, dorm, foodwater, plain, power, gym, medical, sanitation;
	private int totalX, totalY, moduleCount;
}
