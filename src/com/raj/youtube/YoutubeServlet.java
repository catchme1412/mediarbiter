package com.raj.youtube;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.common.base.StringUtil;
import com.raj.media.UnversalMediaFeed;

@SuppressWarnings("serial")
public class YoutubeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("Hello, world");
//		YouTubeService service = getYoutubeService();
//		YouTubeQuery query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
//		// order results by the number of views (most viewed first)
//		query.setOrderBy(YouTubeQuery.OrderBy.RELEVANCE);

		// search for something and include restricted content in the search
		// results
		String queryString = req.getParameter("q");

		queryString = StringUtil.isEmpty(queryString) ? "malayalam movies" : queryString;
//		query.setFullTextQuery(queryString);
//		query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);

		try {
			YoutubeServiceProvider provider = new YoutubeServiceProvider();
			VideoFeed videoFeed = provider.query(queryString);
			voidMethod(videoFeed);
			req.setAttribute("videoFeed", new UnversalMediaFeed(videoFeed));
			out.println(videoFeed.getTitle().getPlainText());
			printVideoFeed(videoFeed, out);
			req.getRequestDispatcher("/results.jsp").include(req, resp);
		} catch (Exception e) {
			e.printStackTrace(out);
		}
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
