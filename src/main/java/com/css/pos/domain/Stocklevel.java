package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the STOCKLEVEL database table.
 * 
 */
@Entity
@NamedQuery(name="Stocklevel.findAll", query="SELECT s FROM Stocklevel s")
public class Stocklevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String location;

	private double stockmaximum;

	private double stocksecurity;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT")
	private Product productBean;

	public Stocklevel() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getStockmaximum() {
		return this.stockmaximum;
	}

	public void setStockmaximum(double stockmaximum) {
		this.stockmaximum = stockmaximum;
	}

	public double getStocksecurity() {
		return this.stocksecurity;
	}

	public void setStocksecurity(double stocksecurity) {
		this.stocksecurity = stocksecurity;
	}

	public Product getProductBean() {
		return this.productBean;
	}

	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}

}