package com.venice.mhcs.data.GUI;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.venice.mhcs.data.GUI.ModuleMap;
import com.venice.mhcs.data.GUI.loginTab;
import com.venice.mhcs.data.GUI.moduleTab;
import com.venice.mhcs.data.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class homeTab  {
	private static VerticalPanel tommyPC = new VerticalPanel();
	static VerticalPanel vp = new VerticalPanel();
	public static AbsolutePanel homePanel = new AbsolutePanel();
	static Image nasa = new Image("images/nasa.png");
	static Image esa = new Image("images/esa.jpg");
	static FlowPanel space = new FlowPanel();
	static Label header = new Label("Mars Habitat Control System");
	Label version = new Label("Version 1.2");
	
	public static void start(){
		startFeed();
	}
	
	public static VerticalPanel startFeed() {
	
	String url =
				"http://api.wunderground.com/api/9d52d4b7aab6ed63/conditions/q/55812.json";
				 url = URL.encode(url);
			// Send request to server and catch any errors.
			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
			try {
			 Request request = builder.sendRequest(null, new RequestCallback() {
		
			 public void onError(Request request, Throwable exception) {
				 Window.alert("onError: Couldn't retrieve JSON");
				 }
			
				 public void onResponseReceived(Request request, Response response) {
					 if (200 == response.getStatusCode()) {
					 String rt = response.getText();
					 vp = update(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
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
		
			return vp;
	}


	public static VerticalPanel update(String rt) {
		
		 //vp.add(new Label(rt)); //TO VIEW
	
		 String sAll = rt;
		 JSONObject jA =
		 (JSONObject)JSONParser.parseLenient(sAll);
		 JSONValue jTry = jA.get("current_observation");
	
		 JSONObject jB =
		 (JSONObject)JSONParser.parseLenient(jTry.toString());
		 JSONValue temp = jB.get("temp_c");
		 JSONValue visibility = jB.get("visibility_km");
	
		 String sTemp = "Temp : " + temp.toString() + " °C";
		 String sVisibility = "Visibility : " + visibility.toString() + " km";
		 Image logo = new Image("images/weatherlogo.jpg");
		 vp.add(logo);
		 vp.add(new Label(sTemp)); //TO VIEW
		 vp.add(new Label(sVisibility)); //TO VIEW

		 return vp;
	}
	
	public static AbsolutePanel getMain() {
		start();
		header.getStylePrimaryName("bigText");
		space.setHeight("200px");
		space.setWidth("100px");
		vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tommyPC.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tommyPC.add(header);
		tommyPC.add(vp);
		tommyPC.add(space);
		tommyPC.add(nasa);
		tommyPC.add(esa);
		homePanel.add(tommyPC);
		homePanel.getElement().setAttribute("align", "center");
		
		return homePanel;
	}

}