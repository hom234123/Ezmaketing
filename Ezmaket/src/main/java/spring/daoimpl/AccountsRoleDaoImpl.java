package spring.daoimpl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.dao.AccountsRoleDao;
import spring.entities.AccountRole;

@Repository
public class AccountsRoleDaoImpl implements AccountsRoleDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<AccountRole> getAll() {
		try (Session session = sessionFactory.openSession()) {
			List<AccountRole> result = session.createQuery("from AccountRole", AccountRole.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
			// TODO: handle exception
		}
	}

	@Override
	public void insert(AccountRole accountRole) {

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(accountRole);
		session.getTransaction().commit();

	}

	@Override
	public void update(AccountRole accountRole) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(accountRole);
		session.getTransaction().commit();

	}

	@Override
	public AccountRole getById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		AccountRole ar = session.get(AccountRole.class, id);
		session.getTransaction().commit();
		return ar;
	}

}
