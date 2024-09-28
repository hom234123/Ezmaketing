package spring.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="roleid")
	
	private int roleid;
	@Column(name="rolename")
	private String rolename;
	
	@OneToMany(mappedBy = "role")
	private Set<AccountRole> accountroles;
	
	public Set<AccountRole> getAccountRoles() {
		return accountroles;
	}
	public void setAccountsRoles(Set<AccountRole> accountroles) {
		this.accountroles = accountroles;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	@Override
    public String toString() {
        return this.rolename;
    }
	
}
