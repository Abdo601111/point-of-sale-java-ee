package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the STOCKIN database table.
 * 
 */
@Entity
@NamedQuery(name="Stockin.findAll", query="SELECT s FROM Stockin s")
public class Stockin implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date datein;

	private float debt;
	@Id
	private String id;

	private float total;

	//bi-directional many-to-one association to Supplier
	@ManyToOne
	@JoinColumn(name="SUPPLIER")
	private Supplier supplierBean;

	public Stockin() {
	}

	public Date getDatein() {
		return this.datein;
	}

	public void setDatein(Date datein) {
		this.datein = datein;
	}

	public float getDebt() {
		return this.debt;
	}

	public void setDebt(float debt) {
		this.debt = debt;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Supplier getSupplierBean() {
		return this.supplierBean;
	}

	public void setSupplierBean(Supplier supplierBean) {
		this.supplierBean = supplierBean;
	}

}