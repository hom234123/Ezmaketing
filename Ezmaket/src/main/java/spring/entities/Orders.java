package spring.entities;

import java.math.BigDecimal;
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
@Table(name = "Orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer OrderID;
    private String Accountid;
    private String FullName;
    private String Address;
    private String Phone;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date OrderDate;
    private BigDecimal TotalAmount;
    private String Note;
    private Integer Status;

	public Orders() {
		
		// TODO Auto-generated constructor stub
	}

	public Integer getOrderID() {
		return OrderID;
	}

	public void setOrderID(Integer orderID) {
		OrderID = orderID;
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

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public BigDecimal getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		TotalAmount = totalAmount;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public Orders(Integer orderID, String accountid, String fullName, String address, String phone,
			Date orderDate, BigDecimal totalAmount, String note, Integer status) {
		super();
		OrderID = orderID;
		Accountid = accountid;
		FullName = fullName;
		Address = address;
		Phone = phone;
		OrderDate = orderDate;
		TotalAmount = totalAmount;
		Note = note;
		Status = status;
	}
}
