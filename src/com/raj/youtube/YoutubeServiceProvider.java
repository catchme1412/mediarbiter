package com.raj.youtube;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.raj.media.UniversalMediaEntry;
import com.raj.media.UniversalMediaFeed;
import com.raj.youtube.media.search.SearchCriteria;

public class YoutubeServiceProvider {

	private YouTubeService service = null;
	
	public YoutubeServiceProvider() {
		service = getYoutubeService();
	}
	
	public VideoFeed mostRecent(SearchCriteria searchCriteria) {
		
		VideoFeed videoFeed = null;
		try {
			YouTubeQuery query = null;
			query = new YouTubeQuery(new URL("https://gdata.youtube.com/feeds/api/charts/movies/most_recent?region=IN"));
//			query.setFullTextQuery(searchCriteria.getQueryString());
//			query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
//			query.setOrderBy(YouTubeQuery.OrderBy.RELEVANCE);
			videoFeed = service.query(query, VideoFeed.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoFeed;
	}
	
	public UniversalMediaFeed query(SearchCriteria searchCriteria) {
		//tryCache(queryString);
		UniversalMediaFeed feed = null;
		try {
			YouTubeQuery query = null;
			query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
			query.setFullTextQuery(searchCriteria.getQueryString());
//			query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
			query.setOrderBy(YouTubeQuery.OrderBy.RELEVANCE);
			VideoFeed videoFeed = service.query(query, VideoFeed.class);
			 List<UniversalMediaEntry> list = getUniversalMediaEntries(videoFeed);
			 feed = new UniversalMediaFeed(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feed;
	}
	
	private List<UniversalMediaEntry> getUniversalMediaEntries(VideoFeed videoFeed) {
		System.out.println("Before filtering :" + videoFeed.getEntries().size());
		List<UniversalMediaEntry> entries = new ArrayList<UniversalMediaEntry>();
		for (VideoEntry entry : videoFeed.getEntries()) {
			entries.add(new UniversalMediaEntry(entry));
		}
		return entries;
	}

//	private VideoFeed tryCache(String queryString) {
//		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
//	    syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
//	    return videoFeed;
//		
//	}

	private YouTubeService getYoutubeService() {
		String developerKey ="AI39si56FCJdE17ywnLex__bul_fdgVlOq_qWKGGLybee9g1_lJJERbzNCkQU2H5P9bejUdri1J0PD6m3dLkY1XlLlN6lqpwvw";
		String clientID = "mediarbiter";
		YouTubeService service = new YouTubeService(clientID, developerKey);
		return service;
	}
	
	public static void main(String[] args) {
		YoutubeServiceProvider youtubeServiceProvider = new YoutubeServiceProvider();
		VideoFeed mostRecent = youtubeServiceProvider.mostRecent(null);
		for (VideoEntry  entry: mostRecent.getEntries()) {
			System.out.println(entry.getId());
			System.out.println(entry.getTitle().getPlainText());
		}
	}
}
