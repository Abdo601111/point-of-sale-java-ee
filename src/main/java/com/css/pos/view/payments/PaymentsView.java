package com.css.pos.view.payments;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.css.pos.dto.payment.PaymentDto;
import com.css.pos.dto.security.UserDetailsDto;
import com.css.pos.service.payments.PaymentsService;
import com.css.pos.view.common.BaseBean;


@ManagedBean (eager=true)
@RequestScoped
@Component
@Lazy
public class PaymentsView extends BaseBean{
	@Autowired
	private PaymentsService paymentsBusiness;
	private List<PaymentDto> payments;
	
	public PaymentsService getPaymentsBusiness() {
		return paymentsBusiness;
	}

	public void setPaymentsBusiness(PaymentsService paymentsBusiness) {
		this.paymentsBusiness = paymentsBusiness;
	}

	public List<PaymentDto> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentDto> payments) {
		this.payments = payments;
	}
	private String cureentActiveCash;
	@PostConstruct
	public void init() {
		try {
			UserDetailsDto user = (UserDetailsDto)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("current_user");
			cureentActiveCash = user.getMoneyRecordID();
			payments = paymentsBusiness.list(cureentActiveCash);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public String closeCashBox() {
		/**
		 * 1- Set end date to current cash (Service)
		 * 2- Set current cash on user's session to null (View)
		 * 3- Open the payments Jasper report (Service/View)
		 */
		try {
			boolean isCashClosed = paymentsBusiness.closeCash(cureentActiveCash);
			if(isCashClosed) {
				UserDetailsDto user = (UserDetailsDto)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("current_user");
				user.setMoneyRecordID(null);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("current_user", user);
				
				/**
				 * Open the report to the response 
				 */
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cash Cannot be closed now \n Please try again later!!!"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
