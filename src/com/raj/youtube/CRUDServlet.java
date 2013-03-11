package com.raj.youtube;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.raj.media.entity.MediaEntry;
import com.raj.media.entity.MediaEntryDAO;
import com.raj.youtube.media.search.SearchCriteria;

@SuppressWarnings("serial")
public class CRUDServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	}

	public static void main(String[] args) throws IOException {
		new CRUDServlet().doGet(null, null);
	}
}
