package spring.daoimpl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.dao.ProductDao;
import spring.entities.Product;
@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	SessionFactory sessionFactory;
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Product> getAll() {
		try(Session session = sessionFactory.openSession()) {
			List<Product> result= session.createQuery("from Product", Product.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	@Override
	public void delete(int ProductID) {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    Product pt = session.get(Product.class, ProductID);
	    if (pt != null) {
	        session.remove(pt);
	        session.getTransaction().commit();
	    } else {
	        session.getTransaction().rollback();
	        throw new RuntimeException("Product with ID " + ProductID + " not found.");
	    }
		
	}
	@Override
	public void insert(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
		
	}
	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(product);
		session.getTransaction().commit();
		
	}
	@Override
	public Object getByid(int ProductID) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Product pt = session.get(Product.class, ProductID);
		session.getTransaction().commit();
		return pt;
	}
	@Override
	public Long countTotalProduct() {
		String query = "SELECT COUNT(o) FROM Product o";
        return (Long) entityManager.createQuery(query).getSingleResult();
	}

}
