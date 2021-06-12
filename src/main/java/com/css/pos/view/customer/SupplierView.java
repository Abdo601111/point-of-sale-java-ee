package com.css.pos.view.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dto.company.BusinessLineDto;
import com.css.pos.dto.company.CompanyDto;
import com.css.pos.dto.customer.CustomerDto;
import com.css.pos.dto.customer.SupplierDto;
import com.css.pos.service.common.GeneralPurposeService;
import com.css.pos.service.common.LocationService;
import com.css.pos.service.company.BusinessLineService;
import com.css.pos.service.company.CompanyService;
import com.css.pos.service.customer.SupplierService;
import com.css.pos.view.common.BaseBean;
@Component
@ManagedBean
@ViewScoped
public class SupplierView extends BaseBean{
	@Autowired
	private SupplierService supplierBusiness;
	@Autowired
	private LocationService locationService;
	@Autowired
	private GeneralPurposeService genericBusiness;
	
	private boolean updateMode;
	private List<SupplierDto> allSuppliers;
	private SupplierDto supplier;
	
	public SupplierDto getSupplier() {
		return supplier;
	}
	public void setSupplier(SupplierDto supplier) {
		this.supplier = supplier;
	}
	public List<SupplierDto> getAllSuppliers() {
		return allSuppliers;
	}
	public void setAllSuppliers(List<SupplierDto> allSuppliers) {
		this.allSuppliers = allSuppliers;
	}

	private Map<String, Integer> countries, cities, states;
	public String listAllSuppliers() {
		try {
			allSuppliers = supplierBusiness.list(getSelectedCompany());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void loadCountryStates() {
		try {
			states = locationService.listAllStates(supplier.getCountry());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void loadStateCities() {
		try {
			cities = locationService.listAllCities(supplier.getState());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void checkMailAvailability() {
		try {
			String dbEmailId = " ";
			if(updateMode) {
				for(SupplierDto c:allSuppliers) {
					if(c.getId().equals(supplier.getId())){
						dbEmailId = c.getEmail();
						break;
					}
				}
			}
			if(   supplier.getEmail() != null
			   && !supplier.getEmail().equals("") 
			   && genericBusiness.isEmailValid(supplier.getEmail())) {
				if(supplierBusiness.isCustomerMailExists(supplier.getEmail(), dbEmailId)) {
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mail already Exists", "Mail already Exists"));
					if(updateMode)
						supplier.setEmail(dbEmailId);
					else
						supplier.setEmail("");
				}
			}else {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Email format", "Invalid Email format"));
				supplier.setEmail("");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public String delete() {
		try {
			if(supplierBusiness.delete(supplier.getId().toString())>0){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Supplier removed successfuly", "Supplier removed successfuly"));
				allSuppliers.remove(supplier);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Email format", "Invalid Email format"));
		}
		return null;
	}
	public String prepare4Add() {
		supplier = new SupplierDto();
		RequestContext ctxt = RequestContext.getCurrentInstance();
		ctxt.execute("PF('supplier_dlg').show();");
		return null;
	}
	public String prepare4Edit() {
		try {
			if(supplier.getCountry() != null) {
				states = locationService.listAllStates(supplier.getCountry());
				if(supplier.getState() != null) {
					cities = locationService.listAllCities(supplier.getState());
				}
			}
			
			supplier = supplier.copy();

			updateMode = true;
			RequestContext ctxt = RequestContext.getCurrentInstance();
			ctxt.execute("PF('supplier_dlg').show();");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String save() {
		try {
			supplier.setCompanyId(getSelectedCompany());
			int result  = supplierBusiness.save(supplier);
			if(result > 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Supplier Saved Successfuly :)"));
				if(allSuppliers == null) allSuppliers = new ArrayList<>();
				if(!updateMode) {
					supplier.setId(result);
					if(allSuppliers==null) allSuppliers = new ArrayList<>();
						allSuppliers.add(supplier);
				}else {
					for(int i = 0 ; i<allSuppliers.size() ; i++) {
						if(allSuppliers.get(i).getId() == supplier.getId()) {
							allSuppliers.set(i, supplier);
							break;
						}
					}
				}
				supplier = new SupplierDto();
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
	public LocationService getLocationService() {
		return locationService;
	}
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
	public GeneralPurposeService getGenericBusiness() {
		return genericBusiness;
	}
	public void setGenericBusiness(GeneralPurposeService genericBusiness) {
		this.genericBusiness = genericBusiness;
	}
	
	public boolean isUpdateMode() {
		return updateMode;
	}
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
	public Map<String, Integer> getCountries() {
		return countries;
	}
	public void setCountries(Map<String, Integer> countries) {
		this.countries = countries;
	}
	public Map<String, Integer> getCities() {
		return cities;
	}
	public void setCities(Map<String, Integer> cities) {
		this.cities = cities;
	}
	public Map<String, Integer> getStates() {
		return states;
	}
	public void setStates(Map<String, Integer> states) {
		this.states = states;
	}
	
	@PostConstruct
	public void inti() {
		supplier = new SupplierDto();
		fillInBusinessLinesList();
		countries = locationService.listAllCountries();
	}
	public SupplierService getSupplierBusiness() {
		return supplierBusiness;
	}

	public void setSupplierBusiness(SupplierService supplierBusiness) {
		this.supplierBusiness = supplierBusiness;
	}
}
