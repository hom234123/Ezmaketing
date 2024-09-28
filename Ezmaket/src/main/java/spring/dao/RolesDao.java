package spring.dao;

import java.util.List;

import spring.entities.Role;

public interface RolesDao {
	public List<Role> getAll();

	public void delete(Long Roleid);

	public void insert(Role role);

	public void update(Role role);

	public Role getByid(int Roleid);

}
