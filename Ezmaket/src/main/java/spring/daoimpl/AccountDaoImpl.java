package spring.daoimpl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; // Thêm annotation này

import spring.dao.AccountDao;
import spring.entities.Account;

@Repository
@Transactional 
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private SessionFactory sessionFactory;
    @PersistenceContext
	private EntityManager entityManager;
    @Override
    public Account get(String username) {
    	Session session = sessionFactory.openSession();
        try  { // Sử dụng try-with-resources để tự động đóng session
            Account accounts = (Account)session.createQuery("from Account where username = :username")
                                 .setParameter("username", username)
                                 .uniqueResult();
            return accounts;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	session.close();
		}
        return null;
    }
    @Override
	public List<Account> getAll() {
		try(Session session = sessionFactory.openSession()) {
			List<Account> result = session.createQuery("from Account", Account.class).getResultList();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
			// TODO: handle exception
		}
	}
	@Override
	public void delete(String Accountid) {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    Account at = session.get(Account.class, Accountid);
	    if (at != null) {
	        session.remove(at);
	        session.getTransaction().commit();
	    } else {
	        session.getTransaction().rollback();
	        throw new RuntimeException("Account with ID " + Accountid + " not found.");
	    }
		
	}
	@Override
	public void insert(Account account) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(account);
		session.getTransaction().commit();
		
	}
	@Override
	public void update(Account account) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(account);
		session.getTransaction().commit();
		
	}
	@Override
	public Account getById(String Accountid) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Account ac = session.get(Account.class, Accountid);
		session.getTransaction().commit();
		return ac;
	}
	@Override
	public boolean isUsername(String username) {
		String sql = "SELECT COUNT(*) FROM Account WHERE username = :username";
		Query query = entityManager.createQuery(sql);
		query.setParameter("username", username);
		Long count = (Long) query.getSingleResult();
		return count>0;
	}
	@Override
	public boolean isEmail(String email) {
		String sql = "SELECT COUNT(*) FROM Account WHERE email = :email";
		Query query = entityManager.createQuery(sql);
		query.setParameter("email", email);
		Long count = (Long) query.getSingleResult();
		return count>0;
	}
	@Override
	public boolean isPhone(String phone) {
		String sql = "SELECT COUNT(*) FROM Account WHERE phone = :phone";
		Query query = entityManager.createQuery(sql);
		query.setParameter("phone", phone);
		Long count = (Long) query.getSingleResult();
		return count>0;
	}
	@Override
	public boolean isId(String Accountid) {
		String sql = "SELECT COUNT(*) FROM Account WHERE Accountid = :Accountid";
		Query query = entityManager.createQuery(sql);
		query.setParameter("Accountid", Accountid);
		Long count = (Long) query.getSingleResult();
		return count>0;
	}
	@Override
	public Long countTotalAccount() {
		String query = "SELECT COUNT(o) FROM Account o";
        return (Long) entityManager.createQuery(query).getSingleResult();
	}
}
