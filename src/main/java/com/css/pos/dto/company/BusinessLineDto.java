package com.css.pos.dto.company;


public class BusinessLineDto {
	@Override
	public String toString() {
		return "BueinessLineDto [id=" + id + ", description=" + description + ", name=" + name + "]";
	}
	public BusinessLineDto() {}
	private String id;
	private String description;
	private String name;
	private Boolean negativestock = false;

	public BusinessLineDto(String id, String description, String name, Boolean negativestock, Boolean optics) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
		this.negativestock = negativestock;
		this.optics = optics;
	}

	private Boolean optics;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Boolean getNegativestock() {
		return negativestock;
	}
	public void setNegativestock(Boolean negativestock) {
		this.negativestock = negativestock;
	}
	public Boolean getOptics() {
		return optics;
	}
	public void setOptics(Boolean optics) {
		this.optics = optics;
	}

	
}
