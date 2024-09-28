package spring.daoimpl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.dao.CategoryDao;
import spring.entities.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Category> getAll() {
		try (Session session = sessionFactory.openSession()) {
			List<Category> result = session.createQuery("from Category", Category.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public void delete(int CategoryID) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Category pt = session.get(Category.class, CategoryID);
		if (pt != null) {
			session.remove(pt);
			session.getTransaction().commit();
		} else {
			session.getTransaction().rollback();
			throw new RuntimeException("Category with ID " + CategoryID + " not found.");
		}
	}

	@Override
	public void insert(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(category);
		session.getTransaction().commit();

	}

	@Override
	public void update(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(category);
		session.getTransaction().commit();

	}

	@Override
	public Category getById(int CategoryID) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Category ct = session.get(Category.class, CategoryID);
		session.getTransaction().commit();
		return ct;
	}

}
