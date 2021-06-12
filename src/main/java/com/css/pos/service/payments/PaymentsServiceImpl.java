package com.css.pos.service.payments;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.css.pos.dal.payments.PaymentsDal;
import com.css.pos.dto.payment.PaymentDto;

@Component
public class PaymentsServiceImpl implements PaymentsService {
	@Autowired
	private PaymentsDal paymentsDal;

	public PaymentsDal getPaymentsDal() {
		return paymentsDal;
	}

	public void setPaymentsDal(PaymentsDal paymentsDal) {
		this.paymentsDal = paymentsDal;
	}

	@Override
	public List<PaymentDto> list(String closeCashId) {
		// TODO Auto-generated method stub
		return paymentsDal.list(closeCashId);
	}

	@Override
	public int save(PaymentDto type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean closeCash(String cureentActiveCash) {
		
		return paymentsDal.closeCash(cureentActiveCash);
	}

	
	
}
