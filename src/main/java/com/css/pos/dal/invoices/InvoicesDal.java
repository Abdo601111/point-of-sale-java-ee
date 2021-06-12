package com.css.pos.dal.invoices;


import java.util.List;

import com.css.pos.dal.common.CommonDal;
import com.css.pos.dto.category.ProductDto;
import com.css.pos.dto.invoices.InvoiceDto;

public interface InvoicesDal extends CommonDal<InvoiceDto, String>{

	public InvoiceDto search4Invoice(Integer searchInvNumber, String branchId,Integer supplier, Integer movType);
	public Integer getInvoiceNumber(Integer code, Integer invoiceNum);
	public boolean delete(String branchId, Integer supplier, Integer type, Integer billNum) ;
	
}
