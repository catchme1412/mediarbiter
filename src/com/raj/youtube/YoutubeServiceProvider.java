package com.raj.youtube;

import java.net.URL;

import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoFeed;

public class YoutubeServiceProvider {

	private YouTubeService service = null;
	private VideoFeed videoFeed = null;
	
	public YoutubeServiceProvider() {
		service = getYoutubeService();
	}
	
	public VideoFeed query(String queryString) {
		try {
			YouTubeQuery query = null;
			query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
			query.setFullTextQuery(queryString);
			query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
			query.setOrderBy(YouTubeQuery.OrderBy.RELEVANCE);
			videoFeed = service.query(query, VideoFeed.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoFeed;
	}

	private YouTubeService getYoutubeService() {
		String developerKey ="AI39si56FCJdE17ywnLex__bul_fdgVlOq_qWKGGLybee9g1_lJJERbzNCkQU2H5P9bejUdri1J0PD6m3dLkY1XlLlN6lqpwvw";
		String clientID = "mediarbiter";
		YouTubeService service = new YouTubeService(clientID, developerKey);
		return service;
	}
}
