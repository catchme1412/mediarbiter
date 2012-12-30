package com.raj.youtube.classifier.predicate;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

import com.raj.media.UniversalMediaEntry;

public abstract class DataEntractor  implements Predicate {

	protected Serializable result;
	
	public abstract Serializable processEntry(UniversalMediaEntry entry);
	
	public Serializable extractResult(UniversalMediaEntry entry) {
		return processEntry(entry);
	}
	
	@Override
	public boolean evaluate(Object arg0) {
		result = extractResult((UniversalMediaEntry)arg0);
		return true;
	}

	public Serializable getResult() {
		return this.getClass().getName() + result;
	}
}
