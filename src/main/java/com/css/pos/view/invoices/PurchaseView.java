package com.css.pos.view.invoices;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.css.pos.common.util.POSConstants;
import com.css.pos.dto.category.CategoryDto;
import com.css.pos.dto.category.ProductDto;
import com.css.pos.dto.customer.CustomerDto;
import com.css.pos.dto.customer.SupplierDto;
import com.css.pos.dto.invoices.InvoiceDto;
import com.css.pos.dto.invoices.InvoiceItemDto;

import static com.css.pos.common.util.POSConstants.NUM_OF_COLUMNS_IN_INVOICE;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.css.pos.dto.security.UserDetailsDto;
import com.css.pos.service.category.CategoryService;
import com.css.pos.service.category.ProductService;
import com.css.pos.service.customer.SupplierService;
import com.css.pos.service.invoices.InvoicesService;
import com.css.pos.view.common.BaseBean;

@ManagedBean
@RequestScoped
@Component
@Lazy
public class PurchaseView extends BaseBean{
	@Autowired
	private InvoicesService invoicesService;
	public InvoicesService getInvoicesService() {
		return invoicesService;
	}
	public void setInvoicesService(InvoicesService invoicesService) {
		this.invoicesService = invoicesService;
	}
	public String resetInvoice(){
		invoice = new InvoiceDto();
		return null;
	}
	private List<CustomerDto> customers;
	public List<CustomerDto> getCustomers() {
		return customers;
	}
	public void setCustomers(List<CustomerDto> customers) {
		this.customers = customers;
	}
	public String saveSalesInvoice(){return null;}
	public String deletInvoice(){
		try {
			if(invoicesService.delete(branchId, invoice.getSupplier(),invoice.getType(),invoice.getSuppInvoiceNumber())) {
				addMsgToFC("Invoice Deleted successfully", FacesMessage.SEVERITY_INFO);
				invoice = new InvoiceDto();
			}else {
				addMsgToFC("Error Occured try again later", FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addMsgToFC("Error Occured:"+e.getMessage(), FacesMessage.SEVERITY_ERROR);
			invoice = new InvoiceDto();
		}
		
		return null;
	}
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productsService;
	public ProductService getProductsService() {
		return productsService;
	}
	private CustomerDto selectedCustomer;
	public CustomerDto getSelectedCustomer() {
		return selectedCustomer;
	}
	public void setSelectedCustomer(CustomerDto selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
	private Map<String,Integer> movmentTypes;
	private ProductDto selectedProduct;
	public ProductDto getSelectedProduct() {
		return selectedProduct;
	}
	public void setSelectedProduct(ProductDto selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	public String loadFirstInvoice() {
		try {
			invoice = invoicesService.loadFirstInvoice(branchId, invoice.getSupplier(),invoice.getType());
			if(invoice.getId() == null)
				addMsgToFC("No such invoice", FacesMessage.SEVERITY_WARN);
			else
				RequestContext.getCurrentInstance().execute("updateAfterRemoval()");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addMsgToFC("Error Occured:"+e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
		return null;
	}
	public String loadLastInvoice() {
		try {
			invoice = invoicesService.loadLastInvoice(branchId, invoice.getSupplier(),invoice.getType());
			if(invoice.getId() == null)
				addMsgToFC("No such invoice", FacesMessage.SEVERITY_WARN);
			else
				RequestContext.getCurrentInstance().execute("updateAfterRemoval()");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addMsgToFC("Error Occured:"+e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
		return null;
	}
	public String loadPrevInvoice(){
		try {
			if(invoice.getSuppInvoiceNumber() == null || invoice.getSuppInvoiceNumber() == -1) {
				addMsgToFC("Enter invoice number!!!", FacesMessage.SEVERITY_ERROR);
			}else {
				invoice = invoicesService.loadPrevInvoice(invoice.getSuppInvoiceNumber(), branchId, invoice.getSupplier(),invoice.getType());
				if(invoice.getId() == null)
					addMsgToFC("No such invoice", FacesMessage.SEVERITY_WARN);
				else
					RequestContext.getCurrentInstance().execute("updateAfterRemoval()");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addMsgToFC("Error Occured:"+e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
		return null;
	}
	public String loadNextInvoice(){
		try {
			if(invoice.getSuppInvoiceNumber() == null || invoice.getSuppInvoiceNumber() == -1) {
				addMsgToFC("Enter invoice number!!!", FacesMessage.SEVERITY_ERROR);
			}else {
				invoice = invoicesService.loadNextToInvoice(invoice.getSuppInvoiceNumber(), branchId, invoice.getSupplier(),invoice.getType());
				if(invoice.getId() == null)
					addMsgToFC("No such invoice", FacesMessage.SEVERITY_WARN);
				else
					RequestContext.getCurrentInstance().execute("updateAfterRemoval()");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addMsgToFC("Error Occured:"+e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
		return null;
	}
	/**
	 * Session 31
	 */
	
	private Integer  searchInvNumber,searchKey;
	public Integer getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(Integer searchKey) {
		this.searchKey = searchKey;
	}
	public String search4Invocie() {
		try {
			invoice = invoicesService.search4Invoice(getSearchInvNumber(), branchId, invoice.getSupplier(),invoice.getType());
			if(invoice.getId() == null)
				addMsgToFC("No such invoice", FacesMessage.SEVERITY_WARN);
			else
				RequestContext.getCurrentInstance().execute("PF('searchDlg').hide();updateAfterRemoval()");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			addMsgToFC("Error Occured:"+e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
		return null;
	}
	
	private String searchName;
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public Integer getSearchInvNumber() {
		return searchInvNumber;
	}
	public void setSearchInvNumber(Integer searchInvNumber) {
		this.searchInvNumber = searchInvNumber;
	}
	public Integer operationType = POSConstants.IN_PURCHASE;
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public String proceed4Payment() {
		System.out.println("payment from here....");
		/**
		 * Do calculations
		 */
		boolean zeroTotal = false;
		invoice.setSubTotal(0f);
		for(int i = 0; i<invoice.getItems().size();i++) {
			invoice.setSubTotal(invoice.getSubTotal() +
					invoice.getItems().get(i).getPrice() * invoice.getItems().get(i).getUnits());
		}
		if(invoice.getSubTotal() != 0) {
			invoice.setTotal(invoice.getSubTotal() * (1-invoice.getDiscount()/100));
			zeroTotal = false;
		}else {
			zeroTotal = true;
		}
		
		/**
		 * IF and ONLY if We're purchasing we'll Open the Payment Dialogue
		 */
		if(zeroTotal) 
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Invoice Zero total encountered !!!"
							, "Invalid Invoice Zero total encountered !!!"));
		
		else if(operationType.equals(POSConstants.IN_PURCHASE)) {
			invoice.setCashPayment(invoice.getTotal());
			RequestContext.getCurrentInstance().execute("PF('paymentDlg').show();");
		}else {
			return saveInvoice();
		}
		return null;
	}
	/**
	 * Sesison 31
	 */
	private String branchId;
	public String saveInvoice() {
		try {
			 
			invoice.setBranch(branchId);
			/**
			 * check payment status 
			 */
			if(operationType.equals(POSConstants.IN_PURCHASE)) {
				
				/**
				 * To be removed
				 */
				invoice.setType(POSConstants.IN_PURCHASE);
				/**
				 * To be removed
				 */
			}
			
			
			int invocieNumber = invoicesService.save(invoice);
			if(invocieNumber > 0) {
				FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Invoice saved with ref number <"+invoice.getSuppInvoiceNumber()
						+">", "New Invoice saved with ref number <"+invoice.getSuppInvoiceNumber()
						+">");
				invoice = new InvoiceDto();
				
				
			}else {
				FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occured please try agian later :(", "Error occured please try agian later :(");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public void addToCart(ActionEvent event) {
		try {
			if(selectedProduct != null) {
				InvoiceItemDto item =  new InvoiceItemDto();
				if(invoice.getItems() == null) {
					invoice.setItems(new ArrayList<InvoiceItemDto>());
				}
				item.setOrder(invoice.getItems().size()+1);
				item.setId(UUID.randomUUID().toString());
				item.setProducId(selectedProduct.getId());
				item.setProductName(selectedProduct.getName());
				if(!alreadyAddedTOCart(item)) {
					invoice.getItems().add(item);
				}else {
					FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item Already added :(", "Item Already added :(");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	private boolean alreadyAddedTOCart(InvoiceItemDto item) {
		try {
			if(invoice.getItems() != null && !invoice.getItems().isEmpty()) {
				for(InvoiceItemDto exIt : invoice.getItems()) {
					if(exIt.getProducId().equals(item.getProducId()))
							return true;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public void setProductsService(ProductService productsService) {
		this.productsService = productsService;
	}

	@Autowired
	private SupplierService supplierService;
	public SupplierService getSupplierService() {
		return supplierService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	private InvoiceDto invoice;
	
	private UserDetailsDto casher;
	public UserDetailsDto getCasher() {
		return casher;
	}
	public void setCasher(UserDetailsDto casher) {
		this.casher = casher;
	}
	public InvoiceDto getInvoice() {
		return invoice;
	}
	public void setInvoice(InvoiceDto invoice) {
		this.invoice = invoice;
	}
	/**
	 * Considered as a store for company categories 
	 */
	private List<CategoryDto> allCategories; 
	
	private List<CategoryDto> viewCategories;
	private List<ProductDto> products;
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	public List<CategoryDto> getViewCategories() {
		return viewCategories;
	}
	public void setViewCategories(List<CategoryDto> viewCategories) {
		this.viewCategories = viewCategories;
	}
	private CategoryDto selectedCategory;
	private InvoiceItemDto selectedItem;
	public String removeFromCart() {
		try {
			invoice.getItems().remove(selectedItem);
			/*
			if(!invoice.getItems().isEmpty()) {
				for(int i = 0;i<invoice.getItems().size();i++) {
					invoice.getItems().get(i).setCost(invoice.getItems().get(i).getUnits() * invoice.getItems().get(i).getPrice());
				}
			}
			*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	} 
	public InvoiceItemDto getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(InvoiceItemDto selectedItem) {
		this.selectedItem = selectedItem;
	}
	public CategoryDto getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(CategoryDto selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	private Integer numOfColumns = 1;
	public Integer getNumOfColumns() {
		return numOfColumns;
	}
	public void setNumOfColumns(Integer numOfColumns) {
		this.numOfColumns = numOfColumns;
	}
	public String viewCategoryDetails() {
		try {
			if(selectedCategory != null) {
				products = new ArrayList<>();
				if(selectedCategory.isHasProducts()) {
					products = productsService.list(selectedCategory.getId());
				}
				viewCategories = new ArrayList<>();
				for(CategoryDto c:allCategories) {
					
					if(c.getParentId()!=null && c.getParentId().equals(selectedCategory.getId())){
						viewCategories.add(c);
					}
				}
				if(viewCategories.isEmpty()) {
					viewCategories.add(selectedCategory);
					numOfColumns = 1;
				}else{
					numOfColumns = (viewCategories.size() >= NUM_OF_COLUMNS_IN_INVOICE) ? NUM_OF_COLUMNS_IN_INVOICE:viewCategories.size(); 
					System.out.println(">>>>>>>>>>>>>>>>.numOfColumns = "+numOfColumns);
				}
				
				
			}else {
				System.out.println("NULL CATEGORY SELECTION");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	private List<SupplierDto> suppliers;
	public List<SupplierDto> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(List<SupplierDto> suppliers) {
		this.suppliers = suppliers;
	}
	private String companyId;
	public String gotoHomeCategories() {
		try {
			
		resetRootCats();
			
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		return null;
	}
	private void resetRootCats() {
		try {
			viewCategories = new ArrayList<>();
			/**
			 * Bad perf. if cats are rarley changed
			 */
			allCategories = categoryService.list(companyId);
			/**
			 * 
			 */
			if(allCategories != null && !allCategories.isEmpty())
			for(CategoryDto cat : allCategories) {
				if(cat.getParentId() == null) {
					if(viewCategories == null) viewCategories = new ArrayList<>();
					viewCategories.add(cat);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@PostConstruct
	public void init() {
		/**
		 * 1- Get logged in  user from the session and set it in to the invoice.
		 * 2- Get selected branch  from the session and set it in to the invoice.
		 * 3- Get list of root categories 
		 * 4- Get list of company Suppliers
		 */
		try {
			invoice = new InvoiceDto();
			products = new ArrayList<>();
			companyId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("current_company")
					.toString();
			casher = (UserDetailsDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("current_user");
			invoice.setCasher(casher.getId());
			invoice.setBranch(FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("current_branch").toString());
			allCategories = categoryService.list(companyId);
			resetRootCats();
			suppliers = supplierService.list(companyId);
			movmentTypes = invoicesService.getMovmentTypesMap();
			branchId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("current_branch").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Map<String, Integer> getMovmentTypes() {
		return movmentTypes;
	}
	public void setMovmentTypes(Map<String, Integer> movmentTypes) {
		this.movmentTypes = movmentTypes;
	}
}
