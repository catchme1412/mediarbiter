package com.raj.media.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.store.types.sco.simple.Date;

@Entity(name = "MediaEntry")
public class MediaEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String mediaId;
	
	private String title;
	
	private boolean isFullVideo;
	
	private double rating;
	
	private List<String> cast;
	
	private List<String> directedBy;
	
	private List<String> producer;
	
	private String languague;
	
	private String url;
	
	private long duration;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private String wikipediaUrl;

	private String description;
	
	private String thumbnail;
	
	private String playlist;
	
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public List<String> getCast() {
		return cast;
	}

	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	public List<String> getDirectedBy() {
		return directedBy;
	}

	public void setDirectedBy(List<String> directedBy) {
		this.directedBy = directedBy;
	}

	public List<String> getProducer() {
		return producer;
	}

	public void setProducer(List<String> producer) {
		this.producer = producer;
	}

	public String getLanguague() {
		return languague;
	}

	public void setLanguague(String languague) {
		this.languague = languague;
	}

	
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getWikipediaUrl() {
		return wikipediaUrl;
	}

	public void setWikipediaUrl(String wikipediaUrl) {
		this.wikipediaUrl = wikipediaUrl;
	}

	public String getMainCategory() {
		return "movie";
	}

	public boolean isFullVideo() {
		return isFullVideo;
	}

	public void setFullVideo(boolean isFullVideo) {
		this.isFullVideo = isFullVideo;
	}

	public String getDescription() {
		return description;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("id:").append(id);
		b.append("; title:").append(title);
		return b.toString();
		
	}

	public String getPlaylist() {
		return playlist;
	}

	public void setPlaylist(String playlist) {
		this.playlist = playlist;
	}

}
