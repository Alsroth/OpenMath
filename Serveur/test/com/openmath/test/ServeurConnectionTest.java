package com.openmath.serveur;

import org.junit.*;
import org.json.*;
import java.io.*;


public class ServeurConnectionTest {

	private String loginMessage;
	private StringWriter sw;
	private PrintWriter pw;
	private ServeurConnection sc;

	@Before
	public void setup() {
		loginMessage = "";
		sc = new ServeurConnection();
		try {
			JSONObject loginJO = new JSONObject();
			loginJO.put("username","Alexandre");
			loginJO.put("password","mdpalex");
			loginJO.put("messageType","LOGIN");
			loginMessage = loginJO.toString();
		} catch(JSONException je) {
			je.printStackTrace();
		}
	
		sw = new StringWriter(0);
		pw = new PrintWriter(sw);
		
	}

	public void traiterTest() {
		sc.traiter(loginMessage, pw);
		Assert.assertEquals("true",sw.toString());
	}


}