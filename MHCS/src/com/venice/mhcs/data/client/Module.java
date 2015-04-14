package com.venice.mhcs.data.client;
import java.lang.String;
import com.google.gwt.json.client.*;

/**
*
*
*@author Max Ronning
*
*/
public class Module {

	//Module type - needs ModuleType declaration somewhere
	private static ModuleType type;
	
	//Module ID number
	private static String id;
	
	//Condition of module (damaged/undamaged)
	private static String damage;
	
	//X coordinate value of module
	private static String xCoord;
	
	//Y coordinate value of module
	private static String yCoord;
	
	//Number of rotations to upright state
	private static String rotations;
	
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
		
		if(newXCoord >= 1 && newXCoord <= 50){
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
	public boolean setModuleType(ModuleType mt) {
		if(mt.toString().equals("Plain") || mt.toString().equals("Airlock") || mt.toString().equals("Plain") 
				|| mt.toString().equals("Power") || mt.toString().equals("Dormitory") || mt.toString().equals("Canteen")
				|| mt.toString().equals("Gym and Relaxation") || mt.toString().equals("Sanitation") || mt.toString().equals("Medical")
				|| mt.toString().equals("Food and Water")){
			type = mt;
			return true;
		}
		else return false;
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
	
		
	public final String moduleString() {
		JSONObject JO = new JSONObject();
		
		JO.put("id", new JSONString(id));
		JO.put("damage", new JSONString(damage));
		JO.put("rotations", new JSONString(rotations));
		JO.put("xCoord", new JSONString(xCoord));
		JO.put("yCoord", new JSONString(yCoord));
		
		return JO.toString();
		
	//	return id + " " + damage + " " + rotations + " " + xCoord + ", " + yCoord;
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
				
			if(newXCoord >= 1 && newXCoord <= 50){} 
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
