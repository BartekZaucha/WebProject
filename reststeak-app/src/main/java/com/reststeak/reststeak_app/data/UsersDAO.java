package com.reststeak.reststeak_app.data;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reststeak.reststeak_app.model.Users;

@Stateless
@LocalBean
public class UsersDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Users> getAllUsers() {
		Query query = em.createQuery("Select u FROM Users u");
		return query.getResultList();
	}

	public Users getUserByUserName(String username) {
		return em.find(Users.class, username);
	}

	public void saveUser(Users user) {
		em.persist(user);
	}

	public void updateUser(Users user) {
		em.merge(user);
	}

	public void deleteUser(String user) {
		em.remove(getUserByUserName(user));
	}

}