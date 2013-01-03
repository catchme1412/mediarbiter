package com.raj.media;

import iweb2.ch5.usecase.email.MediaClassifier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoMessageEntry;
import com.google.gdata.data.youtube.VideoMessageFeed;
import com.raj.youtube.YoutubeServiceProvider;

public class UniversalMediaEntry {

	private Collection<Serializable> title;
	
	private VideoEntry entry;
	
	private List<String> categoryList;
	
	private Map<String, Integer> categoryCountMap;
	
	private static MediaClassifier classifier;
	
	static {
//		classifier = new MediaClassifier();
	}
	
	public UniversalMediaEntry (VideoEntry youtubeEntry) {
		categoryCountMap = new HashMap<String, Integer>();
		
		categoryList = new ArrayList<String>();
		this.entry = youtubeEntry;
		categoryList = new ArrayList<String>();
		String title = entry.getTitle().getPlainText().toLowerCase();
		String category = "other";
		if (title.toLowerCase().contains("movie")) {
			categoryList.add("movie");
			categoryCountMap.put("movie", categoryCountMap.get("movie").intValue() + 1);
		} else if (title.contains("song")) {
			categoryList.add("song");
		} else {
			VideoMessageFeed comments = new YoutubeServiceProvider().getComments(getYoutubeId());
			int movieCount = 0;
			int songCount = 0;
			for (VideoMessageEntry  e : comments.getEntries()) {
				if (e.getTitle().getPlainText().toLowerCase().contains("movie")) {
					categoryList.add("movie");
				} else if (e.getTitle().getPlainText().toLowerCase().contains("movie")) {
					categoryList.add("song");
				}
			}
			category = movieCount > songCount ? "movie" : "song";
			categoryList.add(category);
		}
//		if (isBinary()) {
//			if (media.getId().startsWith("spam-")) {
//				return "SPAM";
//			} else {
//				return "NOT SPAM";
//			}
//		} else {
//			// relying id to have pattern: "biz-???", "world-???", ...
//			String[] parts = media.getId().split("-");
//			if (parts.length < 2) {
//				throw new RuntimeException("Unsupported id format. Expected id format: '<catgory>-???'");
//			}
//			return parts[0].toUpperCase();
//		}
	
//		Category cat = classifier.classify(this);
//		categoryList.add(cat.name());
	}
	
	
	
	public double getRating() {
		if (entry.getRating() != null) {
			return entry.getRating().getAverage();
		}
		else {
			return 0;
		}
	}
	
	public String getPlainTextContent() {
		return entry.getPlainTextContent();
	}
	
	public String getThumbnail() {
		return null;//entry.youtubeVideoEntry.mediaGroup.thumbnails[0].url;
	}
	
	public String getUrl() {
		return entry.getLocation();
	}
	
	public String getDescription() {
		return entry.getMediaGroup().getDescription().getContent().getPlainText();
	}
	
	public String getYoutubeVideoId() {
		return entry.getId().toString().substring(27);
	}
	public VideoEntry getYoutubeVideoEntry() {
		return entry;
	}
	
	
	public int getYear() {
		return Integer.parseInt(entry.getId().toString().substring(16,20));
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}
	
	public void addCategory(String category) {
		categoryList.add(category);
	}
	
	public String getTitle() {
		return this.entry.getTitle().getPlainText();
	}

	public Long getDuration() {
		return entry.getMediaGroup().getDuration();
	}

	public String getId() {
		return entry.getId();
	}
	
	public String getYoutubeId() {
		return entry.getId().substring("tag:youtube.com,2008:video:".length());
	}
	
}
