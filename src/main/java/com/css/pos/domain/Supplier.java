package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GeneratorType;

import java.util.List;


/**
 * The persistent class for the SUPPLIERS database table.
 * 
 */
@Entity
@Table(name="SUPPLIERS")
@NamedQuery(name="Supplier.findAll", query="SELECT s FROM Supplier s")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	

	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="id_gen" )
	@SequenceGenerator(initialValue=1, allocationSize=50, name="id_gen", sequenceName="SUPPLIERS_SEQ1" )
	private Integer id;

	private String address;

	private String code;

	private String email;

	private String mobile;

	private String name;

	private String phone;
	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="CITY")
	private City cityBean;
	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="COUNTRY")
	private Country countryBean;

	public City getCityBean() {
		return cityBean;
	}

	public void setCityBean(City cityBean) {
		this.cityBean = cityBean;
	}

	public Country getCountryBean() {
		return countryBean;
	}

	public void setCountryBean(Country countryBean) {
		this.countryBean = countryBean;
	}

	public State getStateBean() {
		return stateBean;
	}

	public void setStateBean(State stateBean) {
		this.stateBean = stateBean;
	}

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="\"STATE\"")
	private State stateBean;
	//bi-directional many-to-one association to Stockin
//	@OneToMany(mappedBy="supplierBean")
//	private List<Stockin> stockins;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY")
	private Company companyBean;

	//bi-directional many-to-one association to SuppliersPayment
//	@OneToMany(mappedBy="supplier")
//	private List<SuppliersPayment> suppliersPayments;

	public Supplier() {
		
	}
	public Supplier(Integer id) {
		this.id =id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

//	public List<Stockin> getStockins() {
//		return this.stockins;
//	}
//
//	public void setStockins(List<Stockin> stockins) {
//		this.stockins = stockins;
//	}
//
//	public Stockin addStockin(Stockin stockin) {
//		getStockins().add(stockin);
//		stockin.setSupplierBean(this);
//
//		return stockin;
//	}
//
//	public Stockin removeStockin(Stockin stockin) {
//		getStockins().remove(stockin);
//		stockin.setSupplierBean(null);
//
//		return stockin;
//	}

	public Company getCompanyBean() {
		return this.companyBean;
	}

	public void setCompanyBean(Company companyBean) {
		this.companyBean = companyBean;
	}

//	public List<SuppliersPayment> getSuppliersPayments() {
//		return this.suppliersPayments;
//	}
//
//	public void setSuppliersPayments(List<SuppliersPayment> suppliersPayments) {
//		this.suppliersPayments = suppliersPayments;
//	}
//
//	public SuppliersPayment addSuppliersPayment(SuppliersPayment suppliersPayment) {
//		getSuppliersPayments().add(suppliersPayment);
//		suppliersPayment.setSupplier(this);
//
//		return suppliersPayment;
//	}
//
//	public SuppliersPayment removeSuppliersPayment(SuppliersPayment suppliersPayment) {
//		getSuppliersPayments().remove(suppliersPayment);
//		suppliersPayment.setSupplier(null);
//
//		return suppliersPayment;
//	}

}