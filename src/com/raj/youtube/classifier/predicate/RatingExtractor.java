package com.raj.youtube.classifier.predicate;

import java.io.Serializable;

import com.raj.media.UniversalMediaEntry;

public class RatingExtractor extends DataEntractor {

	@Override
	public Serializable processEntry(UniversalMediaEntry entry) {
		return entry.getRating();
	}

}
