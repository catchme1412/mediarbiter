package com.raj.youtube;

import java.net.URL;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoFeed;
import com.raj.youtube.media.search.SearchCriteria;

public class YoutubeServiceProvider {

	private YouTubeService service = null;
	private VideoFeed videoFeed = null;
	
	public YoutubeServiceProvider() {
		service = getYoutubeService();
	}
	
	public VideoFeed query(SearchCriteria searchCriteria) {
		//tryCache(queryString);
		
		try {
			YouTubeQuery query = null;
			query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
			query.setFullTextQuery(searchCriteria.getQueryString());
			query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
			query.setOrderBy(YouTubeQuery.OrderBy.RELEVANCE);
			videoFeed = service.query(query, VideoFeed.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoFeed;
	}

	private VideoFeed tryCache(String queryString) {
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
	    syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
	    return videoFeed;
		
	}

	private YouTubeService getYoutubeService() {
		String developerKey ="AI39si56FCJdE17ywnLex__bul_fdgVlOq_qWKGGLybee9g1_lJJERbzNCkQU2H5P9bejUdri1J0PD6m3dLkY1XlLlN6lqpwvw";
		String clientID = "mediarbiter";
		YouTubeService service = new YouTubeService(clientID, developerKey);
		return service;
	}
}
