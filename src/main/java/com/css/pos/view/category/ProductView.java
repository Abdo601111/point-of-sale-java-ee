package com.css.pos.view.category;

import java.io.ObjectStreamConstants;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.common.util.POSConstants;
import com.css.pos.dto.category.CategoryDto;
import com.css.pos.dto.category.ProdAttrDto;
import com.css.pos.dto.category.ProductDto;
import com.css.pos.dto.common.LookupDto;
import com.css.pos.dto.company.BusinessLineDto;
import com.css.pos.dto.company.CompanyDto;
import com.css.pos.service.category.CategoryService;
import com.css.pos.service.category.ProductService;
import com.css.pos.service.common.LookupDataService;
import com.css.pos.service.company.BusinessLineService;
import com.css.pos.service.company.CompanyService;
import com.css.pos.view.common.BaseBean;

@ManagedBean
@ViewScoped
@Component
public class ProductView extends BaseBean{
	
	private List<CategoryDto> allCats;
	private List<LookupDto> lookupData;
	public String listAllProducts() {
		try {
			allProducts = productService.list(selectedCategory);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public List<LookupDto> getLookupData() {
		return lookupData;
	}
	public void setLookupData(List<LookupDto> lookupData) {
		this.lookupData = lookupData;
	}
	private String selectedCategory;
	public String getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	private List<ProductDto> allProducts; 
	public List<ProductDto> getAllProducts() {
		return allProducts;
	}
	public void setAllProducts(List<ProductDto> allProducts) {
		this.allProducts = allProducts;
	}
	private String selectedBLine;
	private ProductDto product = new ProductDto();
	private boolean updateMode;
	@Autowired
	private ProductService productService;
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private LookupDataService lookupdataService;
	public LookupDataService getLookupdataService() {
		return lookupdataService;
	}
	public void setLookupdataService(LookupDataService lookupdataService) {
		this.lookupdataService = lookupdataService;
	}
	@PostConstruct
	public void inti() {
		product = new ProductDto();
		allCats = new ArrayList<>();
		fillInBusinessLinesList();
	}
	public void handleLogoUpload(FileUploadEvent event) {
		// TODO Auto-generated method stub
		try{
			String dbLogo = super.handleLogoUpload(event, "product.logo.upload_folder");
			product.setLogo(dbLogo);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String addProductAttribute() {
		try {
			System.out.println("adding product attribute...");
			if(product.getAttributes() == null) 
				product.setAttributes(new ArrayList<>());
			product.getAttributes().add(new ProdAttrDto());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String prepareEdit() {
		try {
			System.out.println("opeining edit dlg");
			updateMode = true;
			RequestContext ctxt = RequestContext.getCurrentInstance();
			ctxt.execute("PF('createProduct_dlg').show();");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String delete() {
		try {
			if (productService.delete(product.getId()) > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Product removed Successfuly :)"));
				allProducts.remove(product);
				product = new ProductDto();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
						"Error", "Something went wrong please try again"));
			}
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ex.getMessage()));
		}
		return null;
	}
	public String loadCategories() {
		return null;
	}
	private UploadedFile file;
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	public List<CategoryDto> getAllCats() {
		return allCats;
	}
	public void setAllCats(List<CategoryDto> allCats) {
		this.allCats = allCats;
	}
	public String getSelectedBLine() {
		return selectedBLine;
	}
	public void setSelectedBLine(String selectedBLine) {
		this.selectedBLine = selectedBLine;
	}
	
	public boolean isUpdateMode() {
		return updateMode;
	}
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
	public CategoryService getCatService() {
		return catService;
	}
	public void setCatService(CategoryService catService) {
		this.catService = catService;
	}
	public String saveProduct() {
		try {
			product.setCategoryId(selectedCategory);
			int result  = productService.save(product);
			if(result > 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Category Saved Successfuly :)"));
				if(getCompanies() == null) setCompanies( new ArrayList<>());
				if(!updateMode) {
					if(allProducts==null) allProducts = new ArrayList<>();
					allProducts.add(product);
				}
				product = new ProductDto();
				updateMode = false;
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error","Something went wrong please try again"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
			e.printStackTrace();
		}
		
		return null;
	}
	public void loadBusinessLineCompanies() {
		setCompanies(getCompanyServices().list(selectedBLine));
	}
	public void loadCompanyStaff() {
		try {
			allCats = catService.list(getSelectedCompany());
			lookupData = lookupdataService.listAllLookupElements(POSConstants.PRODUCT_TYPE_LOOKUP_LIST_CODE, getSelectedCompany()); 
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}			
	}
	private ProdAttrDto productAttr;
	public ProdAttrDto getProductAttr() {
		return productAttr;
	}
	public void setProductAttr(ProdAttrDto productAttr) {
		this.productAttr = productAttr;
	}
	public void deleteProductAttribute() {
		try {
			product.getAttributes().remove(productAttr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
