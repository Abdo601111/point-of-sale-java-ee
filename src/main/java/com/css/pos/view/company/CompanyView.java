package com.css.pos.view.company;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dto.company.BusinessLineDto;
import com.css.pos.dto.company.CompanyDto;
import com.css.pos.service.company.BusinessLineService;
import com.css.pos.service.company.CompanyService;
import com.css.pos.view.common.BaseBean;
@ManagedBean
@ViewScoped
@Component
public class CompanyView extends BaseBean{
	
	public void handleLogoUpload(FileUploadEvent event) {
		// TODO Auto-generated method stub
		try{
			String dbLogo = super.handleLogoUpload(event, "company.logo.upload_folder");
			company.setLogo(dbLogo);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void inti() {
		company = new CompanyDto();
		fillInBusinessLinesList();
		
	}
	public String loadCompanies() {
		setCompanies(getCompanyServices().list(getSelectedBLine()));

		return null;
	}
	private boolean updateMode;
	public String saveNewCompany() {
		try {
			int result  = getCompanyServices().save(company);
			if(result > 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Company Saved Successfuly :)"));
				if(getCompanies() == null) setCompanies(new ArrayList<>());
				if(!updateMode)
					getCompanies().add(company);
				company = new CompanyDto();
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
	public String prepareEdit() {
		try {
			updateMode = true;
			RequestContext ctxt = RequestContext.getCurrentInstance();
			ctxt.execute("PF('createCo_dlg').show();");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String delete() {
		try {
			if (getCompanyServices().delete(getSelectedCompany()) > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Company removed Successfuly :)"));
				getCompanies().remove(company);
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
	private CompanyDto company;
	public CompanyDto getCompany() {
		return company;
	}
	public void setCompany(CompanyDto company) {
		this.company = company;
	}
	
	private UploadedFile file;
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
}
