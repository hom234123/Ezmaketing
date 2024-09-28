package spring.dao;

import java.util.List;

import spring.entities.Lead;

public interface LeadDao {
	public List<Lead> getAll();

	public void delete(int leadID);

	public void insert(Lead lead);

	public void update(Lead lead);

	public Lead getByid(int leadID);

	public boolean isPhonenumber(String Phone);

	Long countTotalPhone();

	List<Lead> findByAccountId(String accountId);
	
	Long countPhoneAc(String accountId);
	
	Long countPhonesale(String accountId);
	
	Long phonecallstatus();
}
