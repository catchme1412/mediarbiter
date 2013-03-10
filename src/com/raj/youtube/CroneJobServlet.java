package com.raj.youtube;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.raj.media.entity.MediaEntry;
import com.raj.media.entity.MediaEntryDAO;
import com.raj.youtube.media.search.SearchCriteria;

@SuppressWarnings("serial")
public class CroneJobServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<MediaEntry> latestEntries = MediaEntryDAO.getLatestEntries();
		for (MediaEntry entry : latestEntries) {
			if (entry.getWikipediaUrl() == null) {

				SearchCriteria searchCriteria = new SearchCriteria();
				searchCriteria.setQueryString(entry.getTitle() + " " + entry.getLanguague() + " "
						+ entry.getMainCategory() + " wikipedia");
				try {
					StringBuilder result = GoogleWebSearchServiceProvider.query(searchCriteria);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		new CroneJobServlet().doGet(null, null);
	}
}
