package com.raj.media;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gdata.data.Category;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;

public class UnversalMediaFeed {

	private VideoFeed feed;
	private List<UniversalMediaEntry> entries;
	
	public UnversalMediaFeed(VideoFeed videoFeed) {
		this.feed = videoFeed;
		entries = new ArrayList<UniversalMediaEntry>();
		List<VideoEntry> entryList = feed.getEntries();
		for (VideoEntry entry : entryList) {
			entries.add(new UniversalMediaEntry(entry));
		}
	}

	public List<UniversalMediaEntry> getEntries() {
		return entries;
	}
	
	public String getTitle() {
		return feed.getTitle().getPlainText();
	}
	
	public VideoFeed getYoutubeVideoFeed() {
		return feed;
	}
	
	public Set<Category> getCategories() {
		return feed.getCategories();
	}
}
