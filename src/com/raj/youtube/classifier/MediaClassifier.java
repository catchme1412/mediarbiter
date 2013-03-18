//package com.raj.youtube.classifier;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import com.raj.media.UniversalMediaEntry;
//import com.raj.youtube.classifier.predicate.DataEntractor;
//import com.raj.youtube.classifier.predicate.DurationExtractor;
//import com.raj.youtube.classifier.predicate.RatingExtractor;
//import com.raj.youtube.classifier.predicate.TitleContainsPredicate;
//
//import de.daslaboratorium.machinelearning.classifier.BayesClassifier;
//import de.daslaboratorium.machinelearning.classifier.Classification;
//import de.daslaboratorium.machinelearning.classifier.Classifier;
//
//public class MediaClassifier {
//
//	private Classifier<Serializable, Serializable> bayes = null;
//
//	private enum ATTRIB {
//
//		TITLE_CONTAINS_MOVIE(false, new TitleContainsPredicate("movie")), 
//		TITLE_CONTAINS_SHORT_FILM(false, new TitleContainsPredicate("short film")), 
//		TITLE_CONATAINS_SONG(false, new TitleContainsPredicate("song")),
//		TITLE_CONTAINS_TRAILER(false, new TitleContainsPredicate("trailer")),
//		TITLE_CONTAINS_FULL_MOVIE(false, new TitleContainsPredicate("full movie")),
//		TITLE_CONTAINS_FULL(false, new TitleContainsPredicate("full")),
//		MIN_DURATION(0.0, new DurationExtractor()), YOUTUBE_RATING(0.0, new RatingExtractor());
//		
//		private Serializable defaultValue;
//		private DataEntractor predicate;
//		
//
//		ATTRIB(Serializable defaultValue, DataEntractor predicate) {
//			setDefaultValue(defaultValue);
//			this.predicate = predicate;
//		}
//
//		public Serializable getDefaultValue() {
//			return defaultValue;
//		}
//
//		public void setDefaultValue(Serializable defaultValue) {
//			this.defaultValue = defaultValue;
//		}
//
//	}
//
//	public MediaClassifier() {
//		bayes = new BayesClassifier<Serializable, Serializable>();
//		List<Serializable> movies = init();
//		movies.add(ATTRIB.TITLE_CONTAINS_MOVIE.ordinal(), true);
//		movies.add(ATTRIB.TITLE_CONTAINS_FULL_MOVIE.ordinal(), true);
//		movies.add(ATTRIB.MIN_DURATION.ordinal(), 300);
//		train(Category.MOVIE, movies);
//
//		List<Serializable> trailer = init();
//		trailer.add(ATTRIB.TITLE_CONTAINS_TRAILER.ordinal(), true);
//		train(Category.TRAILER, trailer);
//
//		List<Serializable> song = init();
//		song.add(ATTRIB.TITLE_CONATAINS_SONG.ordinal(), true);
//		train(Category.MUSIC, song);
//
//	}
//
//	public void train(Category category, Collection<Serializable> movies) {
//		bayes.learn(category.name(), movies);
//	}
//
//	public Category classify(UniversalMediaEntry entry) {
//		List<Serializable> input = initClassifyCandidate(entry);
//		Classification<Serializable, Serializable> classifiedValue = bayes.classify(input);
//		System.out.println(entry.getTitle() +  ":::" + classifiedValue.getCategory().toString() + "::::::::::::" + classifiedValue.getProbability());
//		return Category.valueOf(classifiedValue.getCategory().toString());
//	}
//
//	// public Category classify(UniversalMediaEntry entry) {
//	// switch(entry.getT) {
//	//
//	// }
//	// }
//
//	private List<Serializable> init() {
//		List<Serializable> movies = new ArrayList<Serializable>();
//		try {
//			for (ATTRIB e : ATTRIB.values()) {
//				movies.add(e.ordinal(), e.getClass().getClass().getName() + e.getDefaultValue());
//			}
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		return movies;
//	}
//	
//	private List<Serializable> initClassifyCandidate(UniversalMediaEntry entry) {
//		List<Serializable> movies = new ArrayList<Serializable>();
//		try {
//			for (ATTRIB e : ATTRIB.values()) {
//				e.predicate.evaluate(entry);
//				movies.add(e.ordinal(), e.predicate.getResult());
//			}
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		return movies;
//	}
//	
//	public static void main(String[] args) {
//		MediaClassifier t = new MediaClassifier();
//		List<Serializable> movies = t.init();
//		movies.add(ATTRIB.TITLE_CONATAINS_SONG.ordinal(), ATTRIB.TITLE_CONATAINS_SONG.name()+ true);
//		Classification<Serializable, Serializable> tt = t.bayes.classify(movies);
//		System.out.println(t.bayes.getFeatures());
//		System.out.println(tt.getCategory());
//		
//	}
//}
