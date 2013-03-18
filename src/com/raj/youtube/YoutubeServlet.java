package com.raj.youtube;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.common.base.StringUtil;
import com.raj.media.UniversalMediaEntry;
import com.raj.media.UniversalMediaFeed;
import com.raj.media.entity.MediaEntry;
import com.raj.media.entity.MediaEntryDAO;
import com.raj.youtube.media.search.SearchCriteria;

@SuppressWarnings("serial")
public class YoutubeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		SearchCriteria searchCriteria = getSearchCriteria(req);
		if (searchCriteria == null) {
			// home page
			List<MediaEntry> entries = MediaEntryDAO.getLatestEntries(0, 20);
			List<UniversalMediaEntry> universalMediaList = new ArrayList();
			for (MediaEntry entry : entries) {
				universalMediaList.add(new UniversalMediaEntry(entry));
			}
			req.setAttribute("entries", new UniversalMediaFeed(universalMediaList).getEntries());
		} else {
			YoutubeServiceProvider provider = new YoutubeServiceProvider();
			UniversalMediaFeed videoFeed = provider.query(searchCriteria);
			if (videoFeed != null) {
				req.setAttribute("entries", videoFeed.getEntries());
			}	
		}
		try {
			// out.println(videoFeed.getTitle().getPlainText());
			// printVideoFeed(videoFeed, out);
			req.getRequestDispatcher("/results.jsp").include(req, resp);
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		// MediaEntry todo = new MediaEntry();
		//
		// todo.setMediaId("This is my todo");
		// EntityManager em = EMFService.get().createEntityManager();
		// List<MediaEntry> todos = null;
		// try {
		// em.persist(todo);
		// Query q = em.createQuery("select t from MediaEntry t");
		// todos = new ArrayList(q.getResultList());
		// } finally {
		// em.close();
		// }
		//
		// resp.setContentType("text/plain");
		// if (todos != null) {
		// resp.getWriter().println(
		// "Hello, JPA. We have " + todos.size()
		// + " number of entries.");
		// } else {
		// resp.getWriter().println("Should not happen");
		// }

	}

	private SearchCriteria getSearchCriteria(HttpServletRequest req) {
		SearchCriteria searchCriteria = null;
		String queryString = req.getParameter("q");
		if (!StringUtil.isEmpty(queryString)) {

			String lang = req.getParameter("language");
			queryString = StringUtil.isEmpty(queryString) ? "malayalam movies" : lang + " movies " + queryString;
			searchCriteria = new SearchCriteria();
			searchCriteria.setCategory(lang);
			searchCriteria.setQueryString(queryString);
		}
		return searchCriteria;
	}

	private void voidMethod(VideoFeed videoFeed) {
		System.out.println(videoFeed);
		System.out.println(videoFeed.getEntries().get(0));
	}

	private void printVideoFeed(VideoFeed videoFeed, PrintWriter out) {
		List<VideoEntry> allVideos = videoFeed.getEntries();
		Iterator<VideoEntry> itAllVideos = allVideos.iterator();
		while (itAllVideos.hasNext()) {

			VideoEntry oneVideo = itAllVideos.next();
			oneVideo.getMediaGroup().getDuration();

			TextConstruct oneVideoTitle = oneVideo.getTitle();
			// Print titles of all videos:
			out.println(oneVideoTitle.getPlainText());
			if (oneVideo.getRating() != null) {
				out.println(oneVideo.getRating().getAverage());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new YoutubeServlet().doGet(null, null);
	}
}
