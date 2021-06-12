package com.css.pos.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PRODUCTS database table.
 * 
 */
@Entity
@Table(name="PRODUCTS")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="ATTRIBUTES_STRING")
	private String attributesString;

	private String code;

	@Lob
	private byte[] codeimage;

	private String codetype;

	private String image;

	private Integer iscom;

	private Integer isscale;

	private String name;

	private float pricebuy;

	private float pricesell;

	private String reference;

	private float stockcost;

	private float stockvolume;

	private String taxcat;

	//bi-directional many-to-one association to Attribute
	@OneToMany(mappedBy="productBean")
	private List<ProductAttribute> attributes;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CATEGORY")
	private Category categoryBean;

//	//bi-directional one-to-one association to ProductsCat
//	@OneToOne(mappedBy="productBean")
//	private ProductsCat productsCat;
//
//	//bi-directional many-to-one association to ProductsCom
//	@OneToMany(mappedBy="product1")
//	private List<ProductsCom> productsComs1;
//
//	//bi-directional many-to-one association to ProductsCom
//	@OneToMany(mappedBy="product2Bean")
//	private List<ProductsCom> productsComs2;
//
//	//bi-directional many-to-one association to Stockcurrent
//	@OneToMany(mappedBy="productBean")
//	private List<Stockcurrent> stockcurrents;
//
//	//bi-directional many-to-one association to Stockdiary
//	@OneToMany(mappedBy="productBean")
//	private List<Stockdiary> stockdiaries;
//
//	//bi-directional many-to-one association to Stocklevel
//	@OneToMany(mappedBy="productBean")
//	private List<Stocklevel> stocklevels;
//
//	//bi-directional many-to-one association to Ticketline
//	@OneToMany(mappedBy="productBean")
//	private List<Ticketline> ticketlines;

	public Product() {
	}
	public Product(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttributesString() {
		return this.attributesString;
	}

	public void setAttributesString(String attributesString) {
		this.attributesString = attributesString;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte[] getCodeimage() {
		return this.codeimage;
	}

	public void setCodeimage(byte[] codeimage) {
		this.codeimage = codeimage;
	}

	public String getCodetype() {
		return this.codetype;
	}

	public void setCodetype(String codetype) {
		this.codetype = codetype;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIscom() {
		return this.iscom;
	}

	public void setIscom(Integer iscom) {
		this.iscom = iscom;
	}

	public Integer getIsscale() {
		return this.isscale;
	}

	public void setIsscale(Integer isscale) {
		this.isscale = isscale;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPricebuy() {
		return this.pricebuy;
	}

	public void setPricebuy(float pricebuy) {
		this.pricebuy = pricebuy;
	}

	public float getPricesell() {
		return this.pricesell;
	}

	public void setPricesell(float pricesell) {
		this.pricesell = pricesell;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public float getStockcost() {
		return this.stockcost;
	}

	public void setStockcost(float stockcost) {
		this.stockcost = stockcost;
	}

	public float getStockvolume() {
		return this.stockvolume;
	}

	public void setStockvolume(float stockvolume) {
		this.stockvolume = stockvolume;
	}

	public String getTaxcat() {
		return this.taxcat;
	}

	public void setTaxcat(String taxcat) {
		this.taxcat = taxcat;
	}

	public List<ProductAttribute> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(List<ProductAttribute> attributes) {
		this.attributes = attributes;
	}

	public ProductAttribute addAttribute(ProductAttribute attribute) {
		getAttributes().add(attribute);
		attribute.setProductBean(this);

		return attribute;
	}

	public ProductAttribute removeAttribute(ProductAttribute attribute) {
		getAttributes().remove(attribute);
		attribute.setProductBean(null);

		return attribute;
	}

	public Category getCategoryBean() {
		return this.categoryBean;
	}

	public void setCategoryBean(Category categoryBean) {
		this.categoryBean = categoryBean;
	}

//	public ProductsCat getProductsCat() {
//		return this.productsCat;
//	}
//
//	public void setProductsCat(ProductsCat productsCat) {
//		this.productsCat = productsCat;
//	}
//
//	public List<ProductsCom> getProductsComs1() {
//		return this.productsComs1;
//	}
//
//	public void setProductsComs1(List<ProductsCom> productsComs1) {
//		this.productsComs1 = productsComs1;
//	}
//
//	public ProductsCom addProductsComs1(ProductsCom productsComs1) {
//		getProductsComs1().add(productsComs1);
//		productsComs1.setProduct1(this);
//
//		return productsComs1;
//	}
//
//	public ProductsCom removeProductsComs1(ProductsCom productsComs1) {
//		getProductsComs1().remove(productsComs1);
//		productsComs1.setProduct1(null);
//
//		return productsComs1;
//	}
//
//	public List<ProductsCom> getProductsComs2() {
//		return this.productsComs2;
//	}
//
//	public void setProductsComs2(List<ProductsCom> productsComs2) {
//		this.productsComs2 = productsComs2;
//	}
//
//	public ProductsCom addProductsComs2(ProductsCom productsComs2) {
//		getProductsComs2().add(productsComs2);
//		productsComs2.setProduct2Bean(this);
//
//		return productsComs2;
//	}
//
//	public ProductsCom removeProductsComs2(ProductsCom productsComs2) {
//		getProductsComs2().remove(productsComs2);
//		productsComs2.setProduct2Bean(null);
//
//		return productsComs2;
//	}
//
//	public List<Stockcurrent> getStockcurrents() {
//		return this.stockcurrents;
//	}
//
//	public void setStockcurrents(List<Stockcurrent> stockcurrents) {
//		this.stockcurrents = stockcurrents;
//	}
//
//	public Stockcurrent addStockcurrent(Stockcurrent stockcurrent) {
//		getStockcurrents().add(stockcurrent);
//		stockcurrent.setProductBean(this);
//
//		return stockcurrent;
//	}
//
//	public Stockcurrent removeStockcurrent(Stockcurrent stockcurrent) {
//		getStockcurrents().remove(stockcurrent);
//		stockcurrent.setProductBean(null);
//
//		return stockcurrent;
//	}
//
//	public List<Stockdiary> getStockdiaries() {
//		return this.stockdiaries;
//	}
//
//	public void setStockdiaries(List<Stockdiary> stockdiaries) {
//		this.stockdiaries = stockdiaries;
//	}
//
//	public Stockdiary addStockdiary(Stockdiary stockdiary) {
//		getStockdiaries().add(stockdiary);
//		stockdiary.setProductBean(this);
//
//		return stockdiary;
//	}
//
//	public Stockdiary removeStockdiary(Stockdiary stockdiary) {
//		getStockdiaries().remove(stockdiary);
//		stockdiary.setProductBean(null);
//
//		return stockdiary;
//	}
//
//	public List<Stocklevel> getStocklevels() {
//		return this.stocklevels;
//	}
//
//	public void setStocklevels(List<Stocklevel> stocklevels) {
//		this.stocklevels = stocklevels;
//	}
//
//	public Stocklevel addStocklevel(Stocklevel stocklevel) {
//		getStocklevels().add(stocklevel);
//		stocklevel.setProductBean(this);
//
//		return stocklevel;
//	}
//
//	public Stocklevel removeStocklevel(Stocklevel stocklevel) {
//		getStocklevels().remove(stocklevel);
//		stocklevel.setProductBean(null);
//
//		return stocklevel;
//	}
//
//	public List<Ticketline> getTicketlines() {
//		return this.ticketlines;
//	}
//
//	public void setTicketlines(List<Ticketline> ticketlines) {
//		this.ticketlines = ticketlines;
//	}
//
//	public Ticketline addTicketline(Ticketline ticketline) {
//		getTicketlines().add(ticketline);
//		ticketline.setProductBean(this);
//
//		return ticketline;
//	}
//
//	public Ticketline removeTicketline(Ticketline ticketline) {
//		getTicketlines().remove(ticketline);
//		ticketline.setProductBean(null);
//
//		return ticketline;
//	}

}