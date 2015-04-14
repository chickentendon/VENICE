package com.venice.mhcs.data.client;

import java.util.ArrayList;

import org.eclipse.jetty.util.ajax.JSON;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;


public class LocalStorage implements EntryPoint {
	
	private Storage moduleStore = null;
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
	}
	
	
	/**	Takes a module and stores into local storage, giving it the key Module + ID.
	 * 
	 * @param module The module that is to be Stored
	 */
	public void Store(Module module){
		moduleStore = Storage.getLocalStorageIfSupported();
	
		String key ="module" + module.getID();
		String Mvalue = module.moduleString();
	 
		
		if (moduleStore != null){
			moduleStore.setItem(key, Mvalue);
		}
	
	}
	
	/** Retrieves a module string value from local storage.
	 * 
	 * @param key The key in local storage for the module.
	 * @return String value of the module.
	 */
	public String Read (String key){
		moduleStore = Storage.getLocalStorageIfSupported();
		
		String value = moduleStore.getItem(key);
		return value;
	}
	
	public Module toModule(String key){
		
		String value = Read(key);
		

		JSONObject JO = (JSONObject) JSONParser.parseLenient(value);
		

		
		JSONValue idValue = JO.get("id");
		JSONValue damageValue = JO.get("damage");
		JSONValue xValue = JO.get("xCoord");
		JSONValue yValue = JO.get("yCoord");
		JSONValue rotationValue = JO.get("rotations");
		
		
		String id = idValue.toString();
		String damage = damageValue.toString();
		String x = xValue.toString();
		String y = yValue.toString();
		String rotation = rotationValue.toString();
		
		id = id.replace("\"", "");
		damage = damage.replace("\"", "");
		x = x.replace("\"", "");
		y = y.replace("\"", "");
		rotation = rotation.replace("\"", "");
		
		Module rModule = new Module(id, damage, x, y, rotation);
		
		return rModule;
	}
	
	public ArrayList<Module> getModuleList(){
		
		ArrayList<Module> moduleList = new ArrayList<Module>();
		
		for (int i = 1; i < 185; i++) {
			
		}
		
		return null;
	}
	
	
}
