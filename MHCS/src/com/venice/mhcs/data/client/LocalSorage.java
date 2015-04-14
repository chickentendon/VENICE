package com.venice.mhcs.data.client;

import com.google.gwt.core.client.EntryPoint;
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
	
	
}
