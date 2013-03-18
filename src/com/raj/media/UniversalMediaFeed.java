package com.raj.media;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gdata.data.Category;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;

public class UniversalMediaFeed {

	private String feedTitle;

	private VideoFeed feed;
	private List<UniversalMediaEntry> entries;

	public UniversalMediaFeed(VideoFeed videoFeed) {
		this.feed = videoFeed;
		feedTitle = feed != null && feed.getTitle() != null?  feed.getTitle().getPlainText() : "";
		entries = new ArrayList<UniversalMediaEntry>();
		List<VideoEntry> entryList = feed.getEntries();
		for (VideoEntry entry : entryList) {
			entries.add(new UniversalMediaEntry(entry));
		}
	}

	public UniversalMediaFeed(List<UniversalMediaEntry> entries) {
		this.entries = entries;
	}

	public List<UniversalMediaEntry> getEntries() {
		return entries;
	}

	public String getTitle() {
		return feedTitle;
	}

	public VideoFeed getYoutubeVideoFeed() {
		return feed;
	}

	public Set<Category> getCategories() {
		return feed.getCategories();
	}
}
