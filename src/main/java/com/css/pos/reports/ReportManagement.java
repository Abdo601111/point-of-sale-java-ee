package com.css.pos.reports;


import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@ManagedBean(eager = true)
@ViewScoped
public class ReportManagement 
 //extends BaseBean 
implements Serializable{
	private String logosPath = "";
	private String companyLogo = "";
	private String userId = "", searchName;
	
	private boolean reportsAttach = true; 
	
	public String getSearchName() {
		return searchName;
	}
	public String selectSupplier(){
//		supplierDebit = supplier.getCurrentDebit();
		return null;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	private Integer minimumStockLimit = new Integer(0);
	public Integer getMinimumStockLimit() {
		return minimumStockLimit;
	}
	public void setMinimumStockLimit(Integer minimumStockLimit) {
		this.minimumStockLimit = minimumStockLimit;
	}
	
	
	@PostConstruct
	public void init(){
		
		Object o = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("upload_folder_path");
//		if(o != null){
//			UserInfoDto user = (UserInfoDto) FacesContext.getCurrentInstance()
//					.getExternalContext().getSessionMap().get("currentUser");
//			userId = user.getId();
//			companyName = user.getCompanyName();
//			companyAddress = user.getCompanyTelephone();
//			companyTelephone = user.getCompanyTelephone();
//			String logo = user.getCompanyLogo();
//			String physicalPath = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/")+"resources\\upload\\";
//			logosPath = physicalPath;
//			if(!new File(physicalPath + logo).exists()){
//				companyLogo = logosPath + Constants.DEFAULT_COMPANY_LOGO;
//			}else
//				companyLogo = logosPath + logo;
//		}
	}
	
	String reportName = "";
	HashMap<Object, Object> reportfields = new HashMap<Object, Object>();
	
	
	
	
	public String exportCompanyProfitReport(){
		reportfields.put("user", userId);
        reportfields.put("path", companyLogo);
        reportName = "companyProfits.jasper";
		printReport(reportName, reportfields, reportsAttach);
		FacesContext.getCurrentInstance().renderResponse();
        FacesContext.getCurrentInstance().responseComplete();
		return null;
	}
	public String exportCompanyProfitInIntervalReport(){
		reportfields.put("user", userId);
        reportfields.put("path", companyLogo);
        reportfields.put("start", startDate);
        reportfields.put("end", endDate);
        reportName = "companyProfitInterval.jasper";
		printReport(reportName, reportfields, reportsAttach);
		FacesContext.getCurrentInstance().renderResponse();
        FacesContext.getCurrentInstance().responseComplete();
		
		return null;
	}
	
	private String billnumber;
	public String getBillnumber() {
		return billnumber;
	}
	public void setBillnumber(String billnumber) {
		this.billnumber = billnumber;
	}
	public String exportPurchaseInvoiceReport(){
		reportfields.put("billNo", billnumber);
        reportfields.put("path", companyLogo);
		reportName = "suppliers_bill.jasper";
		printReport(reportName, reportfields, reportsAttach);
		FacesContext.getCurrentInstance().renderResponse();
        FacesContext.getCurrentInstance().responseComplete();
		
		return null;
	}
	
	public String exportCustomersReport(){
		reportfields = new HashMap<Object, Object>();
		reportfields.put("user", userId);
        reportfields.put("path", companyLogo);
		
        reportName = "customers.jasper";
//		reportfields.put("branchId", selectedBranch);
		
		printReport(reportName, reportfields, reportsAttach);
		FacesContext.getCurrentInstance().renderResponse();
        FacesContext.getCurrentInstance().responseComplete();
		
		return null;
	}
	private Date startDate, endDate;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String exportClosedCashReport(){
		reportfields = new HashMap<Object, Object>();
		reportfields.put("start", startDate.toString());
        reportfields.put("end", endDate.toString());
//        reportfields.put("branchId", selectedBranch);
        reportName = "bill.jasper";		
		printReport(reportName, reportfields, reportsAttach);
		FacesContext.getCurrentInstance().renderResponse();
        FacesContext.getCurrentInstance().responseComplete();
		
		return null;
	}
	
	public String exportIncoDepitReport(){
		reportfields = new HashMap<Object, Object>();
		reportfields.put("user", userId);
        reportfields.put("path", companyLogo);
        reportName = "insuranceDept.jasper";		
		printReport(reportName, reportfields, reportsAttach);
		FacesContext.getCurrentInstance().renderResponse();
        FacesContext.getCurrentInstance().responseComplete();
		
		return null;
	}
	public String exportCustomersDiaryReport(){
		reportfields = new HashMap<Object, Object>();
		reportfields.put("user", userId);
        reportfields.put("path", companyLogo);
		
        reportName = "customersdiary.jasper";
		
		printReport(reportName, reportfields, reportsAttach);

		FacesContext.getCurrentInstance().renderResponse();
        FacesContext.getCurrentInstance().responseComplete();
		
		return null;
	}
	public String exportSupplierReport(){
		reportfields = new HashMap<Object, Object>();
		reportfields.put("user", userId);
        reportfields.put("path", companyLogo);
		
        reportName = "suppliers.jasper";
		
		printReport(reportName, reportfields, reportsAttach);
		FacesContext.getCurrentInstance().renderResponse();
        FacesContext.getCurrentInstance().responseComplete();
		
		return null;
	}
	public String exportSupplierBillsReport(){
		reportfields = new HashMap<Object, Object>();
		reportfields.put("user", userId);
        reportfields.put("path", companyLogo);
		
        reportName = "suppliers_bills.jasper";
		
		printReport(reportName, reportfields, reportsAttach);
		FacesContext.getCurrentInstance().renderResponse();
        FacesContext.getCurrentInstance().responseComplete();
		
		return null;
	}
	public String exportCloseCashInIntervalReport(){
//		reportfields = new HashMap<Object, Object>();
//		reportfields.put("branch", PosUtil.getKeyFromMapByValue(branchMap, selectedBranch));
//		reportfields.put("start", startDate);
//		reportfields.put("end", endDate);
//        reportfields.put("path", companyLogo);
//		
//        reportName = "closedposInterval.jasper";
//		
//		printReport(reportName, reportfields, reportsAttach);
//		FacesContext.getCurrentInstance().renderResponse();
//        FacesContext.getCurrentInstance().responseComplete();
		
		return null;
	}
	private String companyName, companyAddress, companyTelephone;
	public String exportInventoryForBranchReport(){
//		String selectedBranchName = getBranchNameById(branchMap);
//		reportfields = new HashMap<Object, Object>();
//		reportfields.put("path", companyLogo);
//		reportfields.put("user", userId);
//		reportfields.put("branchId", selectedBranch);
//		reportfields.put("companyName", companyName);
//		reportfields.put("companyAdd" , companyAddress);
//		reportfields.put("companyTel" , companyTelephone);
//		reportfields.put("branchName" , selectedBranchName);
//		if(minimumStockLimit > 0){
//			reportfields.put("limit" , minimumStockLimit.toString());
//			reportName = "inventoryLimit.jasper";
//		}else
//			reportName = "inventory.jasper";
//		
//		printReport(reportName, reportfields, reportsAttach);
//		FacesContext.getCurrentInstance().renderResponse();
//        FacesContext.getCurrentInstance().responseComplete();
//		
//		minimumStockLimit = 0;
		return null;
	}
	
	public String exportSupplierBillsForSupplierReport(){
//		if(selectedSupplier != null){
//			reportfields = new HashMap();
//			String DATE_FORMAT = "dd/mm/yyyy";
//		    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//		    
//			reportfields.put("supplier", selectedSupplier.getId());
//			reportfields.put("start", startDate);
//			reportfields.put("end", endDate);
//	        reportfields.put("path", companyLogo);
//			
//	        reportName = "suppliers_billsTotal.jasper";
//			
//			printReport(reportName, reportfields,reportsAttach);
//			FacesContext.getCurrentInstance().renderResponse();
//	        FacesContext.getCurrentInstance().responseComplete();
//		}else{
//			FacesContext.getCurrentInstance().addMessage(
//					"",
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
//							getLocale("com.pos.locale.messages",
//									"select.vendor")));
//		}
		return null;
	}
	private String getBranchNameById(Map<String, String> branchMap) {
		String branchName = "";
		Object [] values = branchMap.entrySet().toArray();
		Object [] keys   = branchMap.keySet().toArray();
//		for(int index = 0; index < keys.length; index++){
//			System.out.println("value = " + values[index].toString());
//			System.out.println("key = " +   keys[index].toString());
//			if(values[index].toString().contains(selectedBranch)){
//				branchName = keys[index].toString();
//				System.out.println("key = " + branchName);
//				break;
//			}
//		}
		return branchName;
	}


	
	public static void printReport( String reportName, Map reportfields, boolean attach) {
//		Connection connection = null;
//        try {
//        	connection  = new DBConnector().getConnection();
//        	Map reportparams = new HashMap();
//            reportfields.put(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
//            JasperPrint jp = JasperFillManager.fillReport(FacesContext
//					.getCurrentInstance()
//					.getExternalContext()
//					.getRealPath(
//							"WEB-INF\\classes\\com\\pos\\reports\\"
//									+ reportName).toString(), reportfields, connection);
//            if (jp != null) {
//            	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext(). getResponse();
//    			byte[] pdfReport = JasperExportManager
//    					.exportReportToPdf(jp);
//    			response.reset();
//    			
//    			response.setContentType("application/pdf");
//    			response.setHeader("Pragma", "no-store");
//    			if(attach)
//    				response.setHeader("Content-disposition", "attachment; filename=\"document."+new Date().toString() +".pdf\"");
//    			else
//    				response.setHeader("Content-disposition", "inline; filename=\"document."+new Date().toString() +".pdf\"");
//    			response.setContentLength(pdfReport.length);
//    			response.getOutputStream().write(pdfReport);
//    			response.getOutputStream().flush();
//    			response.getOutputStream().close();
//        }
//        }catch (Exception e) {
//           e.printStackTrace();
//        }finally{
//        	try{
//        		connection.close();
//        	}catch(Exception e){
//        		e.printStackTrace();
//        	}
//        }
    }
	
	
	public static void printTktReport( String reportName, Map reportfields, boolean attach) {
//		Connection connection = null;
//        try {
//        	connection  = new DBConnector().getConnection();
//        	reportfields.put(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
//            JasperPrint jp  = JasperFillManager.fillReport(FacesContext
//					.getCurrentInstance()
//					.getExternalContext()
//					.getRealPath(
//							"WEB-INF\\classes\\com\\pos\\reports\\"
//									+ reportName).toString(), null, new JRMapArrayDataSource(new Object[] { reportfields } )); 
//            if (jp != null) {
//            	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext(). getResponse();
//            	byte[] pdfReport = JasperExportManager
//    					.exportReportToPdf(jp);
//    		   if(attach)
//    				response.addHeader("Content-disposition", "attachment; filename=\"ticket."+new Date().toString() +".pdf\"");
//    			else
//    				response.addHeader("Content-disposition", "inline; filename=\"ticket."+new Date().toString() +".pdf\"");
//            	ServletOutputStream servletOutputStream=response.getOutputStream();
//            	JRPdfExporter docxExporter=new JRPdfExporter();
//            	docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//            	docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
//            	docxExporter.exportReport();
//            	servletOutputStream.flush();
//    	        servletOutputStream.close();
//        }
//        }catch (Exception e) {
//           e.printStackTrace();
//        }finally{
//        	try{
//        		connection.close();
//        	}catch(Exception e){
//        		e.printStackTrace();
//        	}
//        }
    }
}
