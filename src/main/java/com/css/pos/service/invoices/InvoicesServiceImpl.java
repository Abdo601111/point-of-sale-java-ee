package com.css.pos.service.invoices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.pos.common.util.POSConstants;
import com.css.pos.dal.invoices.InvoicesDal;
import com.css.pos.dal.security.UserDal;
import com.css.pos.dto.category.ProductDto;
import com.css.pos.dto.invoices.InvoiceDto;
import com.css.pos.dto.security.UserDetailsDto;

@Component
public class InvoicesServiceImpl implements InvoicesService {
	@Autowired
	private InvoicesDal invoicesDal;

	public InvoicesDal getInvoicesDal() {
		return invoicesDal;
	}

	public void setInvoicesDal(InvoicesDal invoicesDal) {
		this.invoicesDal = invoicesDal;
	}

	@Override
	public List<InvoiceDto> list(String id) {
		// TODO Auto-generated method stub
		return invoicesDal.list(id);
	}

	@Override
	public int save(InvoiceDto invoice) {
		if(invoice.getId() == null)
			invoice.setId(UUID.randomUUID().toString());
		return invoicesDal.save(invoice);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return invoicesDal.delete(id);
	}

	@Override
	public Map<String, Integer> getMovmentTypesMap() {
		// TODO Auto-generated method stub
		Map<String, Integer> invoiceTypes = new HashMap<>();
		invoiceTypes.put("Purchase In", POSConstants.IN_PURCHASE);
		invoiceTypes.put("Refund In", POSConstants.IN_REFUND);
		invoiceTypes.put("Movement In", POSConstants.IN_MOVEMENT); //movement across the company branches
		
		invoiceTypes.put("Break Out", POSConstants.OUT_BREAK);
		invoiceTypes.put("Crossing Out", POSConstants.OUT_CROSSING);
		invoiceTypes.put("Movement Out", POSConstants.OUT_MOVEMENT);
		invoiceTypes.put("Refund Out", POSConstants.OUT_REFUND);
		invoiceTypes.put("Sale Out", POSConstants.OUT_SALE);
		
		return invoiceTypes;
	}

	@Override
	public InvoiceDto search4Invoice(Integer searchInvNumber, String branchId, Integer supplier, Integer movType) {
		// TODO Auto-generated method stub
		return invoicesDal.search4Invoice(searchInvNumber, branchId, supplier, movType) ;
	}

	@Override
	public InvoiceDto loadFirstInvoice(String branchId, Integer supplier, Integer movType) {
		// TODO Auto-generated method stub
		Integer fInvNum = invoicesDal.getInvoiceNumber(POSConstants.NAVIGATE_FIRST, null);
		if(fInvNum != null && fInvNum != -1)
			return invoicesDal.search4Invoice(fInvNum, branchId, supplier, movType);
		return new InvoiceDto();
	}

	@Override
	public InvoiceDto loadNextToInvoice(Integer currentInvoiceNumber, String branchId, Integer supplier,
			Integer movType) {
		// TODO Auto-generated method stub
		Integer fInvNum = invoicesDal.getInvoiceNumber(POSConstants.NAVIGATE_NEXT, currentInvoiceNumber);
		if(fInvNum != null && fInvNum != 1)
			return invoicesDal.search4Invoice(fInvNum, branchId, supplier, movType);
		return new InvoiceDto();
	}

	@Override
	public InvoiceDto loadLastInvoice(String branchId, Integer supplier, Integer type) {
		// TODO Auto-generated method stub
				Integer fInvNum = invoicesDal.getInvoiceNumber(POSConstants.NAVIGATE_LAST, null);
				if(fInvNum != null && fInvNum != -1)
					return invoicesDal.search4Invoice(fInvNum, branchId, supplier, type);
				return new InvoiceDto();
	}

	@Override
	public InvoiceDto loadPrevInvoice(Integer currentInvoiceNumber, String branchId, Integer supplier, Integer type) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Integer fInvNum = invoicesDal.getInvoiceNumber(POSConstants.NAVIGATE_PREVIOUS, currentInvoiceNumber);
		if(fInvNum != null && fInvNum != 1)
			return invoicesDal.search4Invoice(fInvNum, branchId, supplier, type);
		return new InvoiceDto();
	}

	@Override
	public boolean delete(String branchId, Integer supplier, Integer type, Integer billNum) {
		// TODO Auto-generated method stub
		return invoicesDal.delete(branchId, supplier, type, billNum);
	}

	
}
