package com.grovs.model;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="root_category")
public class RootCategory {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name",nullable=false,length=50)
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="root_category_id")
	private Set<Category> categories=new TreeSet<>((p1,p2)->p1.getId()<p2.getId()?1:-1);
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
	public Set<Category> getCategories() {
		return categories;
	}
	@Override
	public String toString() {
		return "RootCategory [id=" + id + ", name=" + name + ", categories=" + categories + "]";
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	
	
}
