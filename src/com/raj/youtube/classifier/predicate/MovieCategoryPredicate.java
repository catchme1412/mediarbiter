package com.raj.youtube.classifier.predicate;

import com.raj.media.UniversalMediaEntry;

public class MovieCategoryPredicate extends MediaCategoryPredicate {

	@Override
	public boolean processEntry(UniversalMediaEntry entry) {
		boolean isMovie = entry.getTitle().toLowerCase().contains("movie");
		boolean isFullMoviePro = entry.getTitle().toLowerCase().contains("full");
		boolean isPartPro = entry.getTitle().toLowerCase().contains("part ");
		if (isFullMoviePro) {
			boolean isCorrectDuration = entry.getDuration().longValue() > 3600; //1 HOUR
			if (!isCorrectDuration && isPartPro) {
				return true;
			} else if (isCorrectDuration) {
				return true;
			}
		} else if (isPartPro) {
			boolean isCorrectDuration = entry.getDuration().longValue() > 480;
			return isCorrectDuration;
		}
		return false;
	}

}
