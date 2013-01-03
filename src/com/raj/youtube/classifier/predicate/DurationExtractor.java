package com.raj.youtube.classifier.predicate;

import java.io.Serializable;

import com.raj.media.UniversalMediaEntry;

public class DurationExtractor extends DataEntractor {

	@Override
	public Serializable processEntry(UniversalMediaEntry entry) {
		
		return entry.getDuration();
	}

}
