package spring.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Accounts")
public class Account {
	@Id
	@Column(name="accountid")
	private String accountid;
	@Column(name="username")
	private String username;
	@Column(name="fullname")
	private String fullname;
	@Column (name="gender")
	private int  gender;
	@Column(name="bidthay")
	@Temporal(value = TemporalType.DATE)
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bidthay;
	@Column(name="address")
	private String address;
	@Column(name="email")
	@Email(message = "Nhập đúng định dạng email")
	@NotEmpty(message = "Hãy nhập email")
	private String email;
	@Column(name="phone")
	@Pattern(regexp = "^\\d{8,10}$",message = "Số điện thoại từ 8-10 số")
    @NotEmpty(message = "Hãy điền số điện thoại")
	private String phone;
	@Column(name="password")
	private String passWord;
	@Column(name="note")
	private String note;
	@Column(name="status")
	private int status;
	@OneToMany(mappedBy ="account",fetch = FetchType.EAGER)
	private Set<AccountRole> accountroles;
	
	public Set<AccountRole> getAccountRoles() {
		return accountroles;
	}

	public void setAccountsRoles(Set<AccountRole> accountroles) {
		this.accountroles = accountroles;
	}

	public Account() {
		
		
	}
	
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Date getBidthay() {
		return bidthay;
	}
	public void setBidthay(Date bidthay) {
		this.bidthay = bidthay;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	 @Override
	    public String toString() {
	        return this.accountid; 
	    }
}
