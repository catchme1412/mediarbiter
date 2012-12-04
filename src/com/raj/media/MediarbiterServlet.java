package com.raj.media;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

@SuppressWarnings("serial")
public class MediarbiterServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		try {
			resp.setContentType("text/html");
			out.println("Hello, world");
			Feed result = YoutubeMediaManager.extract(req.getParameter("q"));
			out.println("Search result :" + req.getParameter("q"));
			req.setAttribute("searchResults", result);
//			
			for (Entry m : result.getEntries()) {
				out.println(m.getId() + "<img src='" + ((MovieMediaEntry)m).getThumbnail() + "'/>");
				//out.print(":" + m.getRating() + "\n");
			}
			
			req.setAttribute("feed", result);
			result.getEntries();
			req.getRequestDispatcher("results.jsp").include(req, resp);
//			out.println("Hello, world");
			
		} catch (Throwable t) {
			for (StackTraceElement  st : t.getStackTrace()) {
				out.println(st.getFileName() + ":" + st.getLineNumber() + "<br>");
			}
		}
	}
}
