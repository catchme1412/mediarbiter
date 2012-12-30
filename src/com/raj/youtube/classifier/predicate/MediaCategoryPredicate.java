package com.raj.youtube.classifier.predicate;

import org.apache.commons.collections.Predicate;

import com.raj.media.UniversalMediaEntry;

public abstract class MediaCategoryPredicate implements Predicate {

	public abstract boolean processEntry(UniversalMediaEntry entry);
	
	@Override
	public boolean evaluate(Object arg0) {
		return processEntry((UniversalMediaEntry)arg0);
	}

}
