package com.venice.mhcs.data.client;
import java.lang.String;

/**
*
*
*@author Max Ronning
*
*/
public class Module {

	//Module type - needs ModuleType declaration somewhere
	private ModuleType type;
	
	//Module ID number
	private String id;
	
	//Condition of module (damaged/undamaged)
	private String damage;
	
	//X coordinate value of module
	private String xCoord;
	
	//Y coordinate value of module
	private String yCoord;
	
	//Number of rotations to upright state
	private String rotations;
	
	
	//Declares Module type with a string for each type
	public static enum ModuleType {
	
		//Plain module
		PLAIN("Plain");
		
		//Airlock module
		AIRLOCK("Airlock");
		
		//Control module
		CONTROL("Control");
		
		//Power module
		POWER("Power");
		
		//Dormitory module
		DORMITORY("Dormitory");
		
		//Canteen module
		CANTEEN("Canteen");
		
		//Gym & relaxation module
		GYM_RELAXATION("Gym and Relaxation");
		
		//Sanitation module
		SANITATION("Sanitation");
		
		//Medical module
		MEDICAL("Medical");
		
		//Food & water module
		FOOD_WATER("Food and Water");
		
		//for string value of module type
		private String moduleString;
		
		/**
		 * 
		 * Module type constructor
		 * @param moduleString - string value associated with each module type
		 * 
		 */
		private ModuleType(String str) {
			this.moduleString = str;
		}
		
		/**
		 * definition of toString method
		 * @return moduleString associated with module type
		 */
		public String toString() {
			return moduleString;
		}
	
		
		
	}	
}