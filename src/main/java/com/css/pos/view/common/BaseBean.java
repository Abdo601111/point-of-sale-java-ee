package com.css.pos.view.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.common.util.POSUtil;
import com.css.pos.dto.company.BranchDto;
import com.css.pos.dto.company.BusinessLineDto;
import com.css.pos.dto.company.CompanyDto;
import com.css.pos.service.company.BranchService;
import com.css.pos.service.company.BusinessLineService;
import com.css.pos.service.company.CompanyService;
@Component
@ManagedBean
public class BaseBean {
	@Autowired
	private BusinessLineService blineService;
	@Autowired
	private CompanyService companyServices;
	@Autowired
	private BranchService branchServices;
	
	public BranchService getBranchServices() {
		return branchServices;
	}

	public void setBranchServices(BranchService branchServices) {
		this.branchServices = branchServices;
	}
	private List<BusinessLineDto> blines;
	private List<CompanyDto> companies;
	public List<BranchDto> getBranches() {
		return branches;
	}

	public void setBranches(List<BranchDto> branches) {
		this.branches = branches;
	}

	public String getSelectedBranch() {
		return selectedBranch;
	}

	public void setSelectedBranch(String selectedBranch) {
		this.selectedBranch = selectedBranch;
	}
	private List<BranchDto> branches;
	private String selectedBranch;
	private String selectedCompany;
	private String selectedBLine;
	private String selectedLanguage;
	public String getSelectedLanguage() {
		return selectedLanguage;
	}
	
	public void setSelectedLanguage(String selectedLanguage) {
		this.selectedLanguage = selectedLanguage;
	}
	public void switchlanguage(ValueChangeEvent e) {
		try {
			System.out.println(selectedLanguage);
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(selectedLanguage));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("current_lang", selectedLanguage);
	
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	public void fillInBusinessLinesList() {
		blines = blineService.list(null);
	}
	public String handleLogoUpload(FileUploadEvent event, String resourcePath) {
		String dbLogo = null;
		try {
			String name = POSUtil.getRandomLogoTitle();
			InputStream fis = event.getFile().getInputstream();
			OutputStream outputStream = null;
			
			String uploadFolderPath = null;
			//1- get the app root physical location
			ServletContext ctxt = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
			StringBuilder o = new StringBuilder(ctxt.getRealPath("/"));
			//2- append the [company.logo.upload_folder] from bundle to the path
			o.append(POSUtil.getPropertyFromResource(resourcePath));
			
			if(o != null)
				uploadFolderPath = o.toString();
				String logoFName = name +"."
						+((String[])event.getFile().getContentType().split("/"))[1];
				String logoFullPath = uploadFolderPath + logoFName;
				outputStream = 
		                    new FileOutputStream(new File(logoFullPath));
				int read = 0;
				byte[] bytes = new byte[1024];
		 
				while ((read = fis.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}		
			 dbLogo = POSUtil.getPropertyFromResource(resourcePath).toString().replace("\\", "/")+"/"+logoFName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return dbLogo;
	}
	public void loadCompanyBranches() {
		try {
			branches = branchServices.list(selectedCompany);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<BusinessLineDto> getBlines() {
		return blines;
	}
	public void setBlines(List<BusinessLineDto> blines) {
		this.blines = blines;
	}
	public List<CompanyDto> getCompanies() {
		return companies;
	}
	public void setCompanies(List<CompanyDto> companies) {
		this.companies = companies;
	}
	public String getSelectedCompany() {
		return selectedCompany;
	}
	public void setSelectedCompany(String selectedCompany) {
		this.selectedCompany = selectedCompany;
	}
	public String getSelectedBLine() {
		return selectedBLine;
	}
	public void setSelectedBLine(String selectedBLine) {
		this.selectedBLine = selectedBLine;
	}
	public BusinessLineService getBlineService() {
		return blineService;
	}
	public void setBlineService(BusinessLineService blineService) {
		this.blineService = blineService;
	}
	public CompanyService getCompanyServices() {
		return companyServices;
	}
	public void setCompanyServices(CompanyService companyServices) {
		this.companyServices = companyServices;
	}
	/**
	 * Listener to load the business line companies
	 */
	public void loadBusinessLineCompanies() {
		try {
			companies = companyServices.list(selectedBLine);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(companies.size()+" from base_bean");
		
	}
	protected void addMsgToFC(String msgDtls, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				severity,msgDtls,msgDtls));
		
	}
}
