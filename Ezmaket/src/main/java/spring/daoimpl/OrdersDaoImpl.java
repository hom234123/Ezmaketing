package spring.daoimpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.dao.OrdersDao;
import spring.entities.Orders;

@Repository
public class OrdersDaoImpl implements OrdersDao {
	@Autowired
	SessionFactory sessionFactory;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Orders> getAll() {
		try (Session session = sessionFactory.openSession()) {
			List<Orders> result = session.createQuery("from Orders", Orders.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public void delete(int OrderID) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Orders pt = session.get(Orders.class, OrderID);
		if (pt != null) {
			session.remove(pt);
			session.getTransaction().commit();
		} else {
			session.getTransaction().rollback();
			throw new RuntimeException("Orders with ID " + OrderID + " not found.");
		}

	}

	@Override
	public void insert(Orders orders) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(orders);
		session.getTransaction().commit();

	}

	@Override
	public void update(Orders orders) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(orders);
		session.getTransaction().commit();

	}

	@Override
	public Orders getByid(int OrderID) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Orders or = session.get(Orders.class, OrderID);
		session.getTransaction().commit();
		return or;
	}

	@Override
	public boolean isPhone(String Phone) {
		String sql = "SELECT COUNT(*) FROM Orders WHERE Phone = :Phone";
		Query query = entityManager.createQuery(sql);
		query.setParameter("Phone", Phone);
		Long count = (Long) query.getSingleResult();
		return count > 0;
	}

	@Override
	public Long countTotalOrders() {
		String query = "SELECT COUNT(o) FROM Orders o";
		return (Long) entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public List<Orders> findOrdersByPhoneList(List<String> phoneList) {
		String jpql = "SELECT o FROM Orders o WHERE o.Phone IN :phoneList";
		TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
		query.setParameter("phoneList", phoneList);
		return query.getResultList();
	}

	@Override
	public List<Orders> findOrdersByAccountId(String accountId) {
		String jpql = "SELECT o FROM Orders o WHERE o.Phone IN "
				+ "(SELECT l.Phone FROM Lead l WHERE l.Accountid = :accountId)";
		TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
		query.setParameter("accountId", accountId);
		return query.getResultList();
	}

	@Override
	public List<Orders> findByAccountId(String accountId) {
		String jpql = "SELECT o FROM Orders o WHERE o.Accountid = :accountId";
		TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
		query.setParameter("accountId", accountId);
		return query.getResultList();
	}

	@Override
	public Double findTotalAmountByAccountId(String accountId) {
		String hql = "SELECT SUM(o.TotalAmount) FROM Orders o WHERE o.Accountid = :accountId AND o.Status = 1";
		Query query = entityManager.createQuery(hql);
		query.setParameter("accountId", accountId);
		BigDecimal totalAmount = (BigDecimal) query.getSingleResult();
		return totalAmount != null ? totalAmount.doubleValue() : 0.0;
	}

	@Override
	public Long countOrdersByAccountId(String accountId) {
		String hql = "SELECT COUNT(o) FROM Orders o WHERE o.Accountid = :accountId AND o.Status = 1";
		Query query = entityManager.createQuery(hql);
		query.setParameter("accountId", accountId);
		return (Long) query.getSingleResult();
	}

	@Override
	public Long countPhoneOrStatus(String accountId, int status) {
		String jpql = "SELECT COUNT(o) FROM Orders o WHERE o.Phone IN "
				+ "(SELECT l.Phone FROM Lead l WHERE l.Accountid = :accountId) AND o.Status = :status";
		TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
		query.setParameter("accountId", accountId);
		query.setParameter("status", status);
		return query.getSingleResult();
	}
	@Override
	public Long sumTotalAmountOrs(String accountId, int status) {
		 String hql = "SELECT SUM(o.TotalAmount) FROM Orders o WHERE o.Phone IN" +"(SELECT l.Phone FROM Lead l WHERE l.Accountid = :accountId) AND o.Status = :status";
		    Query query = entityManager.createQuery(hql);
		    query.setParameter("accountId", accountId);
		    query.setParameter("status", status);

		    // Thực hiện truy vấn và xử lý kết quả
		    BigDecimal totalAmount = (BigDecimal) query.getSingleResult();
		    return totalAmount != null ? totalAmount.longValue() : 0L;
	}

	@Override
	public Double sumTotalAmount() {
		String hql = "SELECT SUM(o.TotalAmount) FROM Orders o WHERE o.Status = 1";
	    Query query = entityManager.createQuery(hql);
	    BigDecimal totalAmount = (BigDecimal) query.getSingleResult();
	    return totalAmount != null ? totalAmount.doubleValue() : 0.0;
	}

	@Override
	public Long orderStatus() {
		String hql = "SELECT COUNT(o) FROM Orders o WHERE o.Status = 0";
	    Query query = entityManager.createQuery(hql);
	    return (Long) query.getSingleResult();
	}

}
