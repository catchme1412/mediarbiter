package com.raj.youtube;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.common.base.StringUtil;
import com.raj.media.UniversalMediaEntry;
import com.raj.media.UniversalMediaFeed;
import com.raj.media.entity.MediaEntry;
import com.raj.youtube.classifier.predicate.MovieCategoryPredicate;
import com.raj.youtube.media.MediaEntryUtil;
import com.raj.youtube.media.search.SearchCriteria;

public class CroneJobServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SearchCriteria searchCriteria = getSearchCriteria(req);
		// search and update
		if (searchCriteria != null) {
			UniversalMediaFeed videoFeed = new YoutubeServiceProvider().query(searchCriteria);
			if (videoFeed != null) {
				int count = 0;
				try {
					List<UniversalMediaEntry> entries = videoFeed.getEntries();
					CollectionUtils.filter(entries, new MovieCategoryPredicate());
					count = save(searchCriteria, entries);
					req.setAttribute("entries", entries);
					// out.println(videoFeed.getTitle().getPlainText());
					// printVideoFeed(videoFeed, out);
					req.getRequestDispatcher("/results.jsp").include(req, resp);
				} catch (Exception e) {
					System.out.println("Failed to get search results");
				}
				System.out.println("Got " + count + " full movies");
			} else {
				System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
			}
		}
	}

	private int save(SearchCriteria searchCriteria, List<UniversalMediaEntry> entries) throws IOException,
			JSONException {
		int count = 0;
		for (UniversalMediaEntry entry : entries) {
			count++;
			MediaEntry entryDB = new MediaEntry();
			MediaEntryUtil.copy(entry, entryDB);
			SearchCriteria s = new SearchCriteria();
			s.setQueryString(entry.getTitle() + " malayalam movie");
			try {
				System.out.println(s);
				GoogleSearchDataExtractor googleSearchDataExtractor = new GoogleSearchDataExtractor(searchCriteria);
				String wikipediaUrl = googleSearchDataExtractor.getWikipediaUrl();
				System.out.println("wikipediaUrl:"+wikipediaUrl);
				entry.setWikipediaUrl(wikipediaUrl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Failed to get search results" + entry.getTitle() + " due to " + e);
			}
			System.out.println(">>>>>>>>>>>" + entryDB.getTitle());
			// MediaEntryDAO.create(entryDB);
		}
		return count;
	}

	private List<UniversalMediaEntry> getUniversalMediaEntries(VideoFeed videoFeed) {
		System.out.println("Before filtering :" + videoFeed.getEntries().size());
		List<UniversalMediaEntry> entries = new ArrayList<UniversalMediaEntry>();
		for (VideoEntry entry : videoFeed.getEntries()) {
			entries.add(new UniversalMediaEntry(entry));
		}
		return entries;
	}

	private StringBuilder googleSearch(SearchCriteria searchCriteria) throws IOException {
		StringBuilder result = null;
		try {
			result = GoogleWebSearchServiceProvider.query(searchCriteria);
			// JSONTokener response_tokens = new
			// JSONTokener(result.toString());
			// JSONObject response = new JSONObject(response_tokens);
			// ObjectMapper mapper = new ObjectMapper();
			// JsonNode root = mapper.readTree(json);
			// List<JsonNode> values = root.findValues("Value");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	private SearchCriteria getSearchCriteria(HttpServletRequest req) {
		SearchCriteria searchCriteria = null;
		String queryString = req.getParameter("q");
		if (!StringUtil.isEmpty(queryString)) {
			String lang = req.getParameter("language");
			queryString = StringUtil.isEmpty(queryString) ? "malayalam movies" : "malayalam movies " + queryString;
			searchCriteria = new SearchCriteria();
			searchCriteria.setCategory(lang);
			searchCriteria.setQueryString(queryString + " wikipedia");
		}
		return searchCriteria;
	}

	public static void main(String[] args) throws IOException {
		// new CroneJobServlet().doGet(null, null);
		String title = "kaazhcha full movie part 1";
		String d1 = "Super hit movie Kaazcha part -3";
		String d2 = "Super hit movie Kaazcha part -5";
		String d3 = "Super hit movie - Kaazcha part - 10";

		List titleList = new ArrayList();
		// titleList.add(Arrays.asList(title.split(" ")));
		titleList.add("movie");
		titleList.add("full");
		List d1List = Arrays.asList(d1.split(" "));
		List d2List = Arrays.asList(d2.split(" "));
		List d3List = Arrays.asList(d3.split(" "));
		d1List = new ArrayList();
		d1List.add("movie");
		d1List.add("ff");
		System.out.println(titleList.retainAll(d1List));
		System.out.println(titleList);
	}

}
