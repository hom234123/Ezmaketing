package spring.dao;

import java.util.List;

import spring.entities.AccountRole;

public interface AccountsRoleDao {
	public List<AccountRole> getAll();

	public void insert(AccountRole accountRole);

	public void update(AccountRole accountRole);
	
	public AccountRole getById (Long id);
}
