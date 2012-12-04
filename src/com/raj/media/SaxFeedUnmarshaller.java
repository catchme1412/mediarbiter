package com.raj.media;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.abdera.model.Entry;
import org.apache.abdera.parser.stax.FOMEntry;
import org.apache.abdera.parser.stax.FOMFeed;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxFeedUnmarshaller extends DefaultHandler {
	private MovieMediaFeed feed;
	private FOMEntry entry;

	private Stack stack;
	private boolean isStackReadyForText;

	private Locator locator;
	private InputStream inputStream;
	private boolean isEntryTagStarted;

	// -----

	public SaxFeedUnmarshaller() {
		stack = new Stack();
		isStackReadyForText = false;
	}

	public SaxFeedUnmarshaller(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		this.inputStream = in;
		stack = new Stack();
		parser.parse(in, this);
	}

	public MovieMediaFeed getFeed() {
		return feed;
	}

	// ----- callbacks: -----

	public void setDocumentLocator(Locator rhs) {
		locator = rhs;
	}

	// -----

	public void startElement(String uri, String localName, String qName, Attributes attribs) {

		isStackReadyForText = false;

		// if next element is complex, push a new instance on the stack
		// if element has attributes, set them in the new instance
		if (qName.equals("feed")) {
			stack.push(new MovieMediaFeed());

		} else if (qName.equals("entry")) {
			isEntryTagStarted = true;
			stack.push(new MovieMediaEntry());
		} else if (qName.equals("media:thumbnail")) {
			String tmp = resolveAttrib( uri, "url", attribs, "unknown" );
			MovieMediaEntry entry = (MovieMediaEntry) stack.peek();
			entry.setThumbnail(tmp.toString());
		}
		// else if( localName.equals( "magazine" ) ) {
		// stack.push( new Magazine() );
		//
		// }else if( localName.equals( "article" ) ) {
		// stack.push( new Article() );
		// String tmp = resolveAttrib( uri, "page", attribs, "unknown" );
		// ((Article)stack.peek()).setPage( tmp );
		// }
		// if next element is simple, push StringBuffer
		// this makes the stack ready to accept character text
		else if ((qName.equals("id")) && isEntryTagStarted) {
			stack.push(new StringBuffer());
			isStackReadyForText = true;
		}
		// if none of the above, it is an unexpected element
		else {
			// do nothing
		}
	}

	// -----

	public void endElement(String uri, String localName, String qName) {

		// recognized text is always content of an element
		// when the element closes, no more text should be expected
		isStackReadyForText = false;

		// pop stack and add to 'parent' element, which is next on the stack
		// important to pop stack first, then peek at top element!
		Object tmp = stack.pop();

		if (qName.equals("feed")) {
			feed = (MovieMediaFeed) tmp;

		} else if (qName.equals("entry")) {
			isEntryTagStarted = false;
			((FOMFeed) stack.peek()).addEntry((FOMEntry) tmp);

		} 
		// else if( localName.equals( "magazine" ) ) {
		// ((Catalog)stack.peek()).addMagazine( (Magazine)tmp );
		//
		// }else if( localName.equals( "article" ) ) {
		// ((Magazine)stack.peek()).addArticle( (Article)tmp );
		// }
		// // for simple elements, pop StringBuffer and convert to String
		else if (qName.equals("id") && isEntryTagStarted) {
			FOMEntry entry = (FOMEntry) stack.peek();
			entry.setId(tmp.toString());
		}
		// if none of the above, it is an unexpected element:
		// necessary to push popped element back!
		else {
			stack.push(tmp);
		}
	}

	// -----

	public void characters(char[] data, int start, int length) {

		// if stack is not ready, data is not content of recognized element
		if (isStackReadyForText == true) {
			((StringBuffer) stack.peek()).append(data, start, length);
		} else {
			// read data which is not part of recognized element
		}
	}

	// -----

	private String resolveAttrib(String uri, String localName, Attributes attribs, String defaultValue) {

		String tmp = attribs.getValue(uri, localName);
		return (tmp != null) ? (tmp) : (defaultValue);
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		InputStream is = new ByteArrayInputStream(Downloader.DUMMY_RESPONSE.getBytes());
		SaxFeedUnmarshaller t = new SaxFeedUnmarshaller(is);
		for (Entry e : t.getFeed().getEntries()) {
			System.out.println(e.getId() + ":" + (((MovieMediaEntry)e).getThumbnail()));
		}
	}
}
