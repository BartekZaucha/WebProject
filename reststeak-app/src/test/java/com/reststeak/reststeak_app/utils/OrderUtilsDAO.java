package com.reststeak.reststeak_app.utils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class OrderUtilsDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void deleteTable() {
		entityManager.createQuery("DELETE FROM Orders").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE users AUTO_INCREMENT=1").executeUpdate();
	}
}
