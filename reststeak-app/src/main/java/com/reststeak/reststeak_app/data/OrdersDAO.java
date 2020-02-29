package com.reststeak.reststeak_app.data;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reststeak.reststeak_app.model.Orders;

@Stateless
@LocalBean
public class OrdersDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<Orders> getAllOrders() {
		Query query = em.createQuery("Select u FROM Orders u");
		return query.getResultList();
	}
	
	public Orders getOrderById(int id) {
		return em.find(Orders.class, id);
	}
	
	public void saveOrder(Orders order) {
		em.persist(order);
	}
	
	public void updateOrder(Orders order) {
		em.merge(order);
	}

	public void deleteOrder(int id) {
		em.remove(getOrderById(id));
	}
}
