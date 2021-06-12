package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the STOCKCURRENT database table.
 * 
 */
@Entity
@NamedQuery(name="Stockcurrent.findAll", query="SELECT s FROM Stockcurrent s")
public class Stockcurrent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="ATTRIBUTESETINSTANCE_ID")
	private String attributesetinstanceId;

	private double cost;

	private double total;

	private double units;

	//bi-directional many-to-one association to Branch
	@ManyToOne
	@JoinColumn(name="LOCATION")
	private Branch branch;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT")
	private Product productBean;

	public Stockcurrent() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttributesetinstanceId() {
		return this.attributesetinstanceId;
	}

	public void setAttributesetinstanceId(String attributesetinstanceId) {
		this.attributesetinstanceId = attributesetinstanceId;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getUnits() {
		return this.units;
	}

	public void setUnits(double units) {
		this.units = units;
	}

	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Product getProductBean() {
		return this.productBean;
	}

	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}

}