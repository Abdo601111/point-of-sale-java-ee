package com.css.pos.dal.invoices;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.css.pos.common.util.POSConstants;
import com.css.pos.domain.Branch;
import com.css.pos.domain.Category;
import com.css.pos.domain.Product;
import com.css.pos.domain.Stockcurrent;
import com.css.pos.domain.Stockdiary;
import com.css.pos.domain.Stockin;
import com.css.pos.domain.Stokenpybill;
import com.css.pos.domain.Supplier;
import com.css.pos.domain.SuppliersPayment;
import com.css.pos.dto.invoices.InvoiceDto;
import com.css.pos.dto.invoices.InvoiceItemDto;

@Repository
public class InvoicesDalImpl implements InvoicesDal {
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public int save(InvoiceDto invoice) {
		try {
			/**
			 * Purchasing
			 */
			if(invoice.getType().equals(POSConstants.IN_PURCHASE)) {
				/**
				 * Accumulate the stock
				 */
				Stockcurrent tempStock = null;
				Stockdiary sDairy= null;
				Stokenpybill sPyBill = null;
				double oldUnits = 0f, oldUnitCost = 0f;
				List dbList = null;
				for(InvoiceItemDto item : invoice.getItems()) {
					/**
					 * 1- STEP (1): Insert / Update Stock current record(s):-
					 * Search 4 existing stock for such item
					 */
					
					dbList=  getSessionFactory().getCurrentSession().createCriteria(Stockcurrent.class)
							.add(Restrictions.eq("productBean.id", item.getProducId()))
							.add(Restrictions.eq("branch.id", invoice.getBranch())).list();
					if(dbList != null && !dbList.isEmpty())
						tempStock = (Stockcurrent)dbList.get(0);
					else
						tempStock = null;
					if(tempStock != null) {
						//there exist a stock 3-->5        4-->6      units 3+4 = 7  (Average Cost = (3*5 + 4*6)/(7))
						oldUnits = tempStock.getUnits(); oldUnitCost = tempStock.getCost(); 
						tempStock.setUnits(oldUnits + item.getUnits());
						tempStock.setCost((item.getUnits() * item.getPrice() + oldUnitCost * oldUnits)/(item.getUnits() + oldUnits));
						tempStock.setTotal(item.getUnits() * item.getPrice() + oldUnitCost * oldUnits);
					}else {
						tempStock = new Stockcurrent();
						tempStock.setId(UUID.randomUUID().toString());
						tempStock.setProductBean(new Product(item.getProducId()));
						tempStock.setBranch(new Branch(invoice.getBranch()));
						tempStock.setUnits(item.getUnits());
						tempStock.setCost(item.getPrice());
						tempStock.setTotal(item.getUnits() * item.getPrice());
					}
					getSessionFactory(). getCurrentSession().saveOrUpdate(tempStock);
					
					/**
					 * 2- STEP (2): Insert a Stock dairy record(s) :-
					 * 
					 */
					sDairy = new Stockdiary();
					sDairy.setId(UUID.randomUUID().toString());
					sDairy.setDatenew(invoice.getInvDate());
					sDairy.setLocation(invoice.getBranch());
					sDairy.setProductBean(new Product(item.getProducId()));
					sDairy.setPrice(item.getPrice());
					sDairy.setUnits(item.getUnits());
					sDairy.setBillNumber(invoice.getSuppInvoiceNumber());
					sDairy.setReason(POSConstants.IN_PURCHASE);
					sDairy.setSupplierNum(invoice.getSupplier());
					getSessionFactory(). getCurrentSession().saveOrUpdate(sDairy);
					
					/**
					 * 3- STEP (3): Insert a Stokenpybill record(s) :-
					 * 
					 */
					sPyBill = new Stokenpybill();
					sPyBill.setId(UUID.randomUUID().toString());
					sPyBill.setQuantity(item.getUnits());
					sPyBill.setBillid(invoice.getSuppInvoiceNumber());
					sPyBill.setStickenname(item.getProducId());
					getSessionFactory(). getCurrentSession().saveOrUpdate(sPyBill);
					
				}
				
				/**
				 * 4- STEP (4): Insert a Stockin record :-
				 * 
				 */
				Stockin sIn = new Stockin();
				sIn.setId(UUID.randomUUID().toString());
				sIn.setSupplierBean(new Supplier(invoice.getSupplier()));
				sIn.setDatein(invoice.getInvDate());
				sIn.setDebt(invoice.getDebitPayment());
				sIn.setTotal(invoice.getTotal());
				getSessionFactory(). getCurrentSession().saveOrUpdate(sIn);
				
				/**
				 * 5- STEP (5): Insert a SuppliersPayments record :-
				 * 
				 */
				
				if(invoice.getCashPayment() != 0) {
					SuppliersPayment sPay = new SuppliersPayment();
					sPay.setPaymentDate(new Timestamp(invoice.getInvDate().getTime()));
					sPay.setSupplier(new Supplier(invoice.getSupplier()));
					sPay.setPaymentNote(POSConstants.SUPPLIER_PAYMENTS_4_INVOICE + invoice.getSuppInvoiceNumber());
					sPay.setPaymentValue(invoice.getCashPayment());
					sPay.setPaymentMethod(POSConstants.PAYMENT_CASH);
					getSessionFactory(). getCurrentSession().saveOrUpdate(sPay);
					
				}
				if(invoice.getCreditPayment() != 0) {
					SuppliersPayment sPay = new SuppliersPayment();
					sPay.setPaymentDate(new Timestamp(invoice.getInvDate().getTime()));
					sPay.setSupplier(new Supplier(invoice.getSupplier()));
					sPay.setPaymentNote(POSConstants.SUPPLIER_PAYMENTS_4_INVOICE + invoice.getSuppInvoiceNumber());
					sPay.setPaymentValue(invoice.getCreditPayment());
					sPay.setPaymentMethod(POSConstants.PAYMENT_MAGCARD);
					getSessionFactory(). getCurrentSession().saveOrUpdate(sPay);
				}
				
				
				
			}
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	@Transactional
	public int delete(String roleId) {
		try {
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;			
		}

	}

	@Override
	public List<InvoiceDto> list(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public InvoiceDto search4Invoice(Integer searchInvNumber, String branchId, Integer supplier,Integer movType) {
		return getInvoice(searchInvNumber, branchId, supplier, movType);
	}
	@Transactional
	private InvoiceDto getInvoice(Integer searchInvNumber, String branchId, Integer supplier,Integer movType) {
		try {
			InvoiceDto invoice = new InvoiceDto();
			List<Stockdiary> stocks = getSessionFactory().getCurrentSession().createCriteria(Stockdiary.class)
					.add(Restrictions.eq("billNumber", searchInvNumber))
					.add(Restrictions.eq("location", branchId))
					.add(Restrictions.eq("reason", movType))
					.add(Restrictions.eq("supplierNum", supplier))
					.list();
			if(stocks != null && !stocks.isEmpty()) {
				invoice.setSuppInvoiceNumber(searchInvNumber);
				invoice.setType(movType);
				InvoiceItemDto ii =  null;
				 
				for(Stockdiary sd:stocks) {
					invoice.setId(UUID.randomUUID().toString());
					if(invoice.getInvDate() == null) 
						invoice.setInvDate(sd.getDatenew());
					System.out.println(sd.getDatenew());
					if(invoice.getSupplier() == null)
						invoice.setSupplier(sd.getSupplierNum());
					ii = new InvoiceItemDto();
					ii.setProducId(sd.getProductBean().getId());
					ii.setUnits(sd.getUnits());
					ii.setPrice(sd.getPrice());
					ii.setProductName(sd.getProductBean().getName());
					if(invoice.getItems() == null)
						invoice.setItems(new ArrayList<>());
					invoice.getItems().add(ii);
					invoice.setTotal(invoice.getTotal() + ii.getCost());
				}
				
				/** 
				 * supplier payments
				 */
				if(movType.equals(POSConstants.IN_PURCHASE)) {
					List<SuppliersPayment> sPayments = getSessionFactory().getCurrentSession().createCriteria(SuppliersPayment.class)
							.add(Restrictions.eq("paymentDate", new Timestamp(invoice.getInvDate().getTime())))
							.add(Restrictions.eq("supplier", new Supplier(invoice.getSupplier())))
							.list();
					if(sPayments != null && !sPayments.isEmpty()) {
						for(SuppliersPayment sp : sPayments) {
							if(sp.getPaymentMethod().equals(POSConstants.PAYMENT_CASH)) {
								invoice.setCashPayment(sp.getPaymentValue());
							}else if(sp.getPaymentMethod().equals(POSConstants.PAYMENT_MAGCARD)) {
								invoice.setCreditPayment(sp.getPaymentValue());
							}
						}
					}
					
					/**
					 * To get the supplier debit payment
					 */
					List<Stockin> stokein = getSessionFactory().getCurrentSession().createCriteria(Stockin.class)
							.add(Restrictions.eq("datein", invoice.getInvDate()))
							.add(Restrictions.eq("supplierBean.id", invoice.getSupplier()))
							.list();
					if(stokein != null && !stokein.isEmpty()) {
						Stockin tS = stokein.get(0);
						invoice.setDebitPayment(tS.getDebt());
					}
				}
			}
			
			return invoice;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new InvoiceDto();
		}
	}
	@Override
	@Transactional

	public Integer getInvoiceNumber(Integer code, Integer currentInvNum) {
		Integer invoiceNumber = -1;
		try {
			StringBuffer query = new StringBuffer();
			switch (code) {
			case -100: //1st
				query.append("SELECT MIN(BILL_NUM) AS BILL_NUM FROM STOCKDIARY");
				break;
			case +1://NEXT
				query.append("SELECT MIN(BILL_NUM) AS BILL_NUM FROM STOCKDIARY WHERE BILL_NUM>");
			    query.append(currentInvNum.toString());
				break;
			case 100: //LAST
				query.append("SELECT MAX(BILL_NUM) AS BILL_NUM FROM STOCKDIARY");
				break;
			case -1://PREV
				query.append("SELECT MAX(BILL_NUM) AS BILL_NUM FROM STOCKDIARY WHERE BILL_NUM<");
			    query.append(currentInvNum.toString());
				break;	
				
			}
			SQLQuery sqlQury =  getSessionFactory().getCurrentSession().createSQLQuery(query.toString());
			sqlQury.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List data =  sqlQury.list();
			if(data != null && !data.isEmpty()) {
				Map map = (Map) data.get(0);
				invoiceNumber = Integer.parseInt(map.get("BILL_NUM").toString());
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return invoiceNumber;
	}

	@Override
	@Transactional
	public boolean delete(String branchId, Integer supplier, Integer type, Integer billNum) {
		try {
			/**
			 * Purchasing
			 */
			if(type.equals(POSConstants.IN_PURCHASE)) {
				/**
				 * Update current stock
				 */
				InvoiceDto inv =  search4Invoice( billNum, branchId, supplier, type);
				if(inv != null) {
					Stockcurrent tempStock = null;
					double oldUnits = 0f, oldUnitCost = 0f;
					List dbList = null;
					if(inv.getItems() != null && !inv.getItems().isEmpty())
					for(InvoiceItemDto item : inv.getItems()) {
						/**
						 * 1- STEP (1): Insert / Update Stock current record(s):-
						 * Search 4 existing stock for such item
						 */
						
						dbList=  getSessionFactory().getCurrentSession().createCriteria(Stockcurrent.class)
								.add(Restrictions.eq("productBean.id", item.getProducId()))
								.add(Restrictions.eq("branch.id", branchId)).list();
						if(dbList != null && !dbList.isEmpty())
							tempStock = (Stockcurrent)dbList.get(0);
						else
							tempStock = null;
						if(tempStock != null) {
							//there exist a stock 3-->5        4-->6      units 3+4 = 7  (Average Cost = (3*5 + 4*6)/(7))
							oldUnits = tempStock.getUnits(); oldUnitCost = tempStock.getCost(); 
							tempStock.setUnits(oldUnits - item.getUnits());
							tempStock.setCost(-(item.getUnits() * item.getPrice() + oldUnitCost * oldUnits)/(item.getUnits() + oldUnits));
							tempStock.setTotal(-item.getUnits() * item.getPrice() +  oldUnitCost * oldUnits);
						}
						getSessionFactory(). getCurrentSession().saveOrUpdate(tempStock);
						
						
						
						/**
						 * Delete Stockin
						 */
						Stockin st = new Stockin();
						st.setDatein(inv.getInvDate());
						st.setSupplierBean(new Supplier(inv.getSupplier()));
						getSessionFactory().getCurrentSession().delete(st);
						
						
						/**
						 * Delete Supplier Payments
						 */
						SuppliersPayment sp = new SuppliersPayment();
						
						if(inv.getInvDate() != null) {
							sp.setPaymentDate(new Timestamp(inv.getInvDate().getTime()));
							sp.setSupplier(new Supplier(supplier));
							getSessionFactory().getCurrentSession().delete(st);
						}
					}
					/**
					 * Delete stockdairy
					 */
					getSessionFactory().getCurrentSession().createSQLQuery("DELETE FROM stockdiary " + 
							"WHERE reason = " + type +" AND   location = '"+branchId+"'" + 
							"    AND   bill_num = " + billNum+ 
							"    AND   supplier_num = "+ supplier).executeUpdate();
					
					
					/**
					 * Delete Stockenpybill
					 */
					getSessionFactory().getCurrentSession().createSQLQuery("DELETE FROM stokenpybill WHERE billid ='"
							+ billNum+"'").executeUpdate();
					
					
				}
			}	
				
				
			
		return true;	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}



}
