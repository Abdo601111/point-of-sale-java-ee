package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the BUSINESS_LINES database table.
 * 
 */
@Entity
@Table(name="BUSINESS_LINES")
@NamedQuery(name="BusinessLine.findAll", query="SELECT b FROM BusinessLine b")
public class BusinessLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String description;

	private String name;

	private BigDecimal negativestock;

	private BigDecimal optics;

	//bi-directional many-to-one association to Company
	@OneToMany(mappedBy="businessLine")
	private List<Company> companies;

	public BusinessLine() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getNegativestock() {
		return this.negativestock;
	}

	public void setNegativestock(BigDecimal negativestock) {
		this.negativestock = negativestock;
	}

	public BigDecimal getOptics() {
		return this.optics;
	}

	public void setOptics(BigDecimal optics) {
		this.optics = optics;
	}

	public List<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public Company addCompany(Company company) {
		getCompanies().add(company);
		company.setBusinessLine(this);

		return company;
	}

	public Company removeCompany(Company company) {
		getCompanies().remove(company);
		company.setBusinessLine(null);

		return company;
	}

}