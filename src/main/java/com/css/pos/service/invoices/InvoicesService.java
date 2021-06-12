package com.css.pos.service.invoices;

import java.util.List;
import java.util.Map;

import com.css.pos.dto.category.ProductDto;
import com.css.pos.dto.invoices.InvoiceDto;
import com.css.pos.service.common.CommonService;

public interface InvoicesService extends CommonService<InvoiceDto,String>{

	public Map<String, Integer> getMovmentTypesMap();

	public InvoiceDto search4Invoice(Integer searchInvNumber, String branchId, Integer supplier,Integer movType);
	public InvoiceDto loadFirstInvoice(String branchId, Integer supplier, Integer movType);
	public InvoiceDto loadNextToInvoice(Integer currentInvoiceNumber, String branchId, Integer supplier,Integer movType);

	public InvoiceDto loadLastInvoice(String branchId, Integer supplier, Integer type);

	public InvoiceDto loadPrevInvoice(Integer suppInvoiceNumber, String branchId, Integer supplier, Integer type);

	public boolean delete(String branchId, Integer supplier, Integer type, Integer billNum) ;

	
}
