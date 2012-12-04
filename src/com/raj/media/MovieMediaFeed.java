package com.raj.media;

import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.stax.FOMFeed;

public class MovieMediaFeed extends FOMFeed {
	
	public Feed addEntry(MovieMediaEntry entry) {
		return super.addEntry(entry);
	}

}
