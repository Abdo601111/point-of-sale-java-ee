package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TICKETLINES database table.
 * 
 */
@Entity
@Table(name="TICKETLINES")
@NamedQuery(name="Ticketline.findAll", query="SELECT t FROM Ticketline t")
public class Ticketline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Lob
	private byte[] attributes;

	@Column(name="ATTRIBUTESETINSTANCE_ID")
	private String attributesetinstanceId;

	private Integer line;

	private double price;

	private String taxid;

	@Column(name="UNIT_COST")
	private double unitCost;

	private double units;

	@Column(name="UNITS_REFUND")
	private double unitsRefund;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT")
	private Product productBean;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	@JoinColumn(name="TICKET")
	private Ticket ticketBean;

	public Ticketline() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getAttributes() {
		return this.attributes;
	}

	public void setAttributes(byte[] attributes) {
		this.attributes = attributes;
	}

	public String getAttributesetinstanceId() {
		return this.attributesetinstanceId;
	}

	public void setAttributesetinstanceId(String attributesetinstanceId) {
		this.attributesetinstanceId = attributesetinstanceId;
	}

	public Integer getLine() {
		return this.line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTaxid() {
		return this.taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}

	public double getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public double getUnits() {
		return this.units;
	}

	public void setUnits(double units) {
		this.units = units;
	}

	public double getUnitsRefund() {
		return this.unitsRefund;
	}

	public void setUnitsRefund(double unitsRefund) {
		this.unitsRefund = unitsRefund;
	}

	public Product getProductBean() {
		return this.productBean;
	}

	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}

	public Ticket getTicketBean() {
		return this.ticketBean;
	}

	public void setTicketBean(Ticket ticketBean) {
		this.ticketBean = ticketBean;
	}

}