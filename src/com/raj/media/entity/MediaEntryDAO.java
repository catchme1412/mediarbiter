package com.raj.media.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MediaEntryDAO {

	public static void create(MediaEntry entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.persist(entry);
		} finally {
			em.close();
		}
	}

	public static void delete(MediaEntry entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.remove(entry);
		} finally {
			em.close();
		}
	}

	public static MediaEntry findById(MediaEntry entry) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Query q = em.createQuery("select t from MediaEntry t where t.id=?");
			q.setParameter(1, entry.getId());
			return (MediaEntry) q.getResultList().get(0);
		} finally {
			em.close();
		}
	}

	public static List<MediaEntry> getLatestEntries() {
		EntityManager em = EMFService.get().createEntityManager();
		List<MediaEntry> todos = null;
		try {
			Query q = em.createQuery("select t from MediaEntry t");
			todos = new ArrayList(q.getResultList());
		} finally {
			em.close();
		}
		return todos;
	}
}
