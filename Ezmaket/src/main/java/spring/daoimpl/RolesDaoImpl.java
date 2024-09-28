package spring.daoimpl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.dao.RolesDao;
import spring.entities.Role;

@Repository
public class RolesDaoImpl implements RolesDao {
@Autowired
SessionFactory sessionFactory;

@Override
public List<Role> getAll() {
	try (Session session = sessionFactory.openSession()){
		List<Role> result = session.createQuery("from Role", Role.class).getResultList();
		return result;
	} catch (Exception e) {
		e.printStackTrace();
		return Collections.emptyList();
	}
}

@Override
public void delete(Long Roleid) {
	// TODO Auto-generated method stub
	Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    
    Role pt = session.get(Role.class, Roleid);
    if (pt != null) {
        session.remove(pt);
        session.getTransaction().commit();
    } else {
        session.getTransaction().rollback();
        throw new RuntimeException("Role with ID " + Roleid + " not found.");
    }
}

@Override
public void insert(Role role) {
	Session session = sessionFactory.getCurrentSession();
	session.beginTransaction();
	session.save(role);
	session.getTransaction().commit();
	
}

@Override
public void update(Role role) {
	Session session = sessionFactory.getCurrentSession();
	session.beginTransaction();
	session.update(role);
	session.getTransaction().commit();
	
}

@Override
public Role getByid(int Roleid) {
	Session session = sessionFactory.getCurrentSession();
	session.beginTransaction();
	Role ro = session.get(Role.class, Roleid);
	session.getTransaction().commit();
			
	return ro;
}

}
