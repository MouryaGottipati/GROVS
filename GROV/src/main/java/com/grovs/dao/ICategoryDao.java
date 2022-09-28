package com.grovs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grovs.model.Category;

public interface ICategoryDao extends JpaRepository<Category, Integer> {

	public List<Category> findAllByRootCategoryId(int id);

	@Query(value = "select distinct resultant_products.id,resultant_products.category_name,discount,image,name,base_price,sale_price,AVG(rating) from (select products.id as id,subcategory.category_name,discount,image,name,base_price,(base_price-0.01*(base_price)*(discount)) as sale_price  from products inner join (select DISTINCT category.id,category.name as category_name from category join root_category on root_category_id=?1) as subcategory WHERE subcategory.id=products.category_id and products.stock>0) as resultant_products left outer join reviews on resultant_products.id=reviews.products_id;", nativeQuery = true)
	public List fetchSubCategoryProducts(int id);

}
