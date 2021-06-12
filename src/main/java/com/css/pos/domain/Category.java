package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the CATEGORIES database table.
 * 
 */
@Entity
@Table(name="CATEGORIES")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="HAS_PRODUCTS")
	private Integer hasProducts;

	private String image;

	private String name;

	private String parentid;

	private String printer;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY")
	private Company company;

	//bi-directional many-to-one association to Product
//	@OneToMany(mappedBy="categoryBean")
//	private List<Product> products;

	public Category() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getHasProducts() {
		return this.hasProducts;
	}

	public void setHasProducts(Integer hasProducts) {
		this.hasProducts = hasProducts;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getPrinter() {
		return this.printer;
	}

	public void setPrinter(String printer) {
		this.printer = printer;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

//	public List<Product> getProducts() {
//		return this.products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//
//	public Product addProduct(Product product) {
//		getProducts().add(product);
//		product.setCategoryBean(this);
//
//		return product;
//	}
//
//	public Product removeProduct(Product product) {
//		getProducts().remove(product);
//		product.setCategoryBean(null);
//
//		return product;
//	}

}