package com.venice.mhcs.data.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.storage.client.Storage;


public class LocalStorage implements EntryPoint {
	
	private Storage moduleStore = null;
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
	}
	
	
	/**	Takes a module and stores into local storage, giving it the key venice + ID.
	 * 
	 * @param module The module that is to be Stored
	 */
	public void Store(Module module){
		moduleStore = Storage.getLocalStorageIfSupported();
	
		String key ="venice" + module.getID();
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
	/**
	 * Removes a module from Local Storage.
	 * @param id of the String of the module to be removed
	 * @return if the module was removed
	 */
	public boolean Remove(String id){
		String ID = "venice" + id;
		moduleStore.removeItem(ID);
		return true;
	}
	/**
	 * Changes a string of JSON format into a Module
	 * @param key string of JSON format
	 * @return Module filled with necessary data.
	 */
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
	
	/**
	 * Returns an up-to date ArrayList of Modules
	 * @return ArrayList
	 */
	public ArrayList<Module> getModuleList(){
		
		ArrayList<Module> moduleList = new ArrayList<Module>();
		Module tempModule = new Module();
		
		for (int i = 1; i < 185; i++) {
			if (Read("venice" + i)!= null){
				tempModule = toModule("venice" + i);
				moduleList.add(tempModule);
			}
		}
		
		return moduleList;
	}
	
	public void StoreConfig(ArrayList<Module> array, String configName){
		moduleStore = Storage.getLocalStorageIfSupported();
		
		if(moduleStore != null){
			for(int i = 0; i < array.size(); i ++){
				storeConfigModule(array.get(i), configName +array.get(i).getID());
			}
		}
	}
	
	private void storeConfigModule(Module m, String key){
		moduleStore = Storage.getLocalStorageIfSupported();
		
		if(moduleStore != null){
			moduleStore.setItem(key, m.moduleString());
		}
	}
	
	public ArrayList<Module> getStoredConfig(String configName){
		ArrayList<Module> rList = new ArrayList<Module>();
		Module tempModule = new Module();
		
		for(int i = 1; i < 185; i++){
			if (Read(configName + i)!= null){
				tempModule = toModule(configName + i);
				rList.add(tempModule);
			}
		}
		return rList;
	}
}
	