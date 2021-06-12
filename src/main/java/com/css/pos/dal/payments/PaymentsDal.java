package com.css.pos.dal.payments;



import com.css.pos.dal.common.CommonDal;

import com.css.pos.dto.payment.PaymentDto;

public interface PaymentsDal extends CommonDal<PaymentDto, String>{

	public boolean closeCash(String cureentActiveCash);

	
	
}
