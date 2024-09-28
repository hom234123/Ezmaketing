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

import spring.dao.PhoneCallDao;

import spring.entities.PhoneCall;
@Repository
public class PhoneCallDaoImpl implements PhoneCallDao {
	@Autowired
	SessionFactory sessionFactory;
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<PhoneCall> getAll() {
		try (Session session = sessionFactory.openSession()) {
			List<PhoneCall> result = session.createQuery("from PhoneCall", PhoneCall.class).getResultList();
			return result;
		} catch (Exception e) {
			// Xử lý exception, ví dụ: ghi log
			e.printStackTrace();
			return Collections.emptyList(); // hoặc trả về một danh sách trống hoặc thông báo lỗi khác tùy thuộc vào yêu
											// cầu của bạn
		}
	}
	@Override
	public void delete(int CallID) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    
	    PhoneCall cd = session.get(PhoneCall.class, CallID);
	    if (cd != null) {
	        session.remove(cd);
	        session.getTransaction().commit();
	    } else {
	        session.getTransaction().rollback();
	        throw new RuntimeException("PhoneCall with ID " + CallID + " not found.");
	    }
		
	}
	@Override
	public void insert(PhoneCall phoneCall) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(phoneCall);
		session.getTransaction().commit();
		
	}
	@Override
	public void update(PhoneCall phoneCall) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(phoneCall);
		session.getTransaction().commit();
		
	}
	@Override
	public Object getByid(int CallID) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		PhoneCall pl = session.get(PhoneCall.class, CallID);
		session.getTransaction().commit();
		return pl;
	}
	@Override
	public List<PhoneCall> findByAccountId(String accountId) {
		TypedQuery<PhoneCall> query = entityManager.createQuery(
		        "FROM PhoneCall l WHERE l.Accountid = :accountId", PhoneCall.class); 
		    query.setParameter("accountId", accountId);
		    List<PhoneCall> results = query.getResultList();

		    // In kết quả để kiểm tra
		    return results;
	}
	@Override
	public boolean isLeadID(int LeadID) {
		String sql = "SELECT COUNT(*) FROM PhoneCall WHERE LeadID = :LeadID";
		Query query = entityManager.createQuery(sql);
		query.setParameter("LeadID", LeadID);
		Long count = (Long) query.getSingleResult();
		return count>0;
	}
	@Override
	public Long countTotalPhonecall(String accountId) {
		String hql = "SELECT COUNT(o) FROM PhoneCall o WHERE o.Accountid = :accountId ";
	    Query query = entityManager.createQuery(hql);
	    query.setParameter("accountId", accountId);
	    return (Long) query.getSingleResult();
	}
	@Override
	public Long totalPhonecall() {
		String query = "SELECT COUNT(o) FROM PhoneCall o";
		return (Long) entityManager.createQuery(query).getSingleResult();
	}
	

}
