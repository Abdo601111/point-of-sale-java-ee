package com.css.pos.view.common;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dto.common.LookupDto;
import com.css.pos.dto.common.LookupTypeDto;

import com.css.pos.dto.company.BusinessLineDto;
import com.css.pos.dto.company.CompanyDto;
import com.css.pos.service.common.LookupDataService;
import com.css.pos.service.company.BranchService;
import com.css.pos.service.company.BusinessLineService;
import com.css.pos.service.company.CompanyService;

@ManagedBean
@ViewScoped
@Component
public class LookupdataView {
	@Autowired
	private LookupDataService lookupbusiness;
	private List<LookupTypeDto> allLookupTypes;
	public List<LookupTypeDto> getAllLookupTypes() {
		return allLookupTypes;
	}
	public void setAllLookupTypes(List<LookupTypeDto> allLookupTypes) {
		this.allLookupTypes = allLookupTypes;
	}
	private LookupTypeDto selectedLookupElement;
	private List<BusinessLineDto> blines;
	private List<LookupDto> alllookups = new ArrayList<>();
	private LookupDto selectedLUp;
	
	public void savelUp(ActionEvent e) {
		try {
			if(selectedLUp !=null) {
				if(lookupbusiness.save(selectedLUp)>0) {
					 FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Attribute saved SUCCESSFULY !"));
				 }else {
					 FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Try again | Error ocuured"));
				 }
					
			}
			
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public String addNewLookup() {
		if(alllookups == null)
			alllookups = new ArrayList<>();
		alllookups.add(new LookupDto());	
		return null;
	}
	public String addLookupType() {
		if(allLookupTypes != null && !allLookupTypes.isEmpty()) {
			allLookupTypes.add(new LookupTypeDto());
		}
		return null;
	}
	public LookupDto getSelectedLUp() {
		return selectedLUp;
	}
	public void setSelectedLUp(LookupDto selectedLUp) {
		this.selectedLUp = selectedLUp;
	}
	public List<LookupDto> getAlllookups() {
		return alllookups;
	}
	public void setAlllookups(List<LookupDto> alllookups) {
		this.alllookups = alllookups;
	}
	private String selectedBLine;
	private int selectedlookupTypeCode;
	public String loadLookups() {
		//connect to db to get the LOOKUP_DATA details with type = selectedlookupType.getcode()
		try {
			alllookups = new ArrayList<LookupDto>();
			alllookups = lookupbusiness.listAllLookupElements(selectedlookupTypeCode,
					selectedCompany);//
		}catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Try again | Error ocuured"));
		}
		return null;
	}
	public String listAllLookupTypes() {
		try {
			allLookupTypes = lookupbusiness.listAlllookupElement();
		}catch (Exception e) {
			// TODO: handle exception
			e.getSuppressed();
		}
		
		return null;
	}
	public int getSelectedlookupTypeCode() {
		return selectedlookupTypeCode;
	}
	public void setSelectedlookupTypeCode(int selectedlookupTypeCode) {
		this.selectedlookupTypeCode = selectedlookupTypeCode;
	}
	private List<CompanyDto> companies;
	private String selectedCompany;
	public String getSelectedCompany() {
		return selectedCompany;
	}
	public void setSelectedCompany(String selectedCompany) {
		this.selectedCompany = selectedCompany;
	}
	public List<BusinessLineDto> getBlines() {
		return blines;
	}
	public void setBlines(List<BusinessLineDto> blines) {
		this.blines = blines;
	}
	public String getSelectedBLine() {
		return selectedBLine;
	}
	public void setSelectedBLine(String selectedBLine) {
		this.selectedBLine = selectedBLine;
	}
	@Autowired
	private BusinessLineService blineService;
	public BusinessLineService getBlineService() {
		return blineService;
	}
	public void setBlineService(BusinessLineService blineService) {
		this.blineService = blineService;
	}
	@Autowired
	private BranchService branchService;
	@Autowired
	private CompanyService companyServices;
	public CompanyService getCompanyServices() {
		return companyServices;
	}
	public void setCompanyServices(CompanyService companyServices) {
		this.companyServices = companyServices;
	}
	public List<CompanyDto> getCompanies() {
		return companies;
	}
	public void setCompanies(List<CompanyDto> companies) {
		this.companies = companies;
	}
	public BranchService getBranchService() {
		return branchService;
	}
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}
	public LookupTypeDto getSelectedLookupElement() {
		return selectedLookupElement;
	}
	public void setSelectedLookupElement(LookupTypeDto selectedLookupElement) {
		this.selectedLookupElement = selectedLookupElement;
	}
	@PostConstruct
	public void initiatePage() {
		try {
			allLookupTypes = lookupbusiness.listAlllookupElement();
			blines = blineService.list(null);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadBusinessLineCompanies() {
		companies = companyServices.list(selectedBLine);
	}
	public String saveLType() {
		try {
			boolean done= false;
			 if(selectedLookupElement.getCode() != null) {
				 done = lookupbusiness.UpdateListElement(selectedLookupElement);
			 }else {
				 done = lookupbusiness.addListElement(selectedLookupElement);
				 
			 }
			 if(done) {
				 FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Element added SUCCESSFULY !"));
			 }else {
				 FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Try again | Error ocuured"));
			 }
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
		}
		return null;
	}
	public String deleteLType() {
		
		return null;
	}
	
	
	public String saveLUp() {
		try {
			selectedLUp.setType(selectedlookupTypeCode);
			selectedLUp.setCompanyId(selectedCompany);
			 if(lookupbusiness.save(selectedLUp)>0) {
				 FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Data Saved SUCCESSFULY !"));
			 }else {
				 FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Try again | Error ocuured"));
			 }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
		}
		return null;
	}
	public String deleteLUp() {
		try {
			 if(lookupbusiness.delete(selectedLUp.getId())>0) {
				 FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Data Saved SUCCESSFULY !"));
				 alllookups.remove(selectedLUp);
			 }else {
				 FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Try again | Error ocuured"));
			 }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
		}
		return null;
	}
	public LookupDataService getLookupbusiness() {
		return lookupbusiness;
	}
	public void setLookupbusiness(LookupDataService lookupbusiness) {
		this.lookupbusiness = lookupbusiness;
	}

	
	
}
