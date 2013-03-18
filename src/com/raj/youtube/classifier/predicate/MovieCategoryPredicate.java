package com.raj.youtube.classifier.predicate;

import com.raj.media.UniversalMediaEntry;

public class MovieCategoryPredicate extends MediaCategoryPredicate {

	@Override
	public boolean processEntry(UniversalMediaEntry entry) {
		String title = entry.getTitle().toLowerCase();
		boolean isFullMoviePro = title.contains("full movie") || title.contains("movie");
		boolean isFullLengthMovie = title.contains("full length movie");
		boolean isPartPro = title.contains("part");
		boolean isScene = title.contains("scene");
		boolean isTrailer = title.contains("trailer");
		boolean isSong = title.contains("song");
		if (!isSong && !isTrailer && !isScene && (isFullMoviePro || isFullLengthMovie)) {
			boolean isCorrectDuration = entry.getDuration().longValue() > 3600; //1 HOUR
			if (isCorrectDuration && entry.getRating() > 3) {
				return true;
			}
			if (!isCorrectDuration && isPartPro) {
				if (entry.getRating() > 3 && entry.getPlaylist() != null)
				return true;
			} else {
				System.out.println("Removing:" + entry.getTitle());
				return false;
			}
		} else if (isPartPro) {
			boolean isCorrectDuration = entry.getDuration().longValue() > 480;
			return isCorrectDuration;
		}
		System.out.println("Removing:" + entry.getTitle());
		return false;
	}

}
