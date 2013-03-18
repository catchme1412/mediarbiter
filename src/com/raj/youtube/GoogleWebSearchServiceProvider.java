package com.raj.youtube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.raj.youtube.media.search.SearchCriteria;

public class GoogleWebSearchServiceProvider {

	public GoogleWebSearchServiceProvider() {
	}

	public static StringBuilder query(SearchCriteria searchCriteria) throws IOException, JSONException {

		// The request also includes the userip parameter which provides the end
		// user's IP address. Doing so will help distinguish this legitimate
		// server-side traffic from traffic which doesn't come from an end-user.
		URL url = new URL("https://ajax.googleapis.com/ajax/services/search/web?v=1.0&" + "q="
				+ URLEncoder.encode(searchCriteria.getQueryString(), "UTF-8") + "&userip=168.192.1.1");
		URLConnection connection = url.openConnection();
		// connection.addRequestProperty("Referer", /* Enter the URL of your
		// site here */);

		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		// JSONObject json = new JSONObject(builder.toString());
		// System.out.println(json.getJSONObject("responseData"));
		// System.out.println(json.getJSONObject("responseData").getJSONObject("cursor"));
		// Iterator k = json.getJSONObject("responseData").keys();
		// while(k.hasNext()) {
		// String key = (String) k.next();
		// System.out.println(key);
		// // System.out.println(json.get(key));
		// }
		// now have some fun with the results...
		return builder;
	}

	public static void main(String[] args) throws IOException, JSONException {
		SearchCriteria s = new SearchCriteria();
		s.setQueryString("Kaliyamardhanam - Classic Malayalam Movie - Mohanlal, Shankarm, Nedumdi Venu malalayalam movie wikipedia");

		StringBuilder r = new GoogleWebSearchServiceProvider().query(s);
		 JSONObject json = new JSONObject(r.toString());
		 System.out.println(json.toString());
		 System.out.println("+++++++++++++++++");
		 System.out.println(json.getJSONObject("responseData").getJSONArray("results").getJSONObject(0).getString("url"));
		 Iterator k = json.getJSONObject("responseData").keys();
		 while(k.hasNext()) {
		 String key = (String) k.next();
		 System.out.println(key);
		 // System.out.println(json.get(key));
		 }
	}

}
