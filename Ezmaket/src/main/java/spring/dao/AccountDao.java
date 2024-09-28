package spring.dao;

import java.util.List;

import spring.entities.Account;

public interface AccountDao {
	public Account get(String username);

	public List<Account> getAll();

	public void delete(String Accountid);

	public void insert(Account account);

	public void update(Account account);

	public Account getById(String Accountid);

	public boolean isUsername(String username);

	public boolean isEmail(String email);

	public boolean isPhone(String phone);

	public boolean isId(String Accountid);
	
	Long countTotalAccount();
}
