package spring.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PhoneCall")
public class PhoneCall {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer CallID;
    private Integer LeadID;
    private String Accountid;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date CallDate;
    private Integer Duration;
    private String Notes;
	public PhoneCall() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCallID() {
		return CallID;
	}
	public void setCallID(Integer callID) {
		CallID = callID;
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
	public Date getCallDate() {
		return CallDate;
	}
	public void setCallDate(Date callDate) {
		CallDate = callDate;
	}
	public Integer getDuration() {
		return Duration;
	}
	public void setDuration(Integer duration) {
		Duration = duration;
	}
	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
	public PhoneCall(Integer callID, Integer leadID, String accountid, Date callDate, Integer duration, String notes) {
		super();
		CallID = callID;
		LeadID = leadID;
		Accountid = accountid;
		CallDate = callDate;
		Duration = duration;
		Notes = notes;
	}	
}
