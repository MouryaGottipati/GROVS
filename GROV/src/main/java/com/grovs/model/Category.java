
package com.grovs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "category")
public class Category {

	@Id

	@Column(name = "id")
	private int id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "root_category_id", nullable = false)
	private int rootCategoryId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", rootCategoryId=" + rootCategoryId + "]";
	}

	public int getRootCategoryId() {
		return rootCategoryId;
	}

	public void setRootCategoryId(int rootCategoryId) {
		this.rootCategoryId = rootCategoryId;
	}

}
