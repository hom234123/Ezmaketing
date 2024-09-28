package spring.dao;

import java.util.List;

import spring.entities.Orders;

public interface OrdersDao {
	public List<Orders> getAll();

	public void delete(int OrderID);

	public void insert(Orders orders);

	public void update(Orders orders);

	public Orders getByid(int OrderID);

	public boolean isPhone(String Phone);
	
	Double sumTotalAmount();

	Long countTotalOrders();

	List<Orders> findOrdersByPhoneList(List<String> phoneList);

	List<Orders> findOrdersByAccountId(String accountId);

	List<Orders> findByAccountId(String accountId);
	
	Double findTotalAmountByAccountId(String accountId);
	
	Long countOrdersByAccountId(String accountId);
	
	Long countPhoneOrStatus(String accountId, int status);
	
	Long sumTotalAmountOrs(String accountId, int status);
	
	Long orderStatus ();
}
