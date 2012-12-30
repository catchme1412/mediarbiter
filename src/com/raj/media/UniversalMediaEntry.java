package com.raj.media;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gdata.data.youtube.VideoEntry;
import com.raj.youtube.classifier.Category;
import com.raj.youtube.classifier.MediaClassifier;

public class UniversalMediaEntry {

	private Collection<Serializable> title;
	
	private VideoEntry entry;
	
	private List<String> categoryList;
	
	
	private static MediaClassifier classifier;
	
	static {
		classifier = new MediaClassifier();
	}
	
	public UniversalMediaEntry (VideoEntry entry) {
		categoryList = new ArrayList<String>();
		this.entry = entry;
		String titleText = entry.getTitle().getPlainText();
		categoryList = new ArrayList<String>();
		Category cat = classifier.classify(this);
		categoryList.add(cat.name());
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
		return null;//entry.youtubeVideoEntry.mediaGroup.thumbnails[0].url
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
	
}
