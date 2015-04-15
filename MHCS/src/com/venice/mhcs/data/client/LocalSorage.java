package com.venice.mhcs.data.client;

import java.util.ArrayList;

import org.eclipse.jetty.util.ajax.JSON;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.storage.client.Storage;


public class LocalSorage implements EntryPoint {
	
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
		String value = module.toString();
	 
		if (moduleStore != null){
			moduleStore.setItem(key, value);
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
	
	public Module toModule(String value){
		
		Module rModule = null;
		
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
		
		M
		
		rModule = new Module(id, damage, x, y, rotation, TODO);
		
		return null;
	}
	
	public ArrayList<Module> getModuleList(){
		
		ArrayList<Module> moduleList = new ArrayList<Module>();
		
		for (int i = 1; i < 185; i++) {
			
		}
		
		return null;
	}
	
	
}
