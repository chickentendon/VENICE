package com.venice.mhcs.data.GUI;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.venice.mhcs.data.client.MHCS;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class homeTab  {
	private static VerticalPanel tommyPC = new VerticalPanel();
	static VerticalPanel vp = new VerticalPanel();

	public static AbsolutePanel homePanel = new AbsolutePanel();
	static Image nasa = new Image("images/nasa.png");
	static Image esa = new Image("images/esa.jpg");
	static Image mars = new Image("images/mars1");
	static FlowPanel space = new FlowPanel();
	static Label header = new Label("Mars Habitat Control System");
	Label version = new Label("Version 1.2");
	static int count = 120;
	static Label countDown = new Label();
    static Button b = new Button("Reset Calibration Timer");
	
	static Timer tenDay = new Timer() {
		  @Override
		public void run() {
		        countDown.setText("Time remaining: " + Integer.toString(count) + " seconds");
		        count--;
		        if(count==0) {
		            Window.alert("Calibrate Rover!");
		            b.setEnabled(true);
		            MHCS.getP().selectTab(0);
		            this.cancel(); 
		        }
		  }
	};
		  


	
		  
		
	
	public static void start(){
		startFeed();
	}
	
	public static VerticalPanel startFeed() {
	
	String url =
				"http://api.wunderground.com/api/9d52d4b7aab6ed63/astronomy/conditions/q/55812.json";
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
		 
		 JSONObject jA = (JSONObject)JSONParser.parseLenient(sAll);

		 
		 JSONValue jSun = jA.get("sun_phase");
		 
		 JSONValue jTry = jA.get("current_observation");
		 
		 JSONObject astro = (JSONObject)JSONParser.parseLenient(jSun.toString());
		 JSONObject jB = (JSONObject)JSONParser.parseLenient(jTry.toString());
		 
		 JSONValue temp = jB.get("temp_c");
		 JSONValue visibility = jB.get("visibility_km");
		 
		 JSONValue sunsetH = astro.get("sunset");
		 
		 String sTemp = "Temp : " + temp.toString() + " °C";
		 String sVisibility = "Visibility : " + visibility.toString() + " km";
		
		 sVisibility = sVisibility.replace("\"", "");
		 String sunsetHour;
		 String sunsetMin;
		 String sunset;
		 sunsetHour = sunsetH.toString();
		 sunsetHour = sunsetHour.subSequence(9, 11).toString();
		 sunsetMin = sunsetH.toString();
		 sunsetMin = sunsetMin.subSequence(24, 26).toString();
		 sunset = "Today's sunset occurs at " + sunsetHour + ":" + sunsetMin;
		 Label tempLabel = new Label(sTemp);
		 Label visLabel = new Label(sVisibility);
		 Label sunsetLabel = new Label (sunset);
		 tempLabel.setStylePrimaryName("labelText");
		 visLabel.setStylePrimaryName("labelText");
		 sunsetLabel.setStylePrimaryName("labelText");
		 Image logo = new Image("images/weather.png");
		 vp.add(logo);
		 vp.add(tempLabel); //TO VIEW
		 vp.add(visLabel); //TO VIEW
		 vp.add(sunsetLabel);

		 return vp;
	}
	
	public static AbsolutePanel getMain() {
		start();
		tenDay.scheduleRepeating(1000);
		countDown.getElement().getStyle().setMarginTop(50, Style.Unit.PX);
		countDown.setStylePrimaryName("labelText");
		b.setEnabled(false);
		
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
    		count = 600;
    		tenDay.run();
    		tenDay.scheduleRepeating(1000);
		}
		});
		
		homePanel.setStylePrimaryName("homeBackground");
		header.setStylePrimaryName("h1");
		space.setHeight("25px");
		space.setWidth("1");
		space.getElement().getStyle().setOpacity(100);
		vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tommyPC.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tommyPC.add(header);
		tommyPC.add(vp);
		tommyPC.add(countDown);
		tommyPC.add(b);
		tommyPC.add(space);
		tommyPC.add(nasa);
		tommyPC.add(esa);
		homePanel.add(tommyPC);
		homePanel.getElement().setAttribute("align", "center");
		
		return homePanel;
	}

}
