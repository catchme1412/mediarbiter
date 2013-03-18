package com.raj.youtube.media;


import com.raj.media.UniversalMediaEntry;
import com.raj.media.entity.MediaEntry;

public class MediaEntryUtil {

	public static void copy(UniversalMediaEntry universalMediaEntry, MediaEntry entryDB) {
		entryDB.setTitle(universalMediaEntry.getTitle());
		entryDB.setMediaId(universalMediaEntry.getYoutubeVideoId());
		entryDB.setRating(universalMediaEntry.getRating());
		entryDB.setDuration(universalMediaEntry.getDuration());
		entryDB.setDescription(universalMediaEntry.getDescription().substring(0, universalMediaEntry.getDescription().length() > 400 ? 400 : universalMediaEntry.getDescription().length() ));
		entryDB.setUrl(universalMediaEntry.getUrl());
		entryDB.setThumbnail(universalMediaEntry.getThumbnail());
	}

}
