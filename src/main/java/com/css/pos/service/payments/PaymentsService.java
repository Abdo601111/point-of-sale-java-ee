package com.css.pos.service.payments;

import com.css.pos.dto.payment.PaymentDto;
import com.css.pos.service.common.CommonService;

public interface PaymentsService extends CommonService<PaymentDto,String>{

	public boolean closeCash(String cureentActiveCash);

	

	
}
