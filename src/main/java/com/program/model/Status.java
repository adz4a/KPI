package com.program.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="status_id")
	private Integer statusId;


	private String statusName;
	private String statusCategory;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
	private List<Event> events=new ArrayList<>();


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId")
	@JsonIgnore
	private Category category;


	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("status_id")
	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@JsonProperty("status_name")
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@JsonProperty("status_category")
	public String getStatusCategory() {
		return statusCategory;
	}

	public void setStatusCategory(String statusCategory) {
		this.statusCategory = statusCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setCategoryId(Integer categoryId) {
		this.category.setCategoryId(categoryId);
	}

	@JsonProperty("events")
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}



}
		

