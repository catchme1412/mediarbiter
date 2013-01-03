package com.raj.youtube.classifier.predicate;

import com.raj.media.UniversalMediaEntry;

public class MusicCategoryPredicate extends MediaCategoryPredicate {

	@Override
	public boolean processEntry(UniversalMediaEntry entry) {
		boolean isSong = entry.getTitle().toLowerCase().contains("song");
		
		return false;
	}

}
