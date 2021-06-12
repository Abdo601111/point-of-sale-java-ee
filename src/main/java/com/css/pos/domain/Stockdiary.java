package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the STOCKDIARY database table.
 * 
 */
@Entity
@NamedQuery(name="Stockdiary.findAll", query="SELECT s FROM Stockdiary s")
public class Stockdiary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="ATTRIBUTESETINSTANCE_ID")
	private String attributesetinstanceId;
	@Column(name="BILL_NUM")
	private Integer billNumber;
	public Integer getBillNumber() {
		return billNumber;
	}
	@Column(name="SUPPLIER_NUM")
	private Integer supplierNum;
	public Integer getSupplierNum() {
		return supplierNum;
	}

	public void setSupplierNum(Integer supplierNum) {
		this.supplierNum = supplierNum;
	}

	public void setBillNumber(Integer billNumber) {
		this.billNumber = billNumber;
	}

	private Date datenew;

	private String location;

	private float price;

	private Integer reason;

	private float units;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT")
	private Product productBean;

	public Stockdiary() {
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

	public Date getDatenew() {
		return this.datenew;
	}

	public void setDatenew(Date datenew) {
		this.datenew = datenew;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getReason() {
		return this.reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public float getUnits() {
		return this.units;
	}

	public void setUnits(float units) {
		this.units = units;
	}

	public Product getProductBean() {
		return this.productBean;
	}

	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}

}