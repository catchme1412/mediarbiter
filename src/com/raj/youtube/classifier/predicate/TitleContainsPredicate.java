package com.raj.youtube.classifier.predicate;

import java.io.Serializable;

import com.raj.media.UniversalMediaEntry;

public class TitleContainsPredicate extends DataEntractor {

	private String containingText;
	
	public TitleContainsPredicate(String containingText) {
		this.containingText = containingText;
	}
	
	@Override
	public Serializable processEntry(UniversalMediaEntry entry) {
		return entry.getTitle().toLowerCase().contains(containingText);
	}

}
