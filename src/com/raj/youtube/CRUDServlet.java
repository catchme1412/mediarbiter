package com.raj.youtube;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raj.media.entity.EMFService;

@SuppressWarnings("serial")
public class CRUDServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String action = req.getParameter("action");
		String model = req.getParameter("model");
		PrintWriter out = resp.getWriter();
		model = "com.raj.media.entity.MediaEntry";
		if ("list".equals(action)) {
			List r = list(model);
			for (Object o : r) {
				out.println(o);
			}
		} else if ("deleteAll".equals(action)) {
			deleteAll(model);
		}

	}

	private void deleteAll(String model) {
		try {
			Class clz = Class.forName(model);
			EntityManager em = EMFService.get().createEntityManager();
			em.getTransaction().begin();
			int deletedCount = em.createQuery("DELETE FROM MediaEntry")
					.executeUpdate();
			em.getTransaction().commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List list(String model) {
		try {
			Class clz = Class.forName(model);
			EntityManager em = EMFService.get().createEntityManager();
			CriteriaQuery query = em.getCriteriaBuilder().createQuery(clz);
			query.from(clz);
			List r = em.createQuery(query).getResultList();
			return r;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		new CRUDServlet().list("com.raj.media.entity.MediaEntry");
	}
}
