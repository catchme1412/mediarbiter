package com.raj.youtube;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.VideoMessageFeed;
import com.google.gdata.util.ServiceException;

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
	
	public VideoMessageFeed getComments(String id) {
		String queryStr = "http://gdata.youtube.com/feeds/api/videos/" + id + "/comments?v=2&max-results=20&start-index=1";
		System.out.println(queryStr);
		try {
			YouTubeQuery query = new YouTubeQuery(new URL(queryStr));
			return service.query(query, VideoMessageFeed.class);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(new YoutubeServiceProvider().getComments("o4SW8aBSuYI"));
	}
}
