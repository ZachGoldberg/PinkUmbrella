package com.pinkumbrella.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PredictionService {

	public static List<String> getRecommendedPlaces(Context c, double lat, double lon) throws Exception {
		
		
		List<String> items = new ArrayList<String>();
        
        
        TelephonyManager telephonyManager = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
        String uid = telephonyManager.getDeviceId();
        
        JSONArray response = executeHttpGet("http://apm.zachgoldberg.com:8000/pinkumbrella/getlocations?uid=" + uid + "&servicetype=xyz&serviceid=xyz&servicetoken=xyz&lat=" + lat + "&long=" + lon);

        JSONArray places = response.getJSONArray(1);
        
        for(int i = 0; i < places.length(); i++) {
        	
        	JSONObject place = places.getJSONObject(i);
        	
        	Log.i("PLACE", "Place " + i + " = " + place);
        	items.add(place.getString("name"));
        	
        }
        
        return items;
        

	}
	  // Calls a URI and returns the answer as a JSON object
    private static JSONArray executeHttpGet(String uri) throws Exception{
    	HttpGet req = new HttpGet(uri);

    	HttpClient client = new DefaultHttpClient();
    	HttpResponse resLogin = client.execute(req);
    	BufferedReader r = new BufferedReader(
    			new InputStreamReader(resLogin.getEntity()
    					.getContent()));
    	StringBuilder sb = new StringBuilder();
    	String s = null;
    	while ((s = r.readLine()) != null) {
    		sb.append(s);
    	}


    	return new JSONArray(sb.toString());
    }
    
}
