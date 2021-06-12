package com.css.pos.view.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.css.pos.dto.customer.CustomerDto;
import com.css.pos.service.common.GeneralPurposeService;
import com.css.pos.service.common.LocationService;
import com.css.pos.service.customer.CustomerService;
import com.css.pos.view.common.BaseBean;


@ManagedBean (eager=true)
@RequestScoped
@Component
@Lazy
public class CustomerView extends BaseBean{
	@Autowired
	private CustomerService customerService;// = new CusomterServiceImpl();
	@Autowired
	private LocationService locationService;
	@Autowired
	private GeneralPurposeService genericBusiness;
	public GeneralPurposeService getGenericBusiness() {
		return genericBusiness;
	}
	public void setGenericBusiness(GeneralPurposeService genericBusiness) {
		this.genericBusiness = genericBusiness;
	}
	
	public LocationService getLocationService() {
		return locationService;
	}
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
	private List<CustomerDto> allCustomers;
	private CustomerDto customer, randomPassword;
	public CustomerDto getRandomPassword() {
		return randomPassword;
	}
	public void setRandomPassword(CustomerDto randomPassword) {
		this.randomPassword = randomPassword;
	}
	public boolean isUseDefaultPassword() {
		return useDefaultPassword;
	}
	public void setUseDefaultPassword(boolean useDefaultPassword) {
		this.useDefaultPassword = useDefaultPassword;
	}
	private boolean useDefaultPassword;
	
	
	public List<CustomerDto> getAllCustomers() {
		return allCustomers;
	}
	public void setAllCustomers(List<CustomerDto> allCustomers) {
		this.allCustomers = allCustomers;
	}
	public CustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	private Map<String, Integer> countries, cities, states;
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
	private boolean updateMode;
	
	
	@PostConstruct
	public void inti() {
		System.out.println("from customer view .......................<<<<<<<<<<<<<<<<<");
		customer = new CustomerDto();
		fillInBusinessLinesList();
		countries = locationService.listAllCountries();
	}
	
	public String listAllCustomers() {
		try {
			allCustomers = customerService.list(getSelectedCompany());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void loadCountryStates() {
		try {
			states = locationService.listAllStates(customer.getCountry());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void loadStateCities() {
		try {
			cities = locationService.listAllCities(customer.getState());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void checkMailAvailability() {
		try {
			String dbEmailId = " ";
			if(updateMode) {
				for(CustomerDto c:allCustomers) {
					if(c.getId().equalsIgnoreCase(customer.getId())){
						dbEmailId = c.getEmail();
						break;
					}
				}
			}
			if(   customer.getEmail() != null
			   && !customer.getEmail().equals("") 
			   && genericBusiness.isEmailValid(customer.getEmail())) {
				if(customerService.isCustomerMailExists(customer.getEmail(), dbEmailId)) {
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mail already Exists", "Mail already Exists"));
					if(updateMode)
						customer.setEmail(dbEmailId);
					else
						customer.setEmail("");
				}
			}else {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Email format", "Invalid Email format"));
				customer.setEmail("");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public String prepare4Add() {
		customer = new CustomerDto();
		RequestContext ctxt = RequestContext.getCurrentInstance();
		ctxt.execute("PF('customer_dlg').show();");
		return null;
	}
	public String prepare4Edit() {
		try {
			if(customer.getCountry() != null) {
				states = locationService.listAllStates(customer.getCountry());
				if(customer.getState() != null) {
					cities = locationService.listAllCities(customer.getState());
				}
			}
			CustomerDto newC = new CustomerDto();
			newC.setId(customer.getId());
			newC.setAddress(customer.getAddress());
			newC.setAddress2(customer.getAddress2());
			newC.setCard(customer.getCard());
			newC.setCompanyId(customer.getCompanyId());
			newC.setEmail(customer.getEmail());
			newC.setFax(customer.getFax());
			newC.setFirstname(customer.getFirstname());
			newC.setLastname(customer.getLastname());
			newC.setName(customer.getName());
			newC.setNotes(customer.getNotes());
			newC.setPhone(customer.getPhone());
			newC.setPhone2(customer.getPhone2());
			newC.setPostal(customer.getPostal());
			newC.setSearchkey(customer.getSearchkey());
			newC.setCity(customer.getCity());
			newC.setCountry(customer.getCountry());
			newC.setCurdebt(customer.getCurdebt());
			newC.setMaxdebt(customer.getMaxdebt());
			newC.setState(customer.getState());
			newC.setVisible(customer.getVisible());
			customer = newC;
			
			updateMode = true;
			RequestContext ctxt = RequestContext.getCurrentInstance();
			ctxt.execute("PF('customer_dlg').show();");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String delete() {
		try {
			if (customerService.delete(customer.getId()) > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Customer removed Successfuly :)"));
				allCustomers.remove(customer);
				customer = new CustomerDto();
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
	
	
	
	public boolean isUpdateMode() {
		return updateMode;
	}
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
	public String save() {
		try {
			customer.setCompanyId(getSelectedCompany());
			int result  = customerService.save(customer);
			if(result > 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer Saved Successfuly :)"));
				if(allCustomers == null) allCustomers = new ArrayList<>();
				if(!updateMode) {
					if(allCustomers==null) allCustomers = new ArrayList<>();
					allCustomers.add(customer);
				}else {
					for(int i = 0 ; i<allCustomers.size() ; i++) {
						if(allCustomers.get(i).getId().equals(customer.getId())) {
							allCustomers.set(i, customer);
							break;
						}
					}
				}
				customer = new CustomerDto();
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
}
