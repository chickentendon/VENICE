package com.venice.mhcs.data.client;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class JSONsubvertSOP {
	
	public void onModuleLoad() {
		
		String url =
				"http://api.wunderground.com/api/9d52d4b7aab6ed63/conditions/q/55812.json";
				 url = URL.encode(url);
			// Send request to server and catch any errors.
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
					 update(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
					 } else {
					 Window.alert("Couldn't retrieve JSON (" + response.getStatusCode()
					 + ")");
					 }
				 }
			 });
			} 
			catch (RequestException e) {
				Window.alert("RequestException: Couldn't retrieve JSON");
			}
		}
	
	public void update(String rt) {
		 VerticalPanel vp = new VerticalPanel();
		 //vp.add(new Label(rt)); //TO VIEW

		 String sAll = rt;
		 JSONObject jA =
		 (JSONObject)JSONParser.parseLenient(sAll);
		 JSONValue jTry = jA.get("current_observation");

		 JSONObject jB =
		 (JSONObject)JSONParser.parseLenient(jTry.toString());
		 JSONValue temp = jB.get("temp_c");
		 JSONValue visibility = jB.get("visibility_km");

		 String sTemp = temp.toString();
		 String sVisibility = visibility.toString();

		 vp.add(new Label(sTemp)); //TO VIEW
		 vp.add(new Label(sVisibility)); //TO VIEW
		 RootLayoutPanel.get().add(vp);
	}
	
}
