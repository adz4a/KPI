package com.program.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="category_id")
	private Integer categoryId;

	private String categoryName;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Status> statuses =new ArrayList<>();


	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public Category(Integer categoryId, String categoryName, List<Status> statuses) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.statuses = statuses;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", statuses=" + statuses + "]";
	}

}
