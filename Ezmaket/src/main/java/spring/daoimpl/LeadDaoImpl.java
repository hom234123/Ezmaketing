package spring.daoimpl;

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

import spring.dao.LeadDao;
import spring.entities.Lead;
@Repository
public class LeadDaoImpl implements LeadDao {
	@Autowired
	SessionFactory sessionFactory;
	@PersistenceContext
	private EntityManager entityManager;
	
	//lấy ra danh sách
	@Override	
	public List<Lead> getAll() {
		 Session session = sessionFactory.openSession();
	        try {
	            List<Lead> result = session.createQuery("from Lead", Lead.class).getResultList();
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return Collections.emptyList();
	        }
	}
	
	//xóa một đối tượng theo id
	@Override
	public void delete(int LeadID) {
		 
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    Lead ld = session.get(Lead.class, LeadID);
	    if (ld != null) {
	        session.remove(ld);
	        session.getTransaction().commit();
	    } else {
	        session.getTransaction().rollback();
	        throw new RuntimeException("Lead with ID " + LeadID + " not found.");
	    }
	}

	
		// thêm mới lead
	@Override
	public void insert(Lead lead) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(lead);
		session.getTransaction().commit();
		
	}

	@Override
	public void update(Lead lead) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(lead);
		session.getTransaction().commit();
		
	}

	@Override
	public Lead getByid(int leadID) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Lead le = session.get(Lead.class, leadID);
		session.getTransaction().commit();
		return le;
	}

	@Override
	public boolean isPhonenumber(String Phone) {
		String sql = "SELECT COUNT(*) FROM Lead WHERE Phone = :Phone";
		Query query = entityManager.createQuery(sql);
		query.setParameter("Phone", Phone);
		Long count = (Long) query.getSingleResult();
		return count>0;
		
	}

	@Override
	public Long countTotalPhone() {
		String query = "SELECT COUNT(l) FROM Lead l";
        return (Long) entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public List<Lead> findByAccountId(String accountId) {
		TypedQuery<Lead> query = entityManager.createQuery(
		        "FROM Lead l WHERE l.Accountid = :accountId", Lead.class); 
		    query.setParameter("accountId", accountId);
		    List<Lead> results = query.getResultList();

		    // In kết quả để kiểm tra
		    return results;
    }

	@Override
	public Long countPhoneAc(String accountId) {
		String hql = "SELECT COUNT(l) FROM Lead l WHERE l.Accountid = :accountId";
	    Query query = entityManager.createQuery(hql);
	    query.setParameter("accountId", accountId);
	    return (Long) query.getSingleResult();
	}

	@Override
	public Long countPhonesale(String accountId) {
		String hql = "SELECT COUNT(l) FROM Lead l WHERE l.Accountid = :accountId AND l.Status = 1";
	    Query query = entityManager.createQuery(hql);
	    query.setParameter("accountId", accountId);
	    return (Long) query.getSingleResult(); 
	}

	@Override
	public Long phonecallstatus() {
		String hql = "SELECT COUNT(l) FROM Lead l WHERE l.Status = 1";
	    Query query = entityManager.createQuery(hql);
	    return (Long) query.getSingleResult();
	}
	

	
}
