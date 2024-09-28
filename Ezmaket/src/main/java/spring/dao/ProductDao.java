package spring.dao;

import java.util.List;

import spring.entities.Product;

public interface ProductDao {
	public List<Product> getAll();
	public void delete (int ProductID);
	public void insert (Product product);
	public void update (Product product);
	public Object getByid (int ProductID);
	Long countTotalProduct();
}
