package com.grovs.service;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.grovs.model.Category;
import com.grovs.model.Product;
import com.grovs.model.RootCategory;

@Service
public class HomeService {

	public TreeSet<Product> mapCategoriesToProducts(int categoryId, Set<Product> productsSet) {

		return productsSet.parallelStream().filter(product -> product.getCategoryId() == categoryId)
				.collect(Collectors.toCollection(() -> new TreeSet<>()));
	}

	public Map<RootCategory, Set<Map<Category, Set<Product>>>> iterateRootCatogory(Set<RootCategory> rootCategorySet,
			Set<Product> productsSet) {
		Map<RootCategory, Set<Map<Category, Set<Product>>>> rootCategoryMap = new LinkedHashMap<>();
		Iterator<RootCategory> rootCategoryIter = rootCategorySet.iterator();
		while (rootCategoryIter.hasNext()) {
			RootCategory rootCategoryObject = rootCategoryIter.next();

			Set<Category> categorySetObject = (LinkedHashSet<Category>) rootCategoryObject.getCategories();

			Set<Map<Category, Set<Product>>> categoryProductsMapSet = new LinkedHashSet<>();

			Iterator<Category> categoryIter = categorySetObject.iterator();
			while (categoryIter.hasNext()) {

				Map<Category, Set<Product>> mapCategoryProductsById = new LinkedHashMap<>();

				Category categoryObject = categoryIter.next();
				mapCategoryProductsById.put(categoryObject,
						mapCategoriesToProducts(categoryObject.getId(), productsSet));

				categoryProductsMapSet.add(mapCategoryProductsById);
			}
			rootCategoryMap.put(rootCategoryObject, categoryProductsMapSet);

		}

		return rootCategoryMap;
	}

}