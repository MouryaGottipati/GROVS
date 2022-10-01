package com.grovs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grovs.model.Category;
import com.grovs.model.RootCategory;

public interface IRootCategoryDao extends JpaRepository<RootCategory, Integer>{

	@Query(value="select resultant_products.id,resultant_products.root_category_name,discount,image,name,base_price,sale_price,rating from  (select products.id as id,categories.root_category_name,discount,image,name,base_price,(base_price-0.01*(base_price)*(discount)) as sale_price  from products inner join (select category.id,root_category.name as root_category_name from category join root_category on category.root_category_id=?1 and root_category.id=?1) as categories on categories.id=?1 and products.category_id=?1 and products.stock>0) as resultant_products left JOIN (select AVG(rating) as rating,reviews.products_id as products_id  from reviews GROUP by reviews.products_id) as review on review.products_id=resultant_products.id",nativeQuery=true)
	List<Object> queryToGetRootCategoryProducts(int id);


}
