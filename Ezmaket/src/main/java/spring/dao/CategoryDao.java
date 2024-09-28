package spring.dao;

import java.util.List;

import spring.entities.Category;

public interface CategoryDao {
	public List<Category> getAll();
	public void delete(int CategoryID);
	public void insert(Category category);
	public void update (Category category);
	public Category getById (int CategoryID);
}
