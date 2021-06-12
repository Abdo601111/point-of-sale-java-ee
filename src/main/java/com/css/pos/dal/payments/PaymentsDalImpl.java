package com.css.pos.dal.payments;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import com.css.pos.domain.Closedcash;
import com.css.pos.domain.People;
import com.css.pos.domain.Product;
import com.css.pos.domain.Stockcurrent;
import com.css.pos.domain.Stockdiary;
import com.css.pos.domain.Stockin;
import com.css.pos.domain.Stokenpybill;
import com.css.pos.domain.Supplier;
import com.css.pos.domain.SuppliersPayment;
import com.css.pos.dto.invoices.InvoiceDto;
import com.css.pos.dto.invoices.InvoiceItemDto;
import com.css.pos.dto.payment.PaymentDto;

@Repository
public class PaymentsDalImpl implements PaymentsDal {
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
	public List<PaymentDto> list(String moneyRecordId) {
		List<PaymentDto> payments = new ArrayList<PaymentDto>();
		try {
				String sql = "SELECT PAYMENTS.PAYMENT, SUM(PAYMENTS.TOTAL) AS TOTAL, PAYMENTS.WRITEWHY " 
							+"FROM PAYMENTS, RECEIPTS WHERE PAYMENTS.RECEIPT = RECEIPTS.ID AND RECEIPTS.MONEY "
							+ "= '"+moneyRecordId+"' GROUP BY PAYMENTS.PAYMENT,PAYMENTS.WRITEWHY";
				
				
				SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				PaymentDto pay = null;
				List data = query.list();
				if (data != null && data.size() > 0) {
					for(int i = 0 ; i < data.size() ; i++){
						pay = new PaymentDto();
						Object object = data.get(i);
						Map row = (Map) object;
						if(row.get("PAYMENT") != null)
							pay.setPayment(row.get("PAYMENT").toString());
						if (row.get("TOTAL") != null)
							pay.setTotal(new Double(row.get("TOTAL").toString()));
						if (row.get("WRITEWHY") != null)
							pay.setWritewhy(row.get("WRITEWHY").toString());
						payments.add(pay);
					}
				} 
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return payments;
	}

	@Override
	@Transactional
	public int save(PaymentDto type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean closeCash(String cureentActiveCash) {
		try {
			//create new closed cash and insert it in the DB
			String sql = "UPDATE closedcash SET dateend = sysdate WHERE money ='"+cureentActiveCash+"'";
		
		
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		int affectedRecords = query.executeUpdate();
		if(affectedRecords>0)
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

}














