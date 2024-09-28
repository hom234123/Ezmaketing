package spring.dao;

import java.util.List;

import spring.entities.PhoneCall;

public interface PhoneCallDao {
	public List<PhoneCall> getAll();

	public void delete(int CallID);

	public void insert(PhoneCall phoneCall);

	public void update(PhoneCall phoneCall);

	public Object getByid(int CallID);

	List<PhoneCall> findByAccountId(String accountId);
	
	public boolean isLeadID(int LeadID);
	
	Long countTotalPhonecall(String accountId);
	
	Long  totalPhonecall();
	
	
}
