package com.css.pos.common.util;

public class POSConstants {
	public static final Integer PRODUCT_TYPE_LOOKUP_LIST_CODE = 1;  //SAME AS ListTypes.xml values
	public static final Integer VISIBLE_STATUS_ON = 1;
	public static final Integer VISIBLE_STATUS_OFF = 0;
	public static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String DEFAULT_PASSWORD = "css@1234";
	public static final Integer NUM_OF_COLUMNS_IN_INVOICE = 4;
	public static final String DEFAULT_CAT_LOGO_PATH ="/resources/logos/category/DEFAULT_CAT_LOGO.png"; 
	public static final String DEFAULT_PROD_LOGO_PATH ="/resources/logos/category/DEFAULT_PROD_LOGO.png";
	
	/**
	 * Navigation cases
	 * 
	 */
	public static final Integer NAVIGATE_FIRST = -100;
	public static final Integer NAVIGATE_LAST = +100;
	public static final Integer NAVIGATE_PREVIOUS = -1;
	public static final Integer NAVIGATE_NEXT = +1;
	
	public static final String PAYMENT_CASH 				= "cash";
	public static final String PAYMENT_IN 					= "cashin";
	public static final String PAYMENT_OUT 					= "cashout";
	public static final String PAYMENT_REFUND 				= "cashrefund";
	public static final String PAYMENT_DEBT 				= "debt";
	public static final String PAYMENT_DEBT_PAID 			= "debtpaid";
	public static final String PAYMENT_SUPPLIER_DEBT_PAID 	= "supplierdebtpaid";
	public static final String PAYMENT_MAGCARD				= "magcard";
	
	public static final Integer IN_PURCHASE  = +1;
	public static final Integer IN_REFUND    = +2;
	public static final Integer IN_MOVEMENT  = +4;
	public static final Integer OUT_SALE     = -1;
	public static final Integer OUT_REFUND   = -2;
	public static final Integer OUT_BREAK    = -3; 
	public static final Integer OUT_MOVEMENT = -4;
	public static final Integer OUT_CROSSING = 1000;
	
	public static final Integer SEARCH_BY_CODE = 0;
	public static final Integer SEARCH_BY_NAME = 1;
	public static final Integer SEARCH_BY_ADVANCED = 2;
	
	public static String SUPPLIER_PAYMENTS_4_INVOICE = "Payment for Supplier from an invoice #";
}
