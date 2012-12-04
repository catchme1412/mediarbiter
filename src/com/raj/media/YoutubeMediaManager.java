package com.raj.media;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.Parser;


public class YoutubeMediaManager {

	public static Feed extract(String keyword) {
		Abdera abdera = new Abdera();

		Parser parser = abdera.getParser();
		List<MovieMediaEntry> result = new ArrayList<MovieMediaEntry>();
		
		URL url;
		try {
			
			url = new URL("http://gdata.youtube.com/feeds/api/videos?q=" + URLEncoder.encode(keyword, "UTF-8"));
			String response = Downloader.fetch(url);
			if (true) {
				MovieMediaEntry e = new MovieMediaEntry();
				e.setTitle(response);
				result.add(e);
			}
			// convert String into InputStream
			InputStream is = new ByteArrayInputStream(response.getBytes());
		 
			SaxFeedUnmarshaller t = new SaxFeedUnmarshaller(is);
			return t.getFeed();
//			result.addAll(t.getFeed());
			
//			try {
//				XPathReader xPathReader = new XPathReader(response, false);
//				MovieMedia e = new MovieMedia();
//				e.setTitle(xPathReader.read("//feed/entry/title", XPathConstants.NODESET).toString());
//				System.out.println();
//				result.add(e);
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			Document<Feed> doc = parser.parse(new StringReader(response));
//			Feed feed = doc.getRoot();
//			for (Entry entry : feed.getEntries()) {
//				MovieMedia media = parseEntry(entry);
//				result.add(media);
//				// for (Link l : entry.getLinks()) {
//				// System.out.println("\t\t" + l);
//				// }
//			}
		} catch (Exception e) {
			
		} 
		return null;
	}


	
	public static void main(String[] args) {
		System.out.println(YoutubeMediaManager.extract("malayalam"));
	}
}
