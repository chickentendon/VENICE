package com.venice.mhcs.data.client;

import java.util.ArrayList;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;

 public class TestCases {
	
	// private ArrayList<Module> testList = new ArrayList<Module>();
		 private ArrayList<Module> testList = new ArrayList<Module>();
		 
		 public ArrayList<Module> getTestCase(String id){
			 
			String url = "http://www.d.umn.edu/~abrooks/SomeTests.php?q=" + id;
			URL.encode(url);
			
			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
			try {
			 Request request = builder.sendRequest(null, new RequestCallback() {

			 @Override
			public void onError(Request request, Throwable exception) {
			 Window.alert("onError: Couldn't retrieve JSON");
			 }

			 @Override
			public void onResponseReceived(Request request, Response response) {
			 if (200 == response.getStatusCode()) {
			 String rt = response.getText();
			 testList = toModule(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
			 } else {
			 Window.alert("Couldn't retrieve JSON (" + response.getStatusCode()
			 + ")");
			 }
			 }
			 });
			} catch (RequestException e) {
			 Window.alert("RequestException: Couldn't retrieve JSON");
			}

			if(testList.get(3).getID().equals("1")){
				Window.alert("yea");
			}
			 return testList;
		 }

		 protected ArrayList<Module> toModule(String rt){
			 ArrayList<Module> rList = new ArrayList<Module>();
			 String sAll = rt;
			 String tempID;
			 String tempX;
			 String tempY;
			 String tempDamage;
			 String tempRotation;
			 JSONArray jA = (JSONArray)JSONParser.parseLenient(sAll);
			 
			 
			 JSONNumber jN;
			 JSONString jS;
			 double d;
			 for(int i = 0; i < jA.size(); i++){
				 JSONObject jO = (JSONObject)jA.get(i);
				 jN = (JSONNumber) jO.get("code");
				 d = jN.doubleValue();
				 tempID = Double.toString(d);
				 jS = (JSONString) jO.get("status");
				 tempDamage = jS.stringValue();
				 jN = (JSONNumber) jO.get("turns");
				 d = jN.doubleValue();
				 tempRotation = Double.toString(d);
				 jN = (JSONNumber) jO.get("X");
				 d = jN.doubleValue();
				 tempX = Double.toString(d);
				 jN = (JSONNumber) jO.get("Y");
				 d = jN.doubleValue();
				 tempY = Double.toString(d);
				 if (tempDamage == "undamaged"){
					 tempDamage = "Undamaged";
				 }
				 
				 rList.add(new Module(tempID, tempDamage, tempX, tempY, tempRotation));
			 }
			 return rList;
		 }
	 
 
}
