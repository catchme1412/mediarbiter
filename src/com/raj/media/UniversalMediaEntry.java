package com.raj.media;

import java.util.ArrayList;
import java.util.List;

import com.google.gdata.data.youtube.VideoEntry;
import com.raj.media.entity.MediaEntry;

public class UniversalMediaEntry {

	private VideoEntry entry;

	private String title;

	private String url;

	private double rating;

	private String thumbnail;

	private String description;

	private long duration;

	private String youtubeVideoId;

	private List<String> categoryList;

	private String wikipediaUrl;

	private String playlist;

	// private static MediaClassifier classifier;

	public void setWikipediaUrl(String wikipediaUrl) {
		this.wikipediaUrl = wikipediaUrl;
	}

	static {
		// classifier = new MediaClassifier();
	}

	public UniversalMediaEntry(MediaEntry entry) {
		title = entry.getTitle();
		description = entry.getDescription();
		url = entry.getUrl();
		rating = entry.getRating();
		thumbnail = entry.getThumbnail();
		duration = entry.getDuration();
		wikipediaUrl = entry.getWikipediaUrl();
		playlist = entry.getPlaylist();
	}

	public UniversalMediaEntry(VideoEntry entry) {
		categoryList = new ArrayList<String>();
		this.entry = entry;
		title = this.entry.getTitle().getPlainText();
		description = entry.getMediaGroup().getDescription().getContent().getPlainText();
		thumbnail = entry.getMediaGroup().getThumbnails().get(0).getUrl().toString();
		duration = entry.getMediaGroup().getDuration();
		youtubeVideoId = entry.getId().toString().substring(27);
		url = entry.getLocation();
		if (entry.getRating() != null) {
			rating = entry.getRating().getAverage();
		}
		String content = entry.getContent().toString();
		setPlaylist(content.contains("http://www.youtube.com/view_play_list?p=") == true ? content : null);
		categoryList = new ArrayList<String>();
		// Category cat = classifier.classify(this);
		// categoryList.add(cat.name());
	}

	public double getRating() {
		return rating;
	}

	public String getPlainTextContent() {
		return entry.getPlainTextContent();
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public String getYoutubeVideoId() {
		return youtubeVideoId;
	}

	public VideoEntry getYoutubeVideoEntry() {
		return entry;
	}

	public int getYear() {
		return Integer.parseInt(entry.getContent().getLang());
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
		return title;
	}

	public Long getDuration() {
		return duration;
	}

	public String getWikipediaUrl() {
		return wikipediaUrl;
	}

	public String getPlaylist() {
		return playlist;
	}

	public void setPlaylist(String playlist) {
		this.playlist = playlist;
	}

}
