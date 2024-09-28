package spring.entities;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ProductID;
    private String ProductName;
    private Integer CategoryID;
    private String Unit;
    private Integer Quantity;
    private String Note;
    private Integer Price;
    private Integer SalePrice;
    private BigDecimal TotalPrice;
    private Integer Status;
    
    
	public Product(Integer productID, String productName, Integer categoryID, String unit, Integer quantity,
			String note, Integer price, Integer salePrice, BigDecimal totalPrice, Integer status) {
		super();
		ProductID = productID;
		ProductName = productName;
		CategoryID = categoryID;
		Unit = unit;
		Quantity = quantity;
		Note = note;
		Price = price;
		SalePrice = salePrice;
		TotalPrice = totalPrice;
		Status = status;
	}

	public Integer getProductID() {
		return ProductID;
	}

	public void setProductID(Integer productID) {
		ProductID = productID;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public Integer getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(Integer categoryID) {
		CategoryID = categoryID;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public Integer getPrice() {
		return Price;
	}

	public void setPrice(Integer price) {
		Price = price;
	}

	public Integer getSalePrice() {
		return SalePrice;
	}

	public void setSalePrice(Integer salePrice) {
		SalePrice = salePrice;
	}

	public BigDecimal getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		TotalPrice = totalPrice;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public Product() {
		super();
	}

	public void setCategoryID(Category selectedCategory) {
		// TODO Auto-generated method stub
		
	}
	

}
