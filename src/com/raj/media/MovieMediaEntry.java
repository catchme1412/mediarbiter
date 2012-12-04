package com.raj.media;

import java.util.List;

import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.parser.stax.FOMEntry;

public class MovieMediaEntry extends FOMEntry {

	private List<String> directors;
	
	private List<String> casts;
	
	private String thumbnail;
	
	private List<String> producers;
	
	private String url;
	
	private List<String> category;

	private float rating;

	private float arbiterRating;

	private float duration;

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public List<String> getCasts() {
		return casts;
	}

	public void setCasts(List<String> casts) {
		this.casts = casts;
	}

	public List<String> getProducers() {
		return producers;
	}

	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getArbiterRating() {
		return arbiterRating;
	}

	public void setArbiterRating(float arbiterRating) {
		this.arbiterRating = arbiterRating;
	}


	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	public String toString() {
		return url;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getThumbnail() {
		return thumbnail;
	}

}
