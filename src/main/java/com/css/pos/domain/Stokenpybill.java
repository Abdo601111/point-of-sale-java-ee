package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the STOKENPYBILL database table.
 * 
 */
@Entity
@NamedQuery(name="Stokenpybill.findAll", query="SELECT s FROM Stokenpybill s")
public class Stokenpybill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Integer billid;

	private double quantity;

	private String stickenname;

	public Stokenpybill() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBillid() {
		return this.billid;
	}

	public void setBillid(Integer billid) {
		this.billid = billid;
	}

	public double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getStickenname() {
		return this.stickenname;
	}

	public void setStickenname(String stickenname) {
		this.stickenname = stickenname;
	}

}