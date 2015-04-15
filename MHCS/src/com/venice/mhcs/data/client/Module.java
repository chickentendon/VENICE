package com.venice.mhcs.data.client;
import java.lang.String;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
*
*
*@author Max Ronning, Nathan Gunnarson, Thomas Geeseman
*
*/
public class Module {

	//Module type - needs ModuleType declaration somewhere
	private  ModuleType type;
	
	//Module ID number
	private  String id;
	
	//Condition of module (damaged/undamaged)
	private  String damage;
	
	//X coordinate value of module
	private  String xCoord;
	
	//Y coordinate value of module
	private  String yCoord;
	
	//Number of rotations to upright state
	private  String rotations;
	
	public Module(){
		
	}
	
	public Module(String id, String damage, String xCoord, String yCoord, String rotations){
		setID(id);
		setDamage(damage);
		setX(xCoord);
		setY(yCoord);
		setRotation(rotations);
		setModuleType(id);
	}
	
	/**
	 * Getter for module ID
	 * @return id
	*/
	public String getID() {
		return id;
	}
		
	/**
	 * Getter for module damage
	 * @return damage
	 */
	public String getDamage() {
		return damage;
	}
		
	/**
	 * Getter for xCoord
	 * @return
	 */
	public String getX() {
		return xCoord;
	}
		
	/**
	 * Getter for yCoord
	 * @return yCoord
	 */
	public String getY() {
		return yCoord;
	}
		
	/**
	 * Getter for module type
	 * @return type
	 */
	public ModuleType getModuleType() {
		return type;
	}
		
	/**
	 * Getter for module rotations
	 * @return rotations
	 */
	public String getRotation() {
		return rotations;
	}
	
	
	/**
	 * Setter for ID
	 * @return true if set was successful, false if set was unsuccessful
	 */
	public boolean setID(String s) {
		int newId = Integer.parseInt(s);
		
		if(newId>=1 && newId<=40 || newId>=61 && newId<=80 || newId >= 91 && newId<=100 || 
				newId>=111 && newId<=120 || newId>=131 && newId<=134 || newId>=141 && newId<= 144 ||
				newId>=151 && newId<=154 || newId>=161 && newId<=164 || newId>=171 && newId<=174 ||
				newId>=181 && newId<=184){
			id = s;
			return true;
		}
		else return false;
	}
		
	/**
	 * Setter for module damage
	 * @return true if successful, false if unsuccessful
	 */
	public boolean setDamage(String s) {
		if(s.equals("Good") || s.equals("Moderate") || s.equals("Bad")){
			damage = s;
			return true;
		}
		else return false;
	}
		
	/**
	 * Setter for xCoord
	 * 
	 */
	public boolean setX(String s) {
		int newXCoord = Integer.parseInt(s);
		
		if(newXCoord >= 1 && newXCoord <= 100){
			xCoord = s;
			return true;
		} 
		else return false;
	}
		
	/**
	 * Setter for yCoord
	 * 
	 */
	public boolean setY(String s) {
		int newYCoord = Integer.parseInt(s);
		
		if(newYCoord >= 1 && newYCoord <= 50){
			yCoord = s;
			return true;
		}
		else return false;
	}
		
	/**
	 * Setter for module type
	 * 
	 */
	public boolean setModuleType(String Id) {
		boolean rbool = true;
		int IdNum = Integer.parseInt(Id);
		
		if (IdNum >= 0 && IdNum <= 40){
			type = ModuleType.PLAIN; 
		}
		else if(IdNum >= 61 && IdNum <= 80){
			type = ModuleType.DORMITORY;
		}
		else if(IdNum >= 91 && IdNum <= 100){
			type = ModuleType.SANITATION;
		}
		else if(IdNum >= 111 && IdNum <= 120){
			type = ModuleType.FOOD_WATER;
		}
		else if(IdNum >= 131 && IdNum <= 134){
			type = ModuleType.GYM_RELAXATION;
		}
		else if(IdNum >= 141 && IdNum <= 144){
			type = ModuleType.CANTEEN;
		}
		else if(IdNum >= 151 && IdNum <= 154){
			type = ModuleType.POWER;
		}
		else if(IdNum >= 161 && IdNum <= 164){
			type = ModuleType.CONTROL;
		}
		else if(IdNum >= 171 && IdNum <= 174){
			type = ModuleType.AIRLOCK;
		}
		else if(IdNum >= 181 && IdNum <= 184){
			type = ModuleType.MEDICAL;
		}
		else{
			rbool = false;
		}
		
			return rbool;
	}
		
	/**
	 * Setter for module rotations
	 * 
	 */
	public boolean setRotation(String s) {
		int newRotations = Integer.parseInt(s);
		
		if(newRotations == 0 || newRotations == 1 || newRotations == 2){
			rotations = s;
			return true;
		}
		else return false;
	}
	
		
	public String moduleString() {
		JSONObject JO = new JSONObject();
		
		JO.put("id", new JSONString(id));
		JO.put("damage", new JSONString(damage));
		JO.put("rotations", new JSONString(rotations));
		JO.put("xCoord", new JSONString(xCoord));
		JO.put("yCoord", new JSONString(yCoord));
		
		return JO.toString();
		
		
	}
		
	/**
	 * Tests module variables to ensure it is consistent with module rules
	 * @return true if module adheres to rules, false if module breaks a rule
	 * @throws RuntimeException 
	 */
	
	public boolean isConsistent(){
		int newId = Integer.parseInt(id);
		int newXCoord = Integer.parseInt(xCoord);
		int newYCoord = Integer.parseInt(yCoord);
		int newRotations = Integer.parseInt(rotations);
		try{
			if(newId>=1 && newId<=40 || newId>=61 && newId<=80 || newId >= 91 && newId<=100 || 
			newId>=111 && newId<=120 || newId>=131 && newId<=134 || newId>=141 && newId<= 144 ||
			newId>=151 && newId<=154 || newId>=161 && newId<=164 || newId>=171 && newId<=174 ||
			newId>=181 && newId<=184){}
			else throw new RuntimeException("ID number " + id + " is out of range");
			
			if(damage.equals("Good") || damage.equals("Moderate") || damage.equals("Bad")){}
			else throw new RuntimeException(damage + " is not a valid condition");
				
			if(newXCoord >= 1 && newXCoord <= 100){} 
			else throw new RuntimeException("Coordinates " + xCoord + "," + yCoord + " are out range");
				
			if(newYCoord >= 1 && newYCoord <= 50){}
			else throw new RuntimeException("Coordinates " + xCoord + "," + yCoord + " are out range");
			
			if(newRotations == 0 || newRotations == 1 || newRotations == 2){}
			else throw new RuntimeException(rotations + " is not a valid number of rotations");
				
			if(type.toString().equals("Plain") || type.toString().equals("Airlock") || type.toString().equals("Plain") 
			|| type.toString().equals("Power") || type.toString().equals("Dormitory") || type.toString().equals("Canteen")
			|| type.toString().equals("Gym and Relaxation") || type.toString().equals("Sanitation") || type.toString().equals("Medical")
			|| type.toString().equals("Food and Water")){}
			else throw new RuntimeException("The type of module you have entered is invalid, try again.");
				
			return true;
		}
		catch(RuntimeException e){
			System.out.println(e.getMessage());	
			return false;
		}
	}
}	
