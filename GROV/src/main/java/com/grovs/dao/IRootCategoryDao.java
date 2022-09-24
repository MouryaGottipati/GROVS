package com.grovs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grovs.model.Category;
import com.grovs.model.RootCategory;

public interface IRootCategoryDao extends JpaRepository<RootCategory, Integer>{

	@Query(value="select distinct resultant_products.id,resultant_products.root_category_name,discount,image,name,base_price,sale_price,AVG(rating) from (select products.id as id,subcategory.root_category_name,discount,image,name,base_price,(base_price-0.01*(base_price)*(discount)) as sale_price  from products inner join (select DISTINCT category.id,root_category.name as root_category_name from category join root_category on root_category_id=?1) as subcategory WHERE subcategory.id=products.category_id) as resultant_products left outer join reviews on resultant_products.id=reviews.products_id;",nativeQuery=true)
	List<Object> queryToRootCategoryProducts(int id);


}
