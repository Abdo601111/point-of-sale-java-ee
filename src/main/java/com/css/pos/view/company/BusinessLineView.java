package com.css.pos.view.company;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.dto.company.BusinessLineDto;
import com.css.pos.service.company.BusinessLineService;
import com.css.pos.view.common.BaseBean;

@ManagedBean
@ViewScoped
@Component
public class BusinessLineView{
	private BusinessLineDto business = new BusinessLineDto(), selectedBusinesslline = new BusinessLineDto();

	public BusinessLineDto getSelectedBusinesslline() {
		return selectedBusinesslline;
	}

	public void setSelectedBusinesslline(BusinessLineDto selectedBusinesslline) {
		this.selectedBusinesslline = selectedBusinesslline;
	}
	
	public String update() {
		try {
			if (service.save(selectedBusinesslline) > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Business Line Updated Successfuly :)"));
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

	public String delete() {
		try {
			if (service.delete(selectedBusinesslline.getId()) > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Business Line removed Successfuly :)"));
				allbusinessLines.remove(selectedBusinesslline);
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

	public BusinessLineDto getBusiness() {
		return business;
	}

	public void setBusiness(BusinessLineDto business) {
		this.business = business;
	}

	@Autowired
	private BusinessLineService service;

	public BusinessLineService getService() {
		return service;
	}

	public void setService(BusinessLineService service) {
		this.service = service;
	}

	public String save() {
		try {
			if (service.save(business) > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Business Line Saved Successfuly :)"));
				allbusinessLines.add(business);
				business = new BusinessLineDto();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
						"Error", "Something went wrong please try again"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
		}

		return null;
	}

	public String listAll() {
		try {
			allbusinessLines = service.list(null);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
		}
		return null;
	}

	private List<BusinessLineDto> allbusinessLines = new ArrayList<>();

	public List<BusinessLineDto> getAllbusinessLines() {
		return allbusinessLines;
	}

	public void setAllbusinessLines(List<BusinessLineDto> allbusinessLines) {
		this.allbusinessLines = allbusinessLines;
	}

}
