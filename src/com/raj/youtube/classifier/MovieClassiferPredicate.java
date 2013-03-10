//package com.raj.youtube.classifier;
//
//import java.util.Set;
//
//import org.apache.commons.collections.Predicate;
//
//import quickdt.Attributes;
//import quickdt.Instance;
//import quickdt.Leaf;
//import quickdt.Node;
//import quickdt.TreeBuilder;
//
//import com.google.common.collect.Sets;
//import com.google.gdata.data.youtube.VideoEntry;
//import com.raj.media.UniversalMediaEntry;
//
///**
// * @deprecated use {@link MediaClassifier}
// * @author rkv
// *
// */
//public class MovieClassiferPredicate implements Predicate {
//
//	final Set<Instance> instances = Sets.newHashSet();
//
//	public MovieClassiferPredicate() {
//		//TODO convert to template : Qualifier
//		instances.add(Attributes.create("titleContainsMovie", true, "duration", "above5Mins", "titleContainsSong", false).classification("movie"));
//		instances.add(Attributes.create("titleContainsMovie", false, "duration", "above5Mins", "titleContainsSong", true).classification("song"));
//		instances.add(Attributes.create("titleContainsMovie", false, "duration", "lessThan5Mins", "titleContainsSong", true).classification("song"));
//		instances.add(Attributes.create("titleContainsMovie", false, "duration", "above5Mins", "titleContainsSong", false).classification("other"));
//		instances.add(Attributes.create("titleContainsMovie", false, "duration", "lessThan5Mins", "titleContainsSong", false).classification("other"));
//	}
//
//	@Override
//	public boolean evaluate(Object arg0) {
//		if (arg0 instanceof UniversalMediaEntry) {
//			UniversalMediaEntry entry = (UniversalMediaEntry)arg0;
//			TreeBuilder treeBuilder = new TreeBuilder();
//			Node tree = treeBuilder.buildTree(instances);
//			Leaf leaf = tree.getLeaf(Attributes.create("titleContainsMovie", true));
//			System.out.println(leaf.classification);
//			if (leaf.classification.equals("movie")) {
//				entry.addCategory("movie");
//				System.out.println("OOO");
//				return true;
//			}
//			
//		}
//		return false;
//	}
//
//	public static void main(String[] args) {
//		MovieClassiferPredicate predicate = new MovieClassiferPredicate();
//		UniversalMediaEntry t = new UniversalMediaEntry(new VideoEntry());
//		predicate.evaluate(t);
//	}
//}
