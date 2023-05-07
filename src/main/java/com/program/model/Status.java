package com.program.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public Status(Integer statusId, String statusName, String statusCategory, List<Event> events, Category category) {
		this.statusId = statusId;
		this.statusName = statusName;
		this.statusCategory = statusCategory;
		this.events = events;
		this.category = category;
	}

	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

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

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "Status{" +
				"statusId=" + statusId +
				", statusName='" + statusName + '\'' +
				", statusCategory='" + statusCategory + '\'' +
				", events=" + events +
				", category=" + category +
				'}';
	}
}
		

