package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ATTRIBUTE database table.
 * 
 */
@Entity
@Table(name="PRODUCT_ATTRIBUTE")
public class ProductAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private Integer line;

	@Column(name="\"VALUE\"")
	private String value;

	//bi-directional many-to-one association to LookupData
	@ManyToOne
	@JoinColumn(name="ATTRIBUTE")
	private LookupData lookupData;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT")
	private Product productBean;

	public ProductAttribute() {
	}
	
	public ProductAttribute(String id, Integer line, String value, LookupData lookupData, Product productBean) {
		super();
		this.id = id;
		this.line = line;
		this.value = value;
		this.lookupData = lookupData;
		this.productBean = productBean;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getLine() {
		return this.line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LookupData getLookupData() {
		return this.lookupData;
	}

	public void setLookupData(LookupData lookupData) {
		this.lookupData = lookupData;
	}

	public Product getProductBean() {
		return this.productBean;
	}

	public void setProductBean(Product productBean) {
		this.productBean = productBean;
	}

}