package spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name = "Lead")
public class Lead {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer LeadID;
    private String Accountid;
    private String FullName;
    private String Address;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Dayup;
    @Pattern(regexp = "^\\d{8,10}$",message = "Số điện thoại từ 8-10 số")
    @NotEmpty(message = "Hãy điền số điện thoại")
    private String Phone;
    private String Notes;
    private int Status;
		
	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public Lead() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getLeadID() {
		return LeadID;
	}

	public void setLeadID(Integer leadID) {
		LeadID = leadID;
	}

	public String getAccountid() {
		return Accountid;
	}

	public void setAccountid(String accountid) {
		Accountid = accountid;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}


	public Date getDayup() {
		return Dayup;
	}

	public void setDayup(Date dayup) {
		Dayup = dayup;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}

	public Lead(Integer leadID, String accountid, String fullName, String address, String phone, String notes
			,int status) {
		super();
		LeadID = leadID;
		Accountid = accountid;
		FullName = fullName;
		Address = address;
		Phone = phone;
		Notes = notes;
		Status = status;
	}

	

	
}
