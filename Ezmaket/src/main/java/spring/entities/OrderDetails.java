package spring.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer OrderDetailID;
    private Integer OrderID;
    private Integer ProductID;
    private Integer Quantity;
    private BigDecimal Subtotal;

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getOrderDetailID() {
		return OrderDetailID;
	}

	public void setOrderDetailID(Integer orderDetailID) {
		OrderDetailID = orderDetailID;
	}

	public Integer getOrderID() {
		return OrderID;
	}

	public void setOrderID(Integer orderID) {
		OrderID = orderID;
	}

	public Integer getProductID() {
		return ProductID;
	}

	public void setProductID(Integer productID) {
		ProductID = productID;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public BigDecimal getSubtotal() {
		return Subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		Subtotal = subtotal;
	}

	public OrderDetails(Integer orderDetailID, Integer orderID, Integer productID, Integer quantity,
			BigDecimal subtotal) {
		super();
		OrderDetailID = orderDetailID;
		OrderID = orderID;
		ProductID = productID;
		Quantity = quantity;
		Subtotal = subtotal;
	}

	

	

}
