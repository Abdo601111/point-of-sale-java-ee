package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LOOKUP_DATA database table.
 * 
 */
@Entity
@Table(name="LOOKUP_DATA")
@NamedQuery(name="LookupData.findAll", query="SELECT l FROM LookupData l")
public class LookupData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="\"TYPE\"")
	private Integer type;

	@Column(name="\"VALUE\"")
	private String value;

	//bi-directional many-to-one association to Attribute
	@OneToMany(mappedBy="lookupData")
	private List<ProductAttribute> attributes;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY")
	private Company companyBean;

	public LookupData() {
	}
	public LookupData(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<ProductAttribute> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(List<ProductAttribute> attributes) {
		this.attributes = attributes;
	}

	public ProductAttribute addAttribute(ProductAttribute attribute) {
		getAttributes().add(attribute);
		attribute.setLookupData(this);

		return attribute;
	}

	public ProductAttribute removeAttribute(ProductAttribute attribute) {
		getAttributes().remove(attribute);
		attribute.setLookupData(null);

		return attribute;
	}

	public Company getCompanyBean() {
		return this.companyBean;
	}

	public void setCompanyBean(Company companyBean) {
		this.companyBean = companyBean;
	}

}