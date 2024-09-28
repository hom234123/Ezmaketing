package spring.dao;

import java.util.List;

import spring.entities.OrderDetails;

public interface OrderDetailsDao {
	public List<OrderDetails> getAll();

	public void delete(int OrderDetailID);

	public void insert(OrderDetails orderDetails);

	public void update(OrderDetails orderDetails);

	public Object getByid(int OrderDetailID);
	
	List<OrderDetails> findByOrderId(int OrderID);
}
