package spring.daoimpl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.dao.OrderDetailsDao;
import spring.entities.OrderDetails;
@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao{
	@Autowired
	SessionFactory sessionFactory;
	@PersistenceContext
    private EntityManager entityManager;
	@Override
	public List<OrderDetails> getAll() {
		try(Session session = sessionFactory.openSession()) {
			List<OrderDetails> result= session.createQuery("from OrderDetails", OrderDetails.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	@Override
	public void delete(int OrderDetailID) {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    OrderDetails pt = session.get(OrderDetails.class, OrderDetailID);
	    if (pt != null) {
	        session.remove(pt);
	        session.getTransaction().commit();
	    } else {
	        session.getTransaction().rollback();
	        throw new RuntimeException("OrderDetails with ID " + OrderDetailID + " not found.");
	    }
		
	}
	@Override
	public void insert(OrderDetails orderDetails) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(orderDetails);
		session.getTransaction().commit();
		
	}
	@Override
	public void update(OrderDetails orderDetails) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(orderDetails);
		session.getTransaction().commit();
		
	}
	@Override
	public Object getByid(int OrderDetailID) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		OrderDetails ors = session.get(OrderDetails.class, OrderDetailID);
		session.getTransaction().commit();
		return ors;
	}
	@Override
	public List<OrderDetails> findByOrderId(int OrderID) {
		String jpql = "SELECT od FROM OrderDetails od WHERE od.OrderID  = :orderId";
	    TypedQuery<OrderDetails> query = entityManager.createQuery(jpql, OrderDetails.class);
	    query.setParameter("orderId", OrderID);
	    return query.getResultList();
	}

}
