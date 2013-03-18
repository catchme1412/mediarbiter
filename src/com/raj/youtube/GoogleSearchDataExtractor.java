package com.raj.youtube;

import java.io.IOException;
import java.util.Iterator;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.raj.youtube.media.search.SearchCriteria;

public class GoogleSearchDataExtractor {

	private String wikipediaUrl = null;
	private String mediaTitle = null;
	
	public GoogleSearchDataExtractor(SearchCriteria s) throws IOException, JSONException {
		StringBuilder r = getGoogleSearchResults(s);
		JSONObject json = new JSONObject(r.toString());
		System.out.println("JSON:" + json);
		JSONObject jsonObject = json.getJSONObject("responseData").getJSONArray("results").getJSONObject(0);
		String url = jsonObject.getString("url");
		if (url.contains("http://en.wikipedia.org")) {
			setWikipediaUrl(url);
			setMediaTitle(jsonObject.getString("title"));
		}
		
	}

	public static void main(String[] args) throws IOException, JSONException {
		SearchCriteria s = new SearchCriteria();
		s.setQueryString("kaazhcha malayalam movie mammooty");

		StringBuilder r = getGoogleSearchResults(s);
		JSONObject json = new JSONObject(r.toString());
		System.out.println(json.toString());
		System.out.println("+++++++++++++++++");
		System.out
				.println(json.getJSONObject("responseData").getJSONArray("results").getJSONObject(0).getString("url"));
		Iterator k = json.getJSONObject("responseData").keys();
		while (k.hasNext()) {
			String key = (String) k.next();
			System.out.println(key);
			// System.out.println(json.get(key));
		}
	}

	private static StringBuilder getGoogleSearchResults(SearchCriteria s) throws IOException, JSONException {
		StringBuilder r = new GoogleWebSearchServiceProvider().query(s);
		return r;
	}

	public String getWikipediaUrl() {
		return wikipediaUrl;
	}

	public void setWikipediaUrl(String wikipediaUrl) {
		this.wikipediaUrl = wikipediaUrl;
	}

	public String getMediaTitle() {
		return mediaTitle;
	}

	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}

}
