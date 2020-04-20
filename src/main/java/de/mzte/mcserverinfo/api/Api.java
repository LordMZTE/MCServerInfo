package de.mzte.mcserverinfo.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Api {
	public static final String apiUrl = "https://api.mcsrvstat.us/2/";

	public static JsonObject getServerInfo(String ip, int attempts) {
		String apiRespose = getFromApi(ip, attempts);
		if(apiRespose == null) {
			return null;
		}

		return (JsonObject) JsonParser.parseString(apiRespose);
	}

	private static String getFromApi(String ip, int attempts) {
		URL url;
		try {
			url = new URL(apiUrl + ip);
		} catch(MalformedURLException e) {
			return null;
		}
		int i = 0;
		while(i <= attempts) {
			try {
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					return response.toString();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
			i++;
		}
		return null;
	}

}
